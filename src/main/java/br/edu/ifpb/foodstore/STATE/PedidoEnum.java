package br.edu.ifpb.foodstore.STATE;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.order.OrderException;

public enum PedidoEnum implements EstadosDoPedido{

    CANCELED{
        @Override
        public EstadosDoPedido cancelar(LogService logService) throws OrderException {
            throw new OrderException("Order already canceled!");
        }
    },
    IN_PROGRESS{
        @Override
        public EstadosDoPedido cancelar(LogService logService) {
            logService.info("Canceling in progress order");
            return CANCELED;
        }
    },
    PAYMENT_REFUSED{
        @Override
        public EstadosDoPedido cancelar(LogService logService) {
            logService.info("Canceling refused order");
            return CANCELED;
        }
    },
    PAYMENT_SUCCESS{
        @Override
        public EstadosDoPedido cancelar(LogService logService) {
            logService.info("Canceling already paid order");
            return CANCELED;
        }
    }
}


