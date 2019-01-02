package spring.boot.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

//20190102 #20
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vets"})
//20181219 #16
@Entity
//@Table(name = "Specialties")
public class Speciality extends BaseEntity {

    //@Column(name = "description")
    private String description;
    //20181229
    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets=new HashSet<>();

}
