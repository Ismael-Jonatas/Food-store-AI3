package br.edu.ifpb.foodstore.STRATEGY;

import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PagarNoBoleto implements Pagamento {

    private LogService logService;

    @Override
    public void pagar() {
        logService.info("Do billet payment!");
    }
}
