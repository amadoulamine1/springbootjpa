package sn.openit.foot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurToSave;
import sn.openit.foot.Rank;
import sn.openit.foot.data.JoueurEntity;
import sn.openit.foot.error.JoueurAlreadyExistException;
import sn.openit.foot.error.JoueurNotFoundException;
import sn.openit.foot.repository.JoueurRepository;
import sn.openit.foot.util.RankingCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public List<Joueur> getAllJoueurs() {
        return joueurRepository
                .findAll().stream()
                .map(joueur -> new Joueur(joueur.getFirstName(), joueur.getLastName(), joueur.getBirthDate(),
                        new Rank(joueur.getRank(), joueur.getPoints()))).sorted(Comparator.comparing(joueur -> joueur.getRank().getPosition())
                )
                .collect(Collectors.toList());
    }

    public Joueur getByLastName(String lastName) {
        Optional<JoueurEntity> joueur = joueurRepository.findOneByLastName(lastName);

        if (joueur.isEmpty()) {
            throw new JoueurNotFoundException(lastName);
        }
        return new Joueur(
                joueur.get().getFirstName(),
                joueur.get().getLastName(),
                joueur.get().getBirthDate(),
                new Rank(joueur.get().getRank(),
                        joueur.get().getPoints())
        );
    }

    public Joueur create(JoueurToSave joueurToSave) {
      /*  RankingCalculator rankingCalculator = new RankingCalculator(JoueurList.ALL, joueurToSave);
        List<Joueur> joueurs = rankingCalculator.getNewJoueursRanking();
        return joueurs.stream()
                .filter(joueur -> joueur.getLastName().equals(joueurToSave.lastName()))
                .findFirst().get();*/
        Optional<JoueurEntity> joueur = joueurRepository.findOneByLastNameIgnoreCase(joueurToSave.lastName());
        if (joueur.isPresent()) {
            throw new JoueurAlreadyExistException(joueurToSave.lastName());
        }
        JoueurEntity joueurToRegister = new JoueurEntity(joueurToSave.lastName(), joueurToSave.firstName(), joueurToSave.birthDate(), joueurToSave.points(), 999999999);
        JoueurEntity registeredJoueur = joueurRepository.save(joueurToRegister);
        RankingCalculator rankingCalculator = new RankingCalculator(joueurRepository.findAll());
        List<JoueurEntity> newRanking = rankingCalculator.getNewJoueursRanking();
        joueurRepository.saveAll(newRanking);
        return getByLastName(registeredJoueur.getLastName());
    }

    public Joueur update(JoueurToSave joueurToSave) {
        Optional<JoueurEntity> joueurToUpdate = joueurRepository.findOneByLastNameIgnoreCase(joueurToSave.lastName());
        if (joueurToUpdate.isEmpty()) {
            throw new JoueurNotFoundException(joueurToSave.lastName());
        }
        joueurToUpdate.get().setFirstName(joueurToSave.firstName());
        joueurToUpdate.get().setBirthDate(joueurToSave.birthDate());
        joueurToUpdate.get().setPoints(joueurToSave.points());
        JoueurEntity updatedJoueur = joueurRepository.save(joueurToUpdate.get());
        RankingCalculator rankingCalculator = new RankingCalculator(joueurRepository.findAll());
        List<JoueurEntity> newRanking = rankingCalculator.getNewJoueursRanking();
        joueurRepository.saveAll(newRanking);
        return getByLastName(updatedJoueur.getLastName());

    }

    public void delete (String lastName){
        Optional<JoueurEntity> joueurDelete = joueurRepository.findOneByLastNameIgnoreCase(lastName);
        if (joueurDelete.isEmpty()) {
            throw new JoueurNotFoundException(lastName);
        }
        joueurRepository.delete(joueurDelete.get());
        RankingCalculator rankingCalculator = new RankingCalculator(joueurRepository.findAll());
        List<JoueurEntity> newRanking = rankingCalculator.getNewJoueursRanking();
        joueurRepository.saveAll(newRanking);
    }
}
