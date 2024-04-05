package fr.univamu.iut.apimenus;

import jakarta.json.bind.annotation.JsonbCreator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Menu {
    protected int menuId;

    protected String name;

    protected List<Plat>plats;
    protected String creatorName;
    protected String creationDate;

    @JsonbCreator
    public Menu(String name, List<Plat> plats, String creatorName) {
        this.name = name;
        this.plats = plats;
        this.creatorName = creatorName;
    }

    public Menu(int menuId, String name, List<Plat>plats, String creatorName, String creationDate) {
        this.menuId = menuId;
        this.name = name;
        this.plats = plats;
        this.creatorName = creatorName;
        this.creationDate = creationDate;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getCreationDate() {
        if (creationDate == null) {
            setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return creationDate;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }


    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "id=" + menuId +
                ", name='" + name + '\'' +
                ", plats=" + plats + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
