package com.project.Evenemenetyback.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "event")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name="date")
    private String date;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(name = "music")
    private String music;

    @NotNull
    @Column(name = "place")
    private String place;

    @NotNull
    @Column(name = "starting_time")
    private String startingTime;


    @Column(name = "ending_time")
    private String endingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

   /* @OneToMany(mappedBy = "event")
    private List<Covoiturage> covoiturage; */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 /*
    public List<Covoiturage> getCovoiturage() {
        return covoiturage;
    }

    public void setCovoiturage(List<Covoiturage> covoiturage) {
        this.covoiturage = covoiturage;
    } */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
