package hostel.controllers;

import hostel.models.Room;
import hostel.repositories.RoomRepository;
import hostel.services.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AllocationService allocationService;

   
    @GetMapping("/available")
    public List<Room> getAvailableRooms(@RequestParam("blockName") String blockName) {
        String name = blockName.trim();
        if (name.isEmpty()) {
            return Collections.emptyList();
        }
        return roomRepository.findAvailableRoomsByBlockName(name);
    }

    
    @PostMapping("/allocate")
    public ResponseEntity<String> allocateRoom(
            @RequestParam("studentId") Integer studentId,
            @RequestParam("roomId") Integer roomId) {
        try {
            String result = allocationService.allocateRoom(studentId, roomId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public org.springframework.http.ResponseEntity<String> deleteRoomAjax(@PathVariable("id") Integer id) {
        roomRepository.deleteById(id);
        return org.springframework.http.ResponseEntity.ok("Deleted");
    }
}