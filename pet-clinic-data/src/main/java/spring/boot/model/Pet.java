package spring.boot.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
