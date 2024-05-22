package quad.fr.eventdriven.camel.catalogue.declenchement;


import org.springframework.stereotype.Component;
import quad.fr.eventdriven.camel.catalogue.application.Article;
import quad.fr.eventdriven.camel.catalogue.application.Panier;

@Component
public class PanierModifierToPanierEventMapper {

    public Panier panierModifierEventToPanier(PanierModifieEvent panierModifieEvent){
        return Panier.builder()
                .id(panierModifieEvent.id())
                .articles( panierModifieEvent.articles().stream().map(this::articleCommandeToArticleMap).toList())
                .build();
    }

    public Article articleCommandeToArticleMap(ArticleCommande articleCommande){
        return Article.builder()
                .id(articleCommande.id())
                .build();
    }


}
