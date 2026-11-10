package hostel.controllers;

import hostel.models.HostelBlock;
import hostel.models.Room;
import hostel.models.RoomType;
import hostel.repositories.HostelBlockRepository;
import hostel.repositories.RoomRepository;
import hostel.repositories.RoomTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public String listRooms(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                            @RequestParam(value = "sortField", defaultValue = "roomId") String sortField,
                            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                            Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("asc") 
                    ? Sort.by(sortField).ascending() 
                    : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 5, sort);
        Page<Room> page = roomRepository.findAll(pageable);

        model.addAttribute("rooms", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "rooms/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("blocks", blockRepository.findAll()); 
        model.addAttribute("roomTypes", roomTypeRepository.findAll()); 
        return "rooms/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        model.addAttribute("room", room);
        model.addAttribute("blocks", blockRepository.findAll()); 
        model.addAttribute("roomTypes", roomTypeRepository.findAll()); 
        return "rooms/form";
    }

    @PostMapping("/save")
    public String saveRoom(
            @RequestParam(value = "roomId", required = false) Integer roomId,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("blockId") Integer blockId,
            @RequestParam("roomTypeId") Integer roomTypeId,
            @RequestParam("status") String status) {

        Room room = new Room();
        if (roomId != null) {
            room = roomRepository.findById(roomId).orElse(new Room());
        }

        room.setRoomNumber(roomNumber);
        room.setStatus(status);

        HostelBlock block = blockRepository.findById(blockId).orElse(null);
        RoomType roomType = roomTypeRepository.findById(roomTypeId).orElse(null);

        room.setBlock(block);
        room.setRoomType(roomType);

        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        roomRepository.deleteById(id);
        return "redirect:/rooms";
    }
}