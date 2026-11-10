package hostel.controllers;

import hostel.models.Student;
import hostel.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        
        return getPaginatedStudents(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginatedStudents(@PathVariable(value = "pageNo") int pageNo,
                                       @org.springframework.web.bind.annotation.RequestParam(value = "sortField", defaultValue = "name") String sortField,
                                       @org.springframework.web.bind.annotation.RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
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
}