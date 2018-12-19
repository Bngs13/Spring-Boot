package spring.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//20181219 #16
@Entity
@Table(name = "Specialties")
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
