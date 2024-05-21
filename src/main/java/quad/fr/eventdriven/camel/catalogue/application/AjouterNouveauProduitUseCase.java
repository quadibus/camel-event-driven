package quad.fr.eventdriven.camel.catalogue.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AjouterNouveauProduitUseCase {

    ArticleDataPort articleDataPort;


    public void executer(Article article) {
        articleDataPort.save(article);
        log.info("article saved");
    }

}
