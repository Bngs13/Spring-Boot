package spring.boot.service.map;

import spring.boot.model.Owner;
import spring.boot.service.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void save(Owner object) {
        super.save(object.getId(),object);
    }

    @Override
    public Owner findById(Long id) {
      return super.findById(id);
    }
}
