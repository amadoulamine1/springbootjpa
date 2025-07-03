package sn.openit.foot.error;

public class JoueurAlreadyExistException extends RuntimeException {
    public JoueurAlreadyExistException(String lastName) {
        super("Joueur with last name " + lastName + " already exist.");
    }
}