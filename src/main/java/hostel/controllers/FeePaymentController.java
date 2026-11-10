package hostel.controllers;

import hostel.models.FeePayment;
import hostel.models.Student;
import hostel.repositories.FeePaymentRepository;
import hostel.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
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
        model.addAttribute("students", studentRepo.findAll());
        return "payments/form";
    }

    @PostMapping("/save")
    public String savePayment(@RequestParam("studentId") Integer studentId,
                              @RequestParam("amount") BigDecimal amount,
                              @RequestParam("paymentMode") String paymentMode) {
        FeePayment payment = new FeePayment();
        payment.setStudent(studentRepo.findById(studentId).orElseThrow());
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMode(paymentMode);
        paymentRepo.save(payment);
        return "redirect:/payments";
    }
}