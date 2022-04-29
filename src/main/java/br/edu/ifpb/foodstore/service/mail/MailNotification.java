package br.edu.ifpb.foodstore.service.mail;

import br.edu.ifpb.foodstore.OBSERVER.Ouvinte;
import br.edu.ifpb.foodstore.domain.Customer;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailNotification implements Ouvinte {

    private final LogService logService;

    private String emailAdministration = "contact@food-store.com";

    @Override
    public void sendMailNotificationToCustomer(String mensagem, Customer customer) {
        logService.info("send mail notification to "+ customer.getEmail());
        logService.debug(mensagem);
    }

    @Override
    public void sendMailNotificationToAdmin(String mensagem) {
        logService.info("send mail notification to "+emailAdministration);
        logService.debug(mensagem);
    }

}
