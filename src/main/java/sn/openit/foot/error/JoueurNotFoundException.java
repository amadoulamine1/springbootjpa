package sn.openit.foot.error;

public class JoueurNotFoundException extends RuntimeException {
    public JoueurNotFoundException(String lastName) {
        super("Joueur with last name " + lastName + " could not be found.");
    }
}
/*public Joueur getByLastName(String lastName) {
    return JoueurList.ALL.stream()
            .filter(joueur -> joueur.lastName().equals(lastName))
            .findFirst()
            .orElseThrow(() -> new JoueurNotFoundException(lastName));
}*/