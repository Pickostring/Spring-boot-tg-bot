package home.picko.springdemobot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "camerasDatabase")
public class Camera {

    @Id
    private String auditory;

    private String adress;

    public String getAuditory() {
        return auditory;
    }

    public void setAuditory(String auditory) {
        this.auditory = auditory;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Аудитория = " + auditory +
                ", Адрес = " + adress;
    }
}
