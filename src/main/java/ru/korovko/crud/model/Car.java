package ru.korovko.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
    private UUID id;
    private String model;
    private LocalDate manufactureDate;
    private Integer maxSpeed;
    private Boolean isAutomatic;
    @Enumerated(EnumType.STRING)
    private CarColor color;
    private Integer weight;
}
