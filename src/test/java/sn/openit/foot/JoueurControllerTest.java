package sn.openit.foot;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sn.openit.foot.error.JoueurNotFoundException;
import sn.openit.foot.rest.JoueurController;
import sn.openit.foot.service.JoueurService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JoueurController.class)
public class JoueurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JoueurService joueurService;

    @Test
    public void shouldListAllJoueurs() throws Exception {
        // Given
        // Mock the service to return a list of joueurs
        Mockito.when(joueurService.getAllJoueurs()).thenReturn(JoueurList.ALL);

        // When
        mockMvc.perform(get("/joueurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3))) // Assuming there are 3 joueurs in the mocked service
                .andExpect(jsonPath("$[0].lastName", CoreMatchers.is("Nadal")))
                .andExpect(jsonPath("$[1].lastName", CoreMatchers.is("Djokovic")))
                .andExpect(jsonPath("$[2].lastName", CoreMatchers.is("Federer")))
                .andExpect(jsonPath("$[3].lastName", CoreMatchers.is("Murray")));

        // Then
        // Verify that the service was called
    }

    @Test
    public void shouldRetrieveJoueur() throws Exception {
        // Given
        String joueurToRetrieve = "nadal";
        Mockito.when(joueurService.getByLastName(joueurToRetrieve)).thenReturn(JoueurList.RAFAEL_NADAL);
        // When / Then
        mockMvc.perform(get("/joueurs/nadal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", CoreMatchers.is("Nadal")))
                .andExpect(jsonPath("$.rank.position", CoreMatchers.is(1)));
    }

    @Test
    public void shouldReturn404NotFound_WhenJoueurDoesNotExist() throws Exception {
        // Given
        String joueurToRetrieve = "doe";
        Mockito.when(joueurService.getByLastName(joueurToRetrieve)).thenThrow(new JoueurNotFoundException("Joueur doe does not exist"));
        // When / Then
        mockMvc.perform(get("/joueurs/doe"))
                .andExpect(status().isNotFound());
    }
}
