package spring.boot.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//20190102 #20
@Data
@Builder //constructor inheritance like Owner -> Person -> BaseEntity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"pets"})
//20181219 #16
@Entity
@Table(name = "Owners")
public class Owner extends Person {

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

}
