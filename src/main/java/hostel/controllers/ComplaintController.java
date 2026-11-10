package hostel.controllers;

import hostel.models.Complaint;
import hostel.models.Student;
import hostel.repositories.ComplaintRepository;
import hostel.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintRepository complaintRepository;
    private final StudentRepository studentRepository;

    public ComplaintController(ComplaintRepository complaintRepository, StudentRepository studentRepository) {
        this.complaintRepository = complaintRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listComplaints(Model model) {
        model.addAttribute("complaints", complaintRepository.findAll());
        return "complaints/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("students", studentRepository.findAll());
        return "complaints/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid complaint Id:" + id));
        model.addAttribute("complaint", complaint);
        model.addAttribute("students", studentRepository.findAll());
        return "complaints/form";
    }

    @PostMapping("/save")
    public String saveComplaint(
            @RequestParam(value = "complaintId", required = false) Integer complaintId,
            @RequestParam("studentId") Integer studentId,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("status") String status) {

        Complaint complaint = (complaintId != null) 
                ? complaintRepository.findById(complaintId).orElse(new Complaint()) 
                : new Complaint();

        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) {
            return "redirect:/complaints?error=InvalidStudent";
        }

        complaint.setStudent(student.get());
        complaint.setCategory(category);
        complaint.setDescription(description);
        complaint.setStatus(status);

        if (complaint.getCreatedOn() == null) {
            complaint.setCreatedOn(LocalDate.now());
        }

        if ("Resolved".equals(status) && complaint.getResolvedOn() == null) {
            complaint.setResolvedOn(LocalDate.now());
        }

        complaintRepository.save(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/delete/{id}")
    public String deleteComplaint(@PathVariable("id") Integer id) {
        complaintRepository.deleteById(id);
        return "redirect:/complaints";
    }
}