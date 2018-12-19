package spring.boot.repository;

import spring.boot.model.PetType;
import spring.boot.service.CrudService;

public interface PetTypeRepository extends CrudService<PetType,Long> {
}
