package spring.boot.repository;

import spring.boot.model.Pet;
import spring.boot.service.CrudService;

public interface PetRepository extends CrudService<Pet,Long> {
}
