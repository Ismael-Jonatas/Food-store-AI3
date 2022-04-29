package br.edu.ifpb.foodstore.OBSERVER;

import br.edu.ifpb.foodstore.domain.Customer;

public interface Ouvinte {

    void sendMailNotificationToCustomer(String message, Customer customer);
    void sendMailNotificationToAdmin(String message);

}
