package quad.fr.eventdriven.camel.expedition.application;

import java.util.Optional;

public interface LivrableDataPort {
    public Optional<Livrable> getByProductId(Long id);
}
