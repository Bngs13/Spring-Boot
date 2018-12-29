package spring.boot.service.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Vet;
import spring.boot.repository.VetRepository;
import spring.boot.service.VetService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        //vetRepository.findAll().forEach(vet -> vets.add(vet));
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        if (id == 0) return null;
        Optional<Vet> optionalVet = vetRepository.findById(id);
        return optionalVet.orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        if (object == null) return null;
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        if (object == null) return;
        try {
            vetRepository.delete(object);
        } catch (Exception ex) {
            throw new RuntimeException("Exception from delete method into VetRepository");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == 0) return;
        try {
            vetRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Exception in delete method into ownerRepository");
        }
    }
}
