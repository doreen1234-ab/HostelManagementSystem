package hostel.services;

import hostel.models.Allocation;
import hostel.models.Room;
import hostel.models.Student;
import hostel.repositories.AllocationRepository;
import hostel.repositories.RoomRepository;
import hostel.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public String allocateRoom(Integer studentId, Integer roomId) {
        // Business Rule 1: Student must exist
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Business Rule 2: Room must exist
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

        // Business Rule 3: Room must not be Full or Maintenance
        if ("Full".equals(room.getStatus()) || "Maintenance".equals(room.getStatus())) {
            throw new RuntimeException("Room is not available for allocation.");
        }

        // Business Rule 4: Student must not already have an active allocation
        boolean hasActiveAllocation = allocationRepository.findByActiveTrue()
                .stream()
                .anyMatch(a -> a.getStudent().getStudentId().equals(studentId));
        
        if (hasActiveAllocation) {
            throw new RuntimeException("Student already has an active room allocation.");
        }

      
        Allocation allocation = new Allocation();
        allocation.setStudent(student);
        allocation.setRoom(room);
        allocation.setAllocDate(LocalDate.now());
        allocation.setActive(true);
        allocationRepository.save(allocation);

     
        room.setStatus("Full"); 
        roomRepository.save(room);

       
        return "Success";
    }
}