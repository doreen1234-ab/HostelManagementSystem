package hostel.controllers;

import hostel.models.Warden;
import hostel.repositories.WardenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wardens")
public class WardenController {
    private final WardenRepository wardenRepository;

    public WardenController(WardenRepository wardenRepository) {
        this.wardenRepository = wardenRepository;
    }

    @GetMapping
    public String listWardens(Model model) {
        model.addAttribute("wardens", wardenRepository.findAll());
        return "wardens/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("warden", new Warden());
        return "wardens/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Warden warden = wardenRepository.findById(id).orElseThrow();
        model.addAttribute("warden", warden);
        return "wardens/form";
    }

    @PostMapping("/save")
    public String saveWarden(@RequestParam(value = "wardenId", required = false) Integer wardenId,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email) {
        Warden warden = (wardenId != null) ? wardenRepository.findById(wardenId).orElse(new Warden()) : new Warden();
        warden.setName(name);
        warden.setPhone(phone);
        warden.setEmail(email);
        wardenRepository.save(warden);
        return "redirect:/wardens";
    }

    @GetMapping("/delete/{id}")
    public String deleteWarden(@PathVariable("id") Integer id) {
        wardenRepository.deleteById(id);
        return "redirect:/wardens";
    }
}