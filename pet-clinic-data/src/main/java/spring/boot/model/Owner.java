package spring.boot.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
