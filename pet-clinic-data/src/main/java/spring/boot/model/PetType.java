package spring.boot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//20190102 #20
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Types")
public class PetType extends BaseEntity {

    @Column(name = "Name")
    private String name;

}
