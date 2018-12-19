package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.model.Visit;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
