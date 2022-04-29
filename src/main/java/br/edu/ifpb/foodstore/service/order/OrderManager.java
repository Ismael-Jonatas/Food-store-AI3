package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.OBSERVER.Editor;
import br.edu.ifpb.foodstore.OBSERVER.Ouvinte;
import br.edu.ifpb.foodstore.STATE.EstadosDoPedido;
import br.edu.ifpb.foodstore.STATE.PedidoEnum;
import br.edu.ifpb.foodstore.STRATEGY.Pagamento;
import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager {

    private final PaymentService paymentService;
    private final Ouvinte mailNotification;
    private final LogService logService;
    private EstadosDoPedido estadoAutal;
    private Editor editor = new Editor();

    public void payOrder(Order order, Pagamento paymentType ) {
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        try {
            paymentService.setTipoPagamento(paymentType);
            paymentService.doPayment();
            order.setStatus(Order.OrderStatus.PAYMENT_SUCCESS);
            editor.eventos.incresver("sucesso", mailNotification);
            editor.aprovar(order.getCustomer(), String.format("Order %d completed successfully", order.getId()));
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
            editor.eventos.incresver("recusado", mailNotification);
            editor.recusar(String.format("Order %d refused", order.getId()));
        }
    }

    public void cancelOrder(Order order) throws Exception {

        String status = order.getStatus().toString();
        estadoAutal = PedidoEnum.valueOf(status);
        PedidoEnum pedidoCancelado = (PedidoEnum) estadoAutal.cancelar(logService);
        estadoAutal = pedidoCancelado;
        status = pedidoCancelado.toString();
        order.setStatus(Order.OrderStatus.valueOf(status));
        editor.eventos.incresver("cancelado", mailNotification);
        editor.cancelar(order.getCustomer(), String.format("Order %d canceled", order.getId()));
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
