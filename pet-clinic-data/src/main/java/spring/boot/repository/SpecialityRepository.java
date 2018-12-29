package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.Speciality;

@Repository
public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
}
