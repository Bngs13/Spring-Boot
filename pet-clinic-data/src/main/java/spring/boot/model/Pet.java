package spring.boot.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//20190102 #20
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"visits"})
//20181219 #16
@Entity
@Table(name = "Pets")
public class Pet extends BaseEntity {

    @Column(name = "Name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "TypeId")
    private PetType petType;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pet")
    private Set<Visit> visits=new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "OwnerId")
    private Owner owner;
    @Column(name = "BirthDate")
    private LocalDate birthDate;

}
