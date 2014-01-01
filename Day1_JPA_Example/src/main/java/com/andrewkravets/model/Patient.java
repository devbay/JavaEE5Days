package com.andrewkravets.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew Kravets
 * Date: 12/30/13.
 * url: andrewkravets.com
 */
@Entity
@Access(AccessType.FIELD)
public class Patient implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String familyName;

    @Enumerated(EnumType.ORDINAL)
    Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "DOB")
    Date dateOfBirth;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Pills> patientsPills;

    public String getName() {
        return name;
    }

    public List<Pills> getPatientsPills() {
        return patientsPills;
    }

    public void setPatientsPills(List<Pills> patientsPills) {
        this.patientsPills = patientsPills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
