package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.Owner;
import spring.boot.service.OwnerService;

import java.util.List;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // @GetMapping({"", "/", "/index", "/index.html"})
    // public String ListOwners(Model model) {
    //    model.addAttribute("owners", ownerService.findAll());
    //    return "owners/index";
    // }

    //20190112,  Builder pattern to ensure the thread-safety and atomicity of object creation.
    //Builder-final ??
    @GetMapping({"/find"})
    public String FindOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/find";
    }

    //20190112, BindingResult represents binding results
    //Extends the interface for error registration capabilities, allowing for a Validator to be applied,
    //and adds binding-specific analysis and model building
    @GetMapping
    public String ProcessFindForm(Owner owner, BindingResult result, Model model) {

        List<Owner> owners = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if (owner==null|| owner.getLastName() == null || owner.getLastName().isEmpty() || owners.isEmpty()) {
            //rejectValue(): Register a field error for the specified field of the current object
            result.rejectValue("lastName", "notFound", "notFound");
            return "owners/find";
        } else if (owners.size() == 1) {
            owner = owners.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/findResult";
        }

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

    //20190109, Web Data Binder
    @InitBinder //It injects WebDataBinder when controller is created
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
