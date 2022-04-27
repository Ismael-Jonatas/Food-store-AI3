package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.STRATEGY.*;
import br.edu.ifpb.foodstore.service.log.LogHandler;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private Pagamento tipoPagamento;

    public PaymentService(Pagamento tipoDePagamento){
        this.tipoPagamento = tipoDePagamento;
    }

    public void doPayment() throws Exception{
        try{
            tipoPagamento.pagar();
        } catch (Exception e){
            throw new Exception("unknow pyment method");
        }

    }

    public void setTipoPagamento(Pagamento tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }

}
