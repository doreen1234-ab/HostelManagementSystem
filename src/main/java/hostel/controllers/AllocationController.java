package hostel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allocations")
public class AllocationController {

    @GetMapping("/new")
    public String showAllocationForm() {
        return "allocations/new"; 
    }
}