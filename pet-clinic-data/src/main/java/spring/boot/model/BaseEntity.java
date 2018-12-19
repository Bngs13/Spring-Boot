package spring.boot.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass //20181219, It makes this class is base class to JPA #16
public class BaseEntity implements Serializable {

    @Id //20181219 #16
    @GeneratedValue(strategy = GenerationType.IDENTITY) //20181219, Depends on DB!
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
