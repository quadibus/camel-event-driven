package quad.fr.eventdriven.camel.panier.application;


import java.util.Optional;

public interface PanierDataPort {

    public Panier savePanier(Panier panier);

    public Optional<Panier> findPanierById(Long id);

}
