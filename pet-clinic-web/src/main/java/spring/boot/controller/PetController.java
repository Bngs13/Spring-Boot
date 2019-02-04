package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.boot.model.Owner;
import spring.boot.model.Pet;
import spring.boot.model.PetType;
import spring.boot.service.OwnerService;
import spring.boot.service.PetService;
import spring.boot.service.PetTypeService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//20190114
@Controller
@RequestMapping("owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/form";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    //ModelAttribute: binds a method parameter or method return value to a named model attribute and then exposes it to a web view
    //ModelAttribute methods are invoked before the controller methods annotated with @RequestMapping or other mappings
    //(like get or post) are invoked.
    //the annotation is used to define objects which should be part of a Model.
    //So if you want to have a Person object referenced in the Model you can use it
    @ModelAttribute("petTypesForSelect")
    public Collection<String> populatePetTypes() {
        Set<PetType> petType = petTypeService.findAll();
        if (petType != null) {
            Set<String> petTypeNameList = new HashSet<>();
            for (PetType type : petType) {
                petTypeNameList.add(type.getName());
            }
            return petTypeNameList;
        }
        return null;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        if (ownerId == 0) return null;
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    //Model is an interface,
    //ModelMap is a class.
    //ModelAndView is just a container for both a ModelMap and a view object. It allows a controller to return both as a single value.
    @GetMapping("/pets/new")
    public String PetForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;

    }

    @PostMapping("/pets/new")
    public String PetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (owner == null || pet == null) return null;
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
            result.rejectValue("name", "duplicate", "already exist");
        owner.getPets().add(pet);
        //20190115, same error with recipe project !!!
        //owner has pets but pets have not owner, I didn't set it,
        //So owner becomes null!!
        pet.setOwner(owner);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("pets/{petId}/edit")
    public String Edit(@PathVariable Long petId, Model model) {
        if (petId <= 0) return null;
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/{petId}/edit")
    public String Edit(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (owner == null) return null;
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();

    }

}
