package hostel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hostel.repositories.StudentRepository;
import hostel.repositories.RoomRepository;

@Controller
public class DashboardController {
    
    private final StudentRepository studentRepo;
    private final RoomRepository roomRepo;

    public DashboardController(StudentRepository studentRepo, RoomRepository roomRepo) {
        this.studentRepo = studentRepo;
        this.roomRepo = roomRepo;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentRepo.count());
        model.addAttribute("totalRooms", roomRepo.count());
        return "dashboard"; 
    }
}