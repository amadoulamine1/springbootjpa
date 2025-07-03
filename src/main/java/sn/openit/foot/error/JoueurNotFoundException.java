package sn.openit.foot.error;

public class JoueurNotFoundException extends RuntimeException {
    public JoueurNotFoundException(String lastName) {
        super("Joueur with last name " + lastName + " could not be found.");
    }
}