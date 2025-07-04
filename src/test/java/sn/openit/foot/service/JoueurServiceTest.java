package sn.openit.foot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurEntityList;
import sn.openit.foot.error.JoueurNotFoundException;
import sn.openit.foot.repository.JoueurRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JoueurServiceTest {
    @Mock
    private JoueurRepository joueurRepository;
    private JoueurService joueurService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        joueurService = new JoueurService(joueurRepository);
    }

    @Test
    public void shouldReturnJoueursRanking() {
        // Given
        Mockito.when(joueurRepository.findAll()).thenReturn(JoueurEntityList.ALL);
        // When
        List<Joueur> allJoueurs = joueurService.getAllJoueurs();
        // Then
        Assertions.assertThat(allJoueurs) .extracting("lastName") .containsExactly("Nadal", "Djokovic", "Federer", "Murray");
    }

    @Test
    public void shouldRetrieveJoueur() {
        // Given
        String joueurToRetrieve = "nadal"; Mockito.when(joueurRepository.findOneByLastNameIgnoreCase(joueurToRetrieve)).thenReturn(Optional.of(JoueurEntityList.RAFAEL_NADAL));
        // When
        Joueur retrievedJoueur = joueurService.getByLastName(joueurToRetrieve);
        // Then
        Assertions.assertThat(retrievedJoueur.getLastName()).isEqualTo("Nadal");
    }

    @Test
    public void shouldFailToRetrieveJoueur_WhenJoueurDoesNotExist() {
        // Given
        String unknownJoueur = "doe";
        Mockito.when(joueurRepository.findOneByLastNameIgnoreCase(unknownJoueur)).thenReturn(Optional.empty());
        // When / Then
        Exception exception = assertThrows(JoueurNotFoundException.class, () -> {
            joueurService.getByLastName(unknownJoueur);
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("Joueur with last name doe could not be found.");
    }
}
