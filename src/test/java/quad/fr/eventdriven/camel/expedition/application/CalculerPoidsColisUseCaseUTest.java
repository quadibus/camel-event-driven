package quad.fr.eventdriven.camel.expedition.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculerPoidsColisUseCaseUTest {

    @Mock
    private LivrableDataPort livrableDataPort;

    @InjectMocks
    private CalculerPoidsCommandeUseCase calculerPoidsCommandeUseCase;

    @Test
    public void calculerPoids_commande_un_seul_article_retourne_poids_article() {
        //Given
        Long idArticle = 123456L;
        Livrable unPCLivrable = Livrable.builder()
                .poids(3.4F)
                .id(idArticle)
                .name("Workstation")
                .build();

        when(livrableDataPort.getByProductId(any())).thenReturn(Optional.of(unPCLivrable));

        //When
        Livrable unProduit = Livrable.builder()
                .id(idArticle).build();

        Colis uneColis = Colis.builder()
                .livrables(List.of(unProduit))
                .build();
        Float poidsColis = calculerPoidsCommandeUseCase.executer(uneColis);

        //Then
        assertThat(poidsColis,is(3.4F));
    }

    @Test
    public void calculerPoids_commande_deux_articles_retourne_poids_total() {
        //Given
        Long idpc = 123456L;
        Livrable unPCLivrable = Livrable.builder()
                .poids(3.4F)
                .id(idpc)
                .name("Workstation")
                .build();

        Long idfrigo = 12345L;
        Livrable unFrigo = Livrable.builder()
                .poids(50F)
                .id(idfrigo)
                .name("frigo")
                .build();

        when(livrableDataPort.getByProductId(idpc)).thenReturn(Optional.of(unPCLivrable));
        when(livrableDataPort.getByProductId(idfrigo)).thenReturn(Optional.of(unFrigo));


        //When
        Livrable unProduitPC = Livrable.builder()
                .id(idpc).build();

        Livrable unProduitFrigo = Livrable.builder()
                .id(idfrigo).build();

        Colis uneColis = Colis.builder()
                .livrables(List.of(unProduitPC,unProduitFrigo))
                .build();
        Float poidsColis = calculerPoidsCommandeUseCase.executer(uneColis);

        //Then
        assertThat(poidsColis,is(53.4F));
    }


}