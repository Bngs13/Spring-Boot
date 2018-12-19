package spring.boot.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //20181219 #16
public class Person extends BaseEntity {

    //20181219, adding column annotations #16
    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
