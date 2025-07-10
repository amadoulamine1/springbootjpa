package sn.openit.foot.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurToSave;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JoueurControllerEndToEndTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateJoueur() {
        // Given
        JoueurToSave joueurToCreate = new JoueurToSave(
                "Carlos",
                "Alcaraz",
                LocalDate.of(2003, Month.MAY, 5),
                4500);
        // When
        String url = "http://localhost:" + port + "/joueurs";
        HttpEntity<JoueurToSave> request = new HttpEntity<>(joueurToCreate);
        ResponseEntity<Joueur> joueurResponseEntity = this.restTemplate.postForEntity(url, request, Joueur.class);
        // Then
        Assertions.assertThat(joueurResponseEntity.getBody().getLastName()).isEqualTo("Alcaraz");
        Assertions.assertThat(joueurResponseEntity.getBody().getRank().getPosition()).isEqualTo(2);
    }

    @Test
    public void shouldFailToCreateJoueur_WhenJoueurToCreateIsInvalid() {
        // Given
        JoueurToSave joueurToCreate = new JoueurToSave(
                "Carlos",
                null,
                LocalDate.of(2003, Month.MAY, 5),
                4500 );
        // When
        String url = "http://localhost:" + port + "/joueurs";
        HttpEntity<JoueurToSave> request = new HttpEntity<>(joueurToCreate);
        ResponseEntity<Joueur> joueurResponseEntity = this.restTemplate.exchange(url, HttpMethod.POST, request, Joueur.class);
        // Then
        Assertions.assertThat(joueurResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldUpdateJoueurRanking() {
        // Given
        JoueurToSave joueurToUpdate = new JoueurToSave(
                "Rafael",
                "NadalTest",
                LocalDate.of(1986, Month.JUNE, 3),
                1000 );
        // When
        String url = "http://localhost:" + port + "/joueurs";
        HttpEntity<JoueurToSave> request = new HttpEntity<>(joueurToUpdate);
        ResponseEntity<Joueur> joueurResponseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, request, Joueur.class);
        // Then
        Assertions.assertThat(joueurResponseEntity.getBody().getLastName()).isEqualTo("NadalTest");
        Assertions.assertThat(joueurResponseEntity.getBody().getRank().getPosition()).isEqualTo(3);
    }

    @Test
    public void shouldDeleteJoueur() {
        // Given / When
        String url = "http://localhost:" + port + "/joueurs";
        this.restTemplate.exchange(url + "/djokovictest", HttpMethod.DELETE, null, Joueur.class);
        HttpEntity<List<Joueur>> allJoueursResponseEntity = this.restTemplate.exchange( url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Joueur>>() {} );
        // Then
        Assertions.assertThat(allJoueursResponseEntity.getBody())
                .extracting("lastName", "rank.position")
                .containsExactly(tuple("NadalTest", 1), tuple("FedererTest", 2));
    }


}

