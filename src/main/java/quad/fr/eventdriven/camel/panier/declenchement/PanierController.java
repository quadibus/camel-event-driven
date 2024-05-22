package quad.fr.eventdriven.camel.panier.declenchement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import quad.fr.eventdriven.camel.panier.application.AjouterArticlePanierUseCase;
import quad.fr.eventdriven.camel.panier.application.Article;
import quad.fr.eventdriven.camel.panier.application.Panier;

import java.util.Optional;

@RequestMapping("/panier")
@AllArgsConstructor
@RestController
public class PanierController {

    AjouterArticlePanierUseCase ajouterArticlePanierUseCase;

    @PostMapping
    @ResponseBody
    public Panier ajouterArticleDansPanier(
            @RequestBody( required = true)Article article,
            @RequestParam(name = "idpanier",required = false) Long id){
       return ajouterArticlePanierUseCase.executer(Optional.of(id),article).orElseThrow();
    }

}
