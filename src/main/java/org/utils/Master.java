package org.utils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "masters")
public class Master extends Person {
    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "master_to_service",
            joinColumns = {@JoinColumn(name = "master_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "master_to_client",
            joinColumns = {@JoinColumn(name = "master_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")}
    )
    private List<Client> clients;

    public Master() {
        super();
    }

    public Master(String fullName, String specialization) {
        super(fullName);
        this.specialization = specialization;
    }

    public void setFullName(String newName){fullName = newName;}
    public void setSpecialization(String newSpecialization){specialization = newSpecialization;}
    public String getSpecialization() {
        return specialization;
    }

}
