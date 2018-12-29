package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.Vet;

@Repository
public interface VetRepository extends CrudRepository<Vet,Long> {

}
