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
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found: " + roomId));

        // Rule: reject Maintenance AND Full rooms
        if ("Maintenance".equals(room.getStatus()) || "Full".equals(room.getStatus())) {
            throw new RuntimeException("Room is not available for allocation.");
        }

        // CHECK 1: Student already has active allocation?
        boolean hasActive = allocationRepository.findByActiveTrue().stream()
                .anyMatch(a -> a.getStudent().getStudentId().equals(studentId));
        if (hasActive) {
            throw new RuntimeException("Student already has an active allocation.");
        }

        // CHECK 2: Room capacity
        Integer capacity = room.getRoomType().getCapacity();
        long currentOccupancy = allocationRepository.findByActiveTrue().stream()
                .filter(a -> a.getRoom().getRoomId().equals(roomId))
                .count();
        if (currentOccupancy >= capacity) {
            throw new RuntimeException("Room is at full capacity (" + capacity + ").");
        }

        // Save allocation
        Allocation allocation = new Allocation();
        allocation.setStudent(student);
        allocation.setRoom(room);
        allocation.setAllocDate(LocalDate.now());
        allocation.setActive(true);
        allocationRepository.save(allocation);

        // Update room status if now full
        if ((currentOccupancy + 1) >= capacity) {
            room.setStatus("Full");
            roomRepository.save(room);
        }

        return "Success";
    }
}