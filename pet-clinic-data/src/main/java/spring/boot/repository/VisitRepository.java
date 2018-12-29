package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.Visit;
@Repository
public interface VisitRepository extends CrudRepository<Visit,Long> {
}
