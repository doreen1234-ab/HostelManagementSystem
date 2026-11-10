package hostel.controllers;

import hostel.models.Student;
import hostel.repositories.StudentRepository;
import hostel.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listStudents(Model model) {
        return getPaginatedStudents(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginatedStudents(@PathVariable(value = "pageNo") int pageNo,
                                       @RequestParam(value = "sortField", defaultValue = "name") String sortField,
                                       @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                       Model model) {
        Page<Student> page = studentService.getPaginatedStudents(pageNo, sortField, sortDir);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("students", page.getContent());
        return "students/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Student s = studentRepository.findById(id).orElseThrow();
        model.addAttribute("student", s);
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@RequestParam(value = "studentId", required = false) Integer studentId,
                              @RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone,
                              @RequestParam("gender") String gender,
                              @RequestParam("department") String department,
                              @RequestParam("yearOfStudy") Integer yearOfStudy) {
        Student s = (studentId != null) ? studentRepository.findById(studentId).orElse(new Student()) : new Student();
        s.setName(name);
        s.setEmail(email);
        s.setPhone(phone);
        s.setGender(gender);
        s.setDepartment(department);
        s.setYearOfStudy(yearOfStudy);
        studentRepository.save(s);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}