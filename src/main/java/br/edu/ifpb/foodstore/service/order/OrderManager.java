package br.edu.ifpb.foodstore.service.order;

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
    private final MailNotification mailNotification;
    private final LogService logService;
    private EstadosDoPedido estadoAutal;

    public void payOrder(Order order, Pagamento paymentType ) {
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        try {
            paymentService.setTipoPagamento(paymentType);
            paymentService.doPayment();
            order.setStatus(Order.OrderStatus.PAYMENT_SUCCESS);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d completed successfully", order.getId()));
            mailNotification.sendMailNotificationToCustomer(String.format("Order %d completed successfully", order.getId()), order.getCustomer());
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d refused", order.getId()));
        }
    }

    public void cancelOrder(Order order) throws OrderException {

        String status = order.getStatus().toString();
        estadoAutal = PedidoEnum.valueOf(status);
        PedidoEnum pedidoCancelado = (PedidoEnum) estadoAutal.cancelar(logService);

        status = pedidoCancelado.toString();

        order.setStatus(Order.OrderStatus.valueOf(status));
        mailNotification.sendMailNotificationToAdmin(String.format("Order %d canceled", order.getId()));
        mailNotification.sendMailNotificationToCustomer(String.format("Order %d canceled", order.getId()), order.getCustomer());
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
