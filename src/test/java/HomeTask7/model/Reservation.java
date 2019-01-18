package HomeTask7.model;

import java.util.List;

public class Reservation {
    int id;
    String name;
    String surname;
    String  afrom;
    String  ato;
    int bugs;
    String discount;
    int children;
    String flight;
    int adults;
    int seat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAfrom() {
        return afrom;
    }

    public void setAfrom(String afrom) {
        this.afrom = afrom;
    }

    public String getAto() {
        return ato;
    }

    public void setAto(String ato) {
        this.ato = ato;
    }

    public int getBugs() {
        return bugs;
    }

    public void setBugs(int bugs) {
        this.bugs = bugs;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
