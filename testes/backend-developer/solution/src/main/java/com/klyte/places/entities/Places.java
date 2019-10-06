package com.klyte.places.entities;

import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Data
@Table(name="PLACES")
public class Places {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL_SLUG")
    private String urlSlug;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
}
