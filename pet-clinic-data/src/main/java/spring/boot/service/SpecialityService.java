package spring.boot.service;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Speciality;

@Service
@Profile("service")
public interface SpecialityService extends CrudService<Speciality,Long> {
}
