package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Double power;
    @Column(name = "year_of_issue")
    private Short year;
//    @Column(name = "assessed_value")
//    private BigDecimal value; //оценочная стоимость
}
