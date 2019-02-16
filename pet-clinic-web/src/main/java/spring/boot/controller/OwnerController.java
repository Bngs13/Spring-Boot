package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.Owner;
import spring.boot.service.OwnerService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {

    //20190114, Good habit, You should use view names as private final string
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/form";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String ListOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

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

        List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (owner == null || owner.getLastName() == null || owner.getLastName().isEmpty() || owners.isEmpty()) {
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

    //20190114
    @GetMapping("/new")
    public String OwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String OwnerForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String Edit(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String Edit(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors())
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;

        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();

    }
}
