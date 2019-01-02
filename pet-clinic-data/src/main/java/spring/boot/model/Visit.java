package spring.boot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data //20190102 #20
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Visits")
public class Visit extends BaseEntity {

    @Column(name = "Date")
    private LocalDate date;
    @Column(name = "Description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "PetId") //20181219, writing many side
    private Pet pet;

}
