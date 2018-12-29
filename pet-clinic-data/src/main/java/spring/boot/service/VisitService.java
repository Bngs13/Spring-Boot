package spring.boot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Visit;

@Service
@Profile("service")
public interface VisitService extends CrudService<Visit,Long> {
}
