package sn.openit.foot.integration;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurToSave;
import sn.openit.foot.error.JoueurNotFoundException;
import sn.openit.foot.service.JoueurService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JoueurServiceIntegrationTest {
    @Autowired
    private JoueurService joueurService;

    @Test
    public void shouldCreateJoueur() {
        // Given

        JoueurToSave joueurToSave = new JoueurToSave("John", "Doe", LocalDate.of(2000, Month.JANUARY, 1), 10000);
        // When
        joueurService.create(joueurToSave);
        Joueur createdJoueur = joueurService.getByLastName("doe");
        // Then
        Assertions.assertThat(createdJoueur.getFirstName()).isEqualTo("John");
        Assertions.assertThat(createdJoueur.getLastName()).isEqualTo("Doe");
        Assertions.assertThat(createdJoueur.getBirthDate()).isEqualTo(LocalDate.of(2000, Month.JANUARY, 1));
        Assertions.assertThat(createdJoueur.getRank().getPoints()).isEqualTo(10000);
        Assertions.assertThat(createdJoueur.getRank().getPosition()).isEqualTo(1);
    }

    @Test
    public void shouldUpdateJoueur() { // Given
        JoueurToSave joueurToSave = new JoueurToSave( "Rafael", "NadalTest", LocalDate.of(1986, Month.JUNE, 3), 1000 );
        // When
        joueurService.update(joueurToSave);
        Joueur updatedJoueur = joueurService.getByLastName("NadalTest");
        // Then
        Assertions.assertThat(updatedJoueur.getRank().getPosition()).isEqualTo(3);
    }

    @Test
    public void shouldDeleteJoueur() {
        // Given
        String joueurToDelete = "DjokovicTest";
        // When
        joueurService.delete(joueurToDelete); List<Joueur> allJoueurs = joueurService.getAllJoueurs();
        // Then
        Assertions.assertThat(allJoueurs) .extracting("lastName", "rank.position") .containsExactly(Tuple.tuple("NadalTest", 1), Tuple.tuple("FedererTest", 2));
    }

    @Test
    public void shouldFailToDeleteJoueur_WhenJoueurDoesNotExist() {
        // Given
        String joueurToDelete = "DoeTest";
        // When / Then
        Exception exception = assertThrows(JoueurNotFoundException.class, () -> {
            joueurService.delete(joueurToDelete); }
        );
        Assertions.assertThat(exception.getMessage()).isEqualTo("Joueur with last name DoeTest could not be found.");
    }
}
