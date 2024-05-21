package quad.fr.eventdriven.camel.expedition.application;

import quad.fr.eventdriven.camel.expedition.persistance.LivrableDataBaseRepository;
import quad.fr.eventdriven.camel.expedition.persistance.LivrableEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class CalculerPoidsColisUseCaseITest {


    @Autowired
    private LivrableDataBaseRepository livrableDataBaseRepository;
    @Autowired
    private CalculerPoidsCommandeUseCase calculerPoidsCommandeUseCase;

    @BeforeEach
    public void setUp(){
        LivrableEntity frigo = LivrableEntity.builder()
                .poids(54.2F)
                .name("Frigo")
                .idArticle(1234L)
                .build();
        LivrableEntity pcportable = LivrableEntity.builder()
                .poids(3.6F)
                .name("PcPortable")
                .idArticle(12345678L)
                .build();
        LivrableEntity chaussures = LivrableEntity.builder()
                .poids(1.2F)
                .name("chaussures")
                .idArticle(1245680L)
                .build();
        List<LivrableEntity> livrable = List.of(frigo, pcportable,chaussures);
        livrableDataBaseRepository.saveAllAndFlush(livrable);
    }

    @Test
    @Transactional
    public void calculer_poids_commande_retourne_poids_total_commande(){

        Livrable unFrigo = Livrable.builder()
                .id(1234L)
                .build();

        Livrable pcPortable = Livrable.builder()
                .id(12345678L).build();

        Colis cmde = Colis.builder().livrables(List.of(unFrigo, pcPortable)).build();

        Float poidsCommande = calculerPoidsCommandeUseCase.executer(cmde);

        assertThat(poidsCommande,is(57.8F));
    }


}