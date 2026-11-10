package hostel.controllers;

import hostel.models.FeePayment;
import hostel.repositories.FeePaymentRepository;
import hostel.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        model.addAttribute("payment", new FeePayment());
        model.addAttribute("students", studentRepo.findAll());
        return "payments/form";
    }

    @PostMapping("/save")
    public String savePayment(
            @Valid @ModelAttribute("payment") FeePayment payment,
            BindingResult result,
            @RequestParam("studentId") Integer studentId,
            Model model) {

        // Reject negative/zero amount
        if (payment.getAmount() != null && payment.getAmount().signum() <= 0) {
            result.rejectValue("amount", "invalid", "Amount must be greater than zero.");
        }

        if (result.hasErrors()) {
            model.addAttribute("error", "Please correct the errors below.");
            model.addAttribute("students", studentRepo.findAll());
            return "payments/form";
        }

        payment.setStudent(studentRepo.findById(studentId).orElseThrow());
        payment.setPaymentDate(LocalDate.now());
        paymentRepo.save(payment);
        return "redirect:/payments";
    }
}