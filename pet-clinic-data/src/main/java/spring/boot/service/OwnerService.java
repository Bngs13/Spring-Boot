package spring.boot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Owner;

@Service
@Profile("service")
public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
