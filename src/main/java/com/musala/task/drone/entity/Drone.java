package com.musala.task.drone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.task.drone.entity.converter.ModelAttributeConverter;
import com.musala.task.drone.entity.converter.StatusAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@Entity
@Table(name = "drone")
public class Drone {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(length = 100,name="serial_number",unique = true,nullable = false)
    private String serialNumber;

    @Column(name = "model",nullable = false)
    @Convert(converter = ModelAttributeConverter.class)
    private DroneModel.ModelEnum model;

    @Column(name = "weight_limit",nullable = false)
    @Min(0)
    private int weightLimit;

    @Column(name = "battery",nullable = false)
    @Max(100)
    @Min(0)
    private Integer battery;

    @Column(name = "state",nullable = false)
    @Convert(converter = StatusAttributeConverter.class)
    private DroneModel.StateEnum state;

    @OneToMany(cascade={CascadeType.ALL},
            mappedBy = "drone",
            fetch = FetchType.EAGER)
    @OrderBy("id DESC")
    List<Medication> medicationList;

}
