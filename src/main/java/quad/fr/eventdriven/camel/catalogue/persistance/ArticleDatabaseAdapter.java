package quad.fr.eventdriven.camel.catalogue.persistance;

import quad.fr.eventdriven.camel.catalogue.application.Article;
import quad.fr.eventdriven.camel.catalogue.application.ArticleDataPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class ArticleDatabaseAdapter implements ArticleDataPort {

    private ArticleRepository articleRepository;
    private ArticleMapper articleMapper;

    @Override
    public Optional<Article> findArticleByIdCommun(Long id) {
        return articleRepository.findByIdCommun(id)
                .map(articleMapper::mapArticleEntityToArticle);
    }

    @Override
    public void save(Article article) {

        articleRepository.save(articleMapper.mapArticleToArticleEntity(article));
    }


}
