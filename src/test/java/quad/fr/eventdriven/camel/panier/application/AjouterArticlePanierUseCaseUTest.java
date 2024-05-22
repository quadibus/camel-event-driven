package quad.fr.eventdriven.camel.panier.application;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class AjouterArticlePanierUseCaseUTest {

    @Mock
    private PanierDataPort panierDataPort;

    @InjectMocks
    private AjouterArticlePanierUseCase ajouterArticlePanierUseCase;

    @Test
    public void ajouter_article_panier_inconnu_retourne_nouveau_panier_avec_article(){
        //Given
         when(panierDataPort.savePanier(any())).thenReturn( Panier.builder().id(1L).build());
         when(panierDataPort.findPanierById(any())).thenReturn(Optional.ofNullable(null));
        //When

        Article article = Article.builder()
                .id(80L)
                .build();
        Optional<Panier> panierSaved = ajouterArticlePanierUseCase.executer(Optional.of(1L), article);

        //Then
        assertThat(panierSaved, notNullValue());
    }

}