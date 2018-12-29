package spring.boot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.PetType;

@Service
@Profile("service")
public interface PetTypeService extends CrudService<PetType, Long>  {
}
