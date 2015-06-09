package bangbang.yodasbox.Model;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

public class CharacterResultJSON implements Serializable {
    private String name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeworld, created, edited, url;
    private ArrayList films, species, vehicles, starships;

    @Override
    public String toString() {
        return "CharacterResultJSON{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", hair_color='" + hair_color + '\'' +
                ", skin_color='" + skin_color + '\'' +
                ", eye_color='" + eye_color + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", gender='" + gender + '\'' +
                ", homeworld='" + homeworld + '\'' +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                ", films=" + films +
                ", species=" + species +
                ", vehicles=" + vehicles +
                ", starships=" + starships +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList getFilms() {
        return films;
    }

    public void setFilms(ArrayList films) {
        this.films = films;
    }

    public ArrayList getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList species) {
        this.species = species;
    }

    public ArrayList getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList getStarships() {
        return starships;
    }

    public void setStarships(ArrayList starships) {
        this.starships = starships;
    }
}