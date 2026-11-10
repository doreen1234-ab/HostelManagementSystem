package hostel.controllers;

import hostel.models.FeePayment;
import hostel.repositories.FeePaymentRepository;
import hostel.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/payments")
public class FeePaymentController {
    private final FeePaymentRepository paymentRepo;
    private final StudentRepository studentRepo;

    public FeePaymentController(FeePaymentRepository paymentRepo, StudentRepository studentRepo) {
        this.paymentRepo = paymentRepo;
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentRepo.findAll());
        return "payments/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "payments/form";
    }

    @PostMapping("/save")
    public String savePayment(@Valid @ModelAttribute FeePayment payment, BindingResult result, 
                              @RequestParam("studentId") Integer studentId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentRepo.findAll());
            return "payments/form";
        }
        payment.setStudent(studentRepo.findById(studentId).orElseThrow());
        payment.setPaymentDate(LocalDate.now());
        paymentRepo.save(payment);
        return "redirect:/payments";
    }

}