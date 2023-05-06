package org.utils;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends Person {
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Client() {
        super();
    }

    public Client (String fullName, String phone, String email) {
        super(fullName);
        this.phone = phone;
        this.email = email;
    }

    public void setPhone(String newPhone){phone = newPhone;}
    public void setEmail(String newEmail){email = newEmail;}
    public void setFullName(String newName){fullName = newName;}

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}