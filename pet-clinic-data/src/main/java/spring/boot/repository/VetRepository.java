package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.model.Vet;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
