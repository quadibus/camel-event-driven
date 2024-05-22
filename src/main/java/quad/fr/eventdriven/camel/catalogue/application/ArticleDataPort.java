package quad.fr.eventdriven.camel.catalogue.application;

import java.util.Optional;

public interface ArticleDataPort {

    public Optional<Article> findArticleByIdCommun(Long id)  ;
    public void save(Article article);

}
