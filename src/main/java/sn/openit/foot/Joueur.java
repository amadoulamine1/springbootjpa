package sn.openit.foot;

import java.time.LocalDate;

public class Joueur {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Rank rank;

    public Joueur(String firstName, String lastName, LocalDate birthDate, Rank rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.rank = rank;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
