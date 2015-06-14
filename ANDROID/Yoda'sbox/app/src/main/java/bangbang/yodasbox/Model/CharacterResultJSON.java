package bangbang.yodasbox.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.nio.charset.Charset;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CharacterResultJSON implements Serializable
{
    private String name, height, hair_color, skin_color, eye_color, gender, homeworld;

    public CharacterResultJSON()
    {

    }

    @Override
    public String toString() {
        return "CharacterResultJSON{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", hair_color='" + hair_color + '\'' +
                ", skin_color='" + skin_color + '\'' +
                ", eye_color='" + eye_color + '\'' +
                ", gender='" + gender + '\'' +
                ", homeworld='" + homeworld + '\'' +
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

}