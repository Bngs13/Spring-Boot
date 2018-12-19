package spring.boot.repository;

import spring.boot.model.Owner;
import spring.boot.service.CrudService;

public interface OwnerRepository extends CrudService<Owner,Long> {
}
