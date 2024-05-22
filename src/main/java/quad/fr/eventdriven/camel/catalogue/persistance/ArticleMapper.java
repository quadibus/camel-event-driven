package quad.fr.eventdriven.camel.catalogue.persistance;

import quad.fr.eventdriven.camel.catalogue.application.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {


    public Article mapArticleEntityToArticle(ArticleEntity articleEntity) {
        return Article.builder()
                .id(articleEntity.getIdArticle())
                .prix(articleEntity.getPrix())
                .build();
    }

    public ArticleEntity mapArticleToArticleEntity(Article article){
        return ArticleEntity.builder()
                .idArticle(article.id())
                .prix(article.prix())
                .build();
    }

}