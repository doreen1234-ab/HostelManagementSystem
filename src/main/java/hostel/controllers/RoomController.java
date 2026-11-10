package hostel.controllers;

import hostel.models.HostelBlock;
import hostel.models.Room;
import hostel.models.RoomType;
import hostel.repositories.HostelBlockRepository;
import hostel.repositories.RoomRepository;
import hostel.repositories.RoomTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final HostelBlockRepository blockRepository;
    private final RoomTypeRepository roomTypeRepository;

    public RoomController(RoomRepository roomRepository,
                          HostelBlockRepository blockRepository,
                          RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.blockRepository = blockRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        populateForm(model, new Room());
        return "rooms/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        populateForm(model, room);
        return "rooms/form";
    }

    @PostMapping("/save")
    public String saveRoom(
            @RequestParam(value = "roomId", required = false) Integer roomId,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam(value = "blockId", required = false) Integer blockId,
            @RequestParam(value = "roomTypeId", required = false) Integer roomTypeId,
            @RequestParam("status") String status,
            Model model) {

        Room room = (roomId != null)
                ? roomRepository.findById(roomId).orElse(new Room())
                : new Room();

        room.setRoomNumber(roomNumber);
        room.setStatus(status);

        Optional<HostelBlock> block = (blockId != null)
                ? blockRepository.findById(blockId) : Optional.empty();
        Optional<RoomType> roomType = (roomTypeId != null)
                ? roomTypeRepository.findById(roomTypeId) : Optional.empty();

        if (block.isEmpty() || roomType.isEmpty()) {
                   String error = block.isEmpty()
                    ? "Please select a valid hostel block."
                    : "Please select a valid room type.";
            model.addAttribute("error", error);
            populateForm(model, room);
            return "rooms/form";
        }

        room.setBlock(block.get());
        room.setRoomType(roomType.get());

        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        roomRepository.deleteById(id);
        return "redirect:/rooms";
    }

    private void populateForm(Model model, Room room) {
        model.addAttribute("room", room);
        model.addAttribute("blocks", blockRepository.findAll());
        model.addAttribute("roomTypes", roomTypeRepository.findAll());
    }
}