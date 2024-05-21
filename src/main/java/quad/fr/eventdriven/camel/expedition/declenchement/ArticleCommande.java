package quad.fr.eventdriven.camel.expedition.declenchement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record ArticleCommande (Long id ){
}
