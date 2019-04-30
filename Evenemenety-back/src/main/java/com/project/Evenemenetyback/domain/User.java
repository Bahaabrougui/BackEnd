package com.project.Evenemenetyback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.project.Evenemenetyback.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "user")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

   /* @OneToMany(mappedBy = "user")
    private List<Event> event; */

   /* @OneToMany(mappedBy = "user")
    private List<Covoiturage> covoiturage; */

   /* @OneToMany(mappedBy = "user")
    private List<EventAddRequest> requests; */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", full name='" + name + '\'' +
                ", email='" + email + '\'' +
                "}";
    }

    public User() {}

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

        public String getName(){
        return name;
        }

        public String getUsername(){
        return username;
        }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


   /* public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public List<Covoiturage> getCovoiturage() {
        return covoiturage;
    }

    public void setCovoiturage(List<Covoiturage> covoiturage) {
        this.covoiturage = covoiturage;
    }

    public List<EventAddRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<EventAddRequest> requests) {
        this.requests = requests;
    } */
}
