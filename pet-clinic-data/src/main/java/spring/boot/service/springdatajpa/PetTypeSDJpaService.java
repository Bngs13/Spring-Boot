package spring.boot.service.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.PetType;
import spring.boot.repository.PetTypeRepository;
import spring.boot.service.PetTypeService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        if (id == 0) return null;
        Optional<PetType> optionalPetType = petTypeRepository.findById(id);
        return optionalPetType.orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        if (object == null) return null;
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {

        if (object == null) return;
        try {
            petTypeRepository.delete(object);
        } catch (Exception ex) {
            throw new RuntimeException("Exception from delete method into PetTypeRepository");
        }
    }

    @Override
    public void deleteById(Long id) {

        if(id==0) return;
        try {
            petTypeRepository.deleteById(id);
        }catch(Exception ex) {
            throw new RuntimeException("Exception in delete method into PetTypeRepository");
        }
    }
}
