package spring.boot.service.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Owner;
import spring.boot.repository.OwnerRepository;
import spring.boot.service.OwnerService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;

    }

    @Override
    public Owner findByLastName(String lastName) {
        if (lastName.isEmpty() || lastName == null) return null;
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {

        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        if (id == 0) return null;
        //return ownerRepository.findById(id);

        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        //this blog is shortened as following return statement
        //if (!optionalOwner.isPresent()) return null;
        //return optionalOwner.get();
        return optionalOwner.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        if (object == null) return null;
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        if (object == null) return;
        try {
            ownerRepository.delete(object);
        } catch(Exception ex) {
            throw new RuntimeException("Exception from delete method into OwnerRepository");
        }
    }

    @Override
    public void deleteById(Long id) {

        if(id==0) return;
        try {
            ownerRepository.deleteById(id);
        }catch(Exception ex) {
            throw new RuntimeException("Exception in delete method into OwnerRepository");
        }
    }
}
