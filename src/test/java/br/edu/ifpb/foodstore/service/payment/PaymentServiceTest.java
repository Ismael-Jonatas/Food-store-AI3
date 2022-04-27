package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.STRATEGY.PagarNoBoleto;
import br.edu.ifpb.foodstore.STRATEGY.PagarNoCredito;
import br.edu.ifpb.foodstore.STRATEGY.PagarNoDebito;
import br.edu.ifpb.foodstore.STRATEGY.PagarPeloPaypal;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class PaymentServiceTest {

    @SpyBean
    private PaymentService paymentService;

    @MockBean
    private LogService logService;

    @SneakyThrows
    @Test
    void doPaymentTest() {
        PagarNoCredito credito = new PagarNoCredito(logService);
        paymentService.setTipoPagamento(credito);
        paymentService.doPayment();
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do credit card payment!");

        PagarNoDebito debito = new PagarNoDebito(logService);
        paymentService.setTipoPagamento(debito);
        paymentService.doPayment();
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do debit payment!");

        PagarPeloPaypal paypal = new PagarPeloPaypal(logService);
        paymentService.setTipoPagamento(paypal);
        paymentService.doPayment();
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do paypal payment!");

        PagarNoBoleto boleto = new PagarNoBoleto(logService);
        paymentService.setTipoPagamento(boleto);
        paymentService.doPayment();
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do billet payment!");

    }

}
