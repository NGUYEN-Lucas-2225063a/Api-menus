package fr.univamu.iut.apimenus;

import java.util.Date;

public class Menu {
    protected int id;

    protected String name;
    protected String creatorName;
    protected Date creationDate;
    protected boolean ordered; // Indicateur de commande

    public Menu(int id, String name, String creatorName, Date creationDate) {
        this.id = id;
        this.name = name;
        this.creatorName = creatorName;
        this.creationDate = creationDate;
        this.ordered = false; // Le menu n'a pas encore été commandé lors de sa création
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", creationDate=" + creationDate +
                ", ordered=" + ordered +
                '}';
    }
}
