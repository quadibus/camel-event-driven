package quad.fr.eventdriven.camel.catalogue.persistance;

import quad.fr.eventdriven.camel.catalogue.application.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {


    public Article mapArticleEntityToArticle(ArticleEntity articleEntity) {
        return Article.builder()
                .poids(articleEntity.getPoids())
                .name(articleEntity.getName())
                .id(articleEntity.getIdCommun())
                .build();
    }

    public ArticleEntity mapArticleToArticleEntity(Article article){
        return ArticleEntity.builder()
                .idCommun(article.id())
                .poids(article.poids())
                .name(article.name())
                .build();
    }

}