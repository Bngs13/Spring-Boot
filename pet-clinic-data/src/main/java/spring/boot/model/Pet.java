package spring.boot.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//20190102 #20
@Data
//@Builder //20190115, commented
//@AllArgsConstructor //20190115, commented
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"visits"})
//20181219 #16
@Entity
@Table(name = "Pets")
public class Pet extends BaseEntity {

    //20190115
    @Builder
    public Pet(String name, PetType petType, Set<Visit> visits, Owner owner, LocalDate birthDate) {
        this.name = name;
        this.petType = petType;
        this.visits = visits;
        this.owner = owner;
        this.birthDate = birthDate;
    }

    public Pet(Long id, String name, PetType petType, Set<Visit> visits, Owner owner, LocalDate birthDate) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.visits = visits;
        this.owner = owner;
        this.birthDate = birthDate;
    }

    @Column(name = "Name")
    private String name;
    @ManyToOne
    //@JoinColumn(name = "Id")
    private PetType petType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "OwnerId")
    private Owner owner;
    @Column(name = "BirthDate")
    private LocalDate birthDate;

}
