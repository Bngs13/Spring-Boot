package spring.boot.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//20181219 #16
@Entity
@Table(name = "Vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    //eager, JPA is going to try to load everything all at once.
    //By lazy initialization, which is the default, it doesn't load
    //until it it's asked for so you'll get back of that entity and the specialties would be null
    @JoinTable(name = "VetSpeicality",
            joinColumns = @JoinColumn(name="VetId"),inverseJoinColumns = @JoinColumn(name = "SpecialityId"))
    private Set<Speciality> specialities=new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
