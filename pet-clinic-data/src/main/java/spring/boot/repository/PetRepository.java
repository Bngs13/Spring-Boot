package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet,Long> {
}
