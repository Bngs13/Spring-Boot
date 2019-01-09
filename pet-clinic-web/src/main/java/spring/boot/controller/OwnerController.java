package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.Owner;
import spring.boot.service.OwnerService;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String ListOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping({"/find"})
    public String FİndOwners() {
        return "notFound";
    }

    //20190108, ModelAndView is composite object
    @GetMapping("/{ownerId}")
    public ModelAndView List(@PathVariable Long ownerId) {
        if (ownerId <= 0) return null;
        Owner owners = ownerService.findById(ownerId);
        ModelAndView model = new ModelAndView("owners/list");
        model.addObject(owners);
        return model;
    }
}
