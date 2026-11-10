package hostel.controllers;

import hostel.repositories.AllocationRepository;
import hostel.repositories.ComplaintRepository;
import hostel.repositories.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private final RoomRepository roomRepository;
    private final ComplaintRepository complaintRepository;
    private final AllocationRepository allocationRepository;

    public ReportController(RoomRepository roomRepository, 
                            ComplaintRepository complaintRepository,
                            AllocationRepository allocationRepository) {
        this.roomRepository = roomRepository;
        this.complaintRepository = complaintRepository;
        this.allocationRepository = allocationRepository;
    }

    @GetMapping("/reports")
    public String showReports(Model model) {
        // Report 1: All Rooms and their status
        model.addAttribute("rooms", roomRepository.findAll());
        
        // Report 2: Unresolved Complaints
        model.addAttribute("complaints", complaintRepository.findByStatusNot("Resolved"));
        
        // Report 3: Active Allocations
        model.addAttribute("allocations", allocationRepository.findByActiveTrue());
        
        return "reports/index";
    }
}