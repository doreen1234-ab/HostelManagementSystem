package hostel;

import hostel.models.Room;
import hostel.models.Student;
import hostel.repositories.RoomRepository;
import hostel.repositories.StudentRepository;
import hostel.services.AllocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HostelApplicationTests {

    @Autowired
    private AllocationService allocationService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StudentRepository studentRepository;

    // ----- BUSINESS RULE TESTS -----

    @Test
    void testBR1_StudentMustExistToAllocate() {
        // Rule: allocation should fail if the student ID does not exist
        Exception ex = assertThrows(RuntimeException.class, () -> {
            allocationService.allocateRoom(99999, 1);
        });
        assertTrue(ex.getMessage().contains("Student not found"));
    }

    @Test
    void testBR2_RoomMustExistToAllocate() {
        // Fetch any real student
        List<Student> students = studentRepository.findAll();
        assertFalse(students.isEmpty(), "At least one student must exist to run this test");
        Integer realStudentId = students.get(0).getStudentId();

        // Rule: allocation should fail if the room ID does not exist
        Exception ex = assertThrows(RuntimeException.class, () -> {
            allocationService.allocateRoom(realStudentId, 99999);
        });
        assertTrue(ex.getMessage().contains("Room not found"));
    }

    @Test
    void testBR3_PreventDuplicateActiveAllocation() {
        // Fetch any student who already has an active allocation
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            fail("No students in the database to test with");
        }
        Integer studentId = students.get(0).getStudentId();
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            fail("No rooms in the database to test with");
        }
        Integer roomId = rooms.get(0).getRoomId();

        // First allocation may succeed or fail depending on state.
        // The second allocation with the same student must fail.
        try {
            allocationService.allocateRoom(studentId, roomId);
        } catch (RuntimeException ignored) {
           
        }

        Exception ex = assertThrows(RuntimeException.class, () -> {
            allocationService.allocateRoom(studentId, roomId);
        });
        assertTrue(ex.getMessage().contains("already has an active allocation"));
    }

    // ----- QUERY TESTS -----

    @Test
    void testQuery1_FindByStatus() {
        // Query test: findByStatus should never return null
        List<Room> available = roomRepository.findByStatus("Available");
        assertNotNull(available);
        // Every returned room must have the requested status
        available.forEach(r -> assertEquals("Available", r.getStatus()));
    }

    @Test
    void testQuery2_FindRoomsByBlockName() {
        // Query test: findRoomsByBlockName should return rooms for a known block
        List<Room> rooms = roomRepository.findRoomsByBlockId(1);
        assertNotNull(rooms);
    }
}