package hostel.controllers;

import hostel.models.Complaint;
import hostel.repositories.ComplaintRepository;
import hostel.repositories.RoomRepository;
import hostel.repositories.StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
    
    private final StudentRepository studentRepo;
    private final RoomRepository roomRepo;
    private final ComplaintRepository complaintRepo;

    public DashboardController(StudentRepository studentRepo, RoomRepository roomRepo, ComplaintRepository complaintRepo) {
        this.studentRepo = studentRepo;
        this.roomRepo = roomRepo;
        this.complaintRepo = complaintRepo;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentRepo.count());
        model.addAttribute("totalRooms", roomRepo.count());
        
        // Fetch 3 most recent complaints for "Recent Activity"
        List<Complaint> recent = complaintRepo.findAll(Sort.by(Sort.Direction.DESC, "complaintId"))
                .stream().limit(3).collect(Collectors.toList());
        model.addAttribute("recentComplaints", recent);
        
        return "dashboard";
    }
}