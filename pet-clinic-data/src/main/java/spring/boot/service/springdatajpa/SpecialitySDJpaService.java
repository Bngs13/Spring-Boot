package spring.boot.service.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.boot.model.Speciality;
import spring.boot.repository.SpecialityRepository;
import spring.boot.service.SpecialityService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        if (id == 0) return null;
        Optional<Speciality> optionalSpeciality = specialityRepository.findById(id);
        return optionalSpeciality.orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        if (object == null) return null;
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        if (object == null) return;
        try {
            specialityRepository.delete(object);
        } catch (Exception ex) {
            throw new RuntimeException("Exception from delete method into SpecialityRepository");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == 0) return;
        try {
            specialityRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Exception in delete method into SpecialityRepository");
        }
    }
}
