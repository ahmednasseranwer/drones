package com.musala.task.drone.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code", nullable = false)
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "allowed only upper case letters, underscore and numbers")


    private String code;

    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "image", nullable = false)
    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", referencedColumnName = "id", nullable = false)
    private Drone drone;

}
