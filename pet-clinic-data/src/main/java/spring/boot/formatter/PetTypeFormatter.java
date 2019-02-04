package spring.boot.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import spring.boot.model.PetType;
import spring.boot.service.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

//20190115
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        if (findPetTypes != null) {
            PetType correctPetType = new PetType();
            for (PetType pet : findPetTypes) {
                if (pet.toString() == text)
                    correctPetType = pet;
            }
            correctPetType = findPetTypes.stream().filter(x -> x.getName().contains(text)).findFirst().get();
            if (correctPetType != null) return correctPetType;
        }
        throw new ParseException("Type not found" + text, 0);
    }

    @Override
    public String print(PetType object, Locale locale) {
        return object.getName();
    }
}
