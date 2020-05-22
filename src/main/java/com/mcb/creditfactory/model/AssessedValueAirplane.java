package com.mcb.creditfactory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assessed_value_airplane")
public class AssessedValueAirplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "airplane_id", foreignKey = @ForeignKey(name = "AIRPLANE_ID_FK"))
    //@JoinColumn(name = "airplane_id")
    private Airplane airplane;
    private BigDecimal value;
    private LocalDate assessment_date;
}
