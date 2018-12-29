package spring.boot.service.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Visit;
import spring.boot.repository.VisitRepository;
import spring.boot.service.VisitService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        HashSet<Visit> visits=new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
        if (id == 0) return null;
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        if (object == null) return null;
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        if (object == null) return;
        try {
            visitRepository.delete(object);
        } catch (Exception ex) {
            throw new RuntimeException("Exception from delete method into VisitRepository");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == 0) return;
        try {
            visitRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Exception in delete method into ownerRepository");
        }
    }
}
