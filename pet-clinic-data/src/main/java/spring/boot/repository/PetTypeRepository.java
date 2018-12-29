package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.PetType;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
