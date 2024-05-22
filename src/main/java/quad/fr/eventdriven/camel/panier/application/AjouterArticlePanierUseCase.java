package quad.fr.eventdriven.camel.panier.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@AllArgsConstructor
public class AjouterArticlePanierUseCase {

    PanierDataPort panierDataPort;
    PanierEventPort panierEventPort;

    public Optional<Panier> executer(Optional<Long> id, Article article) {

        Optional<Panier> panierExistant = panierDataPort.findPanierById(id.get());

        panierExistant= Optional.of(
                panierExistant.orElseGet(() -> (Panier.builder()
                .id(new Random().longs(1).findFirst().getAsLong())
                .articles(new ArrayList<>())
                .build()

        )));   //Moche

        return panierExistant.map(panier ->
        {
            List<Article> newArticles = new ArrayList<>(panier.articles());
            newArticles.add(article);
            panier = Panier.builder()
                    .id(panier.id())
                    .articles(newArticles)
                    .build();
            panierEventPort.notifierModificationPanier(panier);
            return panierDataPort.savePanier(panier);
        });
    }


}
