package spring.boot.service.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Pet;
import spring.boot.repository.PetRepository;
import spring.boot.service.PetService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        if (id == 0) return null;
        Optional<Pet> optionalPet = petRepository.findById(id);
        return optionalPet.orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        if (object == null) return null;
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {

        if (object == null) return;
        try {
            petRepository.delete(object);
        } catch (Exception ex) {
            throw new RuntimeException("Exception from delete method into OwnerRepository");
        }
    }

    @Override
    public void deleteById(Long id) {

        if(id==0) return;
        try {
            petRepository.deleteById(id);
        }catch(Exception ex) {
            throw new RuntimeException("Exception in delete method into ownerRepository");
        }
    }
}
