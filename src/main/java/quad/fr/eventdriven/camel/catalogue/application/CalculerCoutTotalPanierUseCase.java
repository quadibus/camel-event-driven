package quad.fr.eventdriven.camel.catalogue.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CalculerCoutTotalPanierUseCase {

    ArticleDataPort articleDataPort;

   public Float executer( Panier panier ){
       return panier.articles().stream()
               .map(article -> articleDataPort.findArticleByIdCommun(article.id()))
               .map(Optional::orElseThrow)
               .map(Article::prix).reduce(0.0F,Float::sum);
   }

}
