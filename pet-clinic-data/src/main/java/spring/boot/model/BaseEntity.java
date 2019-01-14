package spring.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//20190102 #20
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass //20181219, It makes this class is base class to JPA #16
public class BaseEntity implements Serializable {

    @Id //20181219 #16
    @GeneratedValue(strategy = GenerationType.IDENTITY) //20181219, Depends on DB!
    private Long id;

    //20190114
    public boolean isNew() {
        return this.id == null;
    }

}
