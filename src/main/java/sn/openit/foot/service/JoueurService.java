package sn.openit.foot.service;

import org.springframework.stereotype.Service;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurList;
import sn.openit.foot.error.JoueurNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoueurService {
    public List<Joueur> getAllJoueurs() {
        return JoueurList.ALL.stream()
                .sorted(Comparator.comparing(joueur -> joueur.getRank().getPosition()))
                .collect(Collectors.toList());
    }

    public Joueur getByLastName(String lastName) {
        return JoueurList.ALL.stream()
                .filter(joueur -> joueur.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new JoueurNotFoundException(lastName));
    }
}
