package quad.fr.eventdriven.camel.panier.declenchement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import quad.fr.eventdriven.camel.panier.persistance.PanierDatabaseRepository;
import quad.fr.eventdriven.camel.panier.persistance.PanierEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PanierControllerITest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PanierDatabaseRepository panierDatabaseRepository;


    @Test
    public void ajouter_article_panier_inexistant_retourne_nouveau_panier_avec_article() throws Exception {
        MvcResult resultat = mockMvc.perform(
                post("/panier?idpanier=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\":80}")

        ) .andExpect(status().isOk())
                .andReturn();
        assertThat(resultat.getResponse().getStatus(),is(200));
        assertThat(resultat.getResponse().getContentAsString(),containsStringIgnoringCase("80"));
    }

    @Test
    public void ajouter_article_sur_panier_existant_retourne_panier_avec_article_ajoute() throws Exception {
        PanierEntity panierBase = PanierEntity.builder()
                .idCommun(1234L)
                .articles(List.of(80L))
                .build();
        PanierEntity panierSaved = panierDatabaseRepository.save(panierBase);


        MvcResult resultat = mockMvc.perform(
                        post("/panier?idpanier="+panierSaved.getIdCommun())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"id\":901}")

                ) .andExpect(status().isOk())
                .andReturn();
        assertThat(resultat.getResponse().getStatus(),is(200));
        assertThat(resultat.getResponse().getContentAsString(),containsStringIgnoringCase("901"));
    }
}