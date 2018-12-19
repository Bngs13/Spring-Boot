package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
}
