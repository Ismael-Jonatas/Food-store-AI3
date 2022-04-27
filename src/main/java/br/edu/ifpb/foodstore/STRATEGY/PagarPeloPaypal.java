package br.edu.ifpb.foodstore.STRATEGY;

import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PagarPeloPaypal implements Pagamento {

    private LogService logService;

    @Override
    public void pagar() {
        logService.info("Do paypal payment!");
    }

}
