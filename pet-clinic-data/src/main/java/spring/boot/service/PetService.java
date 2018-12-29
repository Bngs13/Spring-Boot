package spring.boot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Pet;

@Service
@Profile("service")
public interface PetService extends CrudService<Pet, Long> {
}
