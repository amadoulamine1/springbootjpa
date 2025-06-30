package sn.openit.foot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class Joueur {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @PastOrPresent
    private LocalDate birthDate;
    @Valid
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
