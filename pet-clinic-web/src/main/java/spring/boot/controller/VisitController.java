package spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.boot.model.Pet;
import spring.boot.model.Visit;
import spring.boot.service.PetService;
import spring.boot.service.VisitService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;
    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "visits/form";

    public VisitController(VisitService visitService,PetService petService) {
        this.visitService = visitService;
        this.petService=petService;
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        if(pet!=null && pet.getVisits()!=null) {
            pet.getVisits().add(visit);
            visit.setPet(pet);
        }
        return visit;
    }


    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String VisitForm(@PathVariable Long petId){
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String VisitForm(@PathVariable Long petId, @Valid Visit visit, BindingResult result){
        if(result.hasErrors())
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        visitService.save(visit);
        return "redirect:/owners/{ownerId}";

    }
}
