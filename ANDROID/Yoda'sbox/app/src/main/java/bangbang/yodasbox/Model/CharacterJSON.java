package bangbang.yodasbox.Model;

public class CharacterJSON
{
    private String name, gender, eye_color, skin_color, hair_color, height, homeworld;

    public CharacterJSON ()
    {

    }

    public CharacterJSON(String name, String gender, String eye_color, String skin_color, String hair_color, String height, String homeworld)
    {
        this.name = name;
        this.gender = gender;
        this.eye_color = eye_color;
        this.skin_color = skin_color;
        this.hair_color = hair_color;
        this.height = height;
        this.homeworld = homeworld;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getHeight() {
        return height;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    @Override
    public String toString() {
        return "personage{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", eye_color='" + eye_color + '\'' +
                ", skin_color='" + skin_color + '\'' +
                ", hair_color='" + hair_color + '\'' +
                ", height='" + height + '\'' +
                ", homeworld='" + homeworld + '\'' +
                '}';
    }
}
