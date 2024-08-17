package com.mini.project.model;

import jakarta.persistence.*;

@Entity
public class AboutUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false)
    private String fullname;

    @Column(unique = true)
    private String pnumber;

    @Column(nullable = false)
    private String address;

    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
