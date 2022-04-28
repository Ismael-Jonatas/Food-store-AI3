package br.edu.ifpb.foodstore.STATE;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.order.OrderException;

public interface EstadosDoPedido {
    EstadosDoPedido cancelar(LogService logService) throws OrderException;
    /*EstadosDoPedido inProgress(LogService logService);
    EstadosDoPedido paymentRefused(LogService logService);
    EstadosDoPedido paymentSucess(LogService logService);*/
}
