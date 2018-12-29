package spring.boot.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

//20181219 #16
@Entity
//@Table(name = "Specialties")
public class Speciality extends BaseEntity {

    //@Column(name = "description")
    private String description;
    //20181229
    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets=new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Vet> getVets() {
        return vets;
    }

    public void setVets(Set<Vet> vets) {
        this.vets = vets;
    }
}
