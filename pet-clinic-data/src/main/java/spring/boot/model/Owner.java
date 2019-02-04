package spring.boot.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//20190102 #20
@Data
//@Builder //constructor inheritance like Owner -> Person -> BaseEntity
//@AllArgsConstructor 20190114, I wrote it with Builder
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"pets"})
//20181219 #16
@Entity
@Table(name = "Owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets != null)  this.pets = pets;

    }

    @Column(name = "Address")
    private String address;
    @Column(name = "City")
    private String city;
    @Column(name = "Telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner") //20181219, for one owner can has one more pets
    //When deleting owner, delete related pets also
    //mappedBy refers to Owner property in the Pet class
    private Set<Pet> pets = new HashSet<>();

    //20190114
    public Pet getPet(String name, boolean ignoreNew) {

        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String petName = pet.getName();
                if (petName.equals(name)) return pet;
            }
        }
        return null;
    }

    public Pet getPet(String name) {
        return getPet(name, false);
    }


}
