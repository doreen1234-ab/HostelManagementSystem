package hostel.controllers;

import org.springframework.transaction.annotation.Transactional;
import hostel.models.LeaveRequest;
import hostel.models.Student;
import hostel.models.Warden;
import hostel.repositories.LeaveRequestRepository;
import hostel.repositories.StudentRepository;
import hostel.repositories.WardenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/leaves")
public class LeaveRequestController {
    private final LeaveRequestRepository leaveRepo;
    private final StudentRepository studentRepo;
    private final WardenRepository wardenRepo;

    public LeaveRequestController(LeaveRequestRepository leaveRepo, StudentRepository studentRepo, WardenRepository wardenRepo) {
        this.leaveRepo = leaveRepo;
        this.studentRepo = studentRepo;
        this.wardenRepo = wardenRepo;
    }

    @GetMapping
    public String listLeaves(Model model) {
        model.addAttribute("leaves", leaveRepo.findAll());
        return "leaves/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("leave", new LeaveRequest());
        model.addAttribute("students", studentRepo.findAll());
        return "leaves/form";
    }

    @PostMapping("/save")
    public String saveLeave(@RequestParam("studentId") Integer studentId,
                            @RequestParam("fromDate") String fromDate,
                            @RequestParam("toDate") String toDate,
                            @RequestParam("reason") String reason) {
        LeaveRequest leave = new LeaveRequest();
        Student student = studentRepo.findById(studentId).orElseThrow();
        leave.setStudent(student);
        leave.setFromDate(LocalDate.parse(fromDate));
        leave.setToDate(LocalDate.parse(toDate));
        leave.setReason(reason);
        leave.setStatus("Pending");
        leaveRepo.save(leave);
        return "redirect:/leaves";
    }

    @Transactional
    @PostMapping("/approve/{id}")
    public String approveLeave(@PathVariable("id") Integer id, 
                               @RequestParam("wardenId") Integer wardenId) {
        LeaveRequest leave = leaveRepo.findById(id).orElseThrow();
        
        // Rule check: only Pending requests can be approved
        if (!"Pending".equals(leave.getStatus())) {
            return "redirect:/leaves?error=AlreadyProcessed";
        }
        
        Warden warden = wardenRepo.findById(wardenId).orElse(null);
        leave.setWarden(warden);
        leave.setStatus("Approved");
        leaveRepo.save(leave);
        return "redirect:/leaves";
    }
    }