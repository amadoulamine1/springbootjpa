package sn.openit.foot.util;

import sn.openit.foot.JoueurToSave;
import sn.openit.foot.data.JoueurEntity;

import java.util.ArrayList;
import java.util.List;

public class RankingCalculator {
    private final List<JoueurEntity> currentJoueursRanking;
    // private final JoueurToSave joueurToSave;

  /*  public RankingCalculator(List<Joueur> currentJoueursRanking, JoueurToSave joueurToSave) {
        this.currentJoueursRanking = currentJoueursRanking;
        this.joueurToSave = joueurToSave;
    }*/

    public RankingCalculator(List<JoueurEntity> currentJoueursRanking) {
        this.currentJoueursRanking = currentJoueursRanking;
        //    this.joueurToSave = null;
    }

    public List<JoueurEntity> getNewJoueursRanking() {
        currentJoueursRanking.sort((joueur1, joueur2) -> Integer.compare(joueur2.getPoints(), joueur1.getPoints()));
        List<JoueurEntity> updatedJoueurs = new ArrayList<>();
        for (int i = 0; i < currentJoueursRanking.size(); i++) {
            JoueurEntity updatedJoueur = currentJoueursRanking.get(i);
            updatedJoueur.setRank(i + 1);
            updatedJoueurs.add(updatedJoueur);
        }
        return updatedJoueurs;
    }

    private JoueurEntity getJoueurNewRanking(List<JoueurEntity> existingJoueurs, JoueurToSave joueurToSave) {
        RankingCalculator rankingCalculator = new RankingCalculator(existingJoueurs);
        List<JoueurEntity> joueurs = rankingCalculator.getNewJoueursRanking();
        return joueurs.stream().filter(joueur -> joueur.getLastName().equals(joueurToSave.lastName())).findFirst().get();
    }
}