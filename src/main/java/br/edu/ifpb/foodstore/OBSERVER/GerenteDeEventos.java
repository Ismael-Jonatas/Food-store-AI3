package br.edu.ifpb.foodstore.OBSERVER;

import br.edu.ifpb.foodstore.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenteDeEventos {

    Map<String, List<Ouvinte>> ouvintes= new HashMap<>();

    public GerenteDeEventos(String... operacoes){
        for (String operacao : operacoes){
            this.ouvintes.put(operacao, new ArrayList<>());
        }
    }

    public void incresver(String tipoDeEvento, Ouvinte ouvinte){
        List<Ouvinte> ouvintesAux = ouvintes.get(tipoDeEvento);
        ouvintesAux.add(ouvinte);
    }

    public void cancelarIncricao(String tipoDeEvento, Ouvinte ouvinte){
        List<Ouvinte> ouvintesAux = ouvintes.get(tipoDeEvento);
        ouvintesAux.remove(ouvinte);
    }

    public void notify(String tipoDeEvento, String mensagem, Customer customer){
        List<Ouvinte> ouvintesAux = ouvintes.get(tipoDeEvento);
        for(Ouvinte ouvinte: ouvintesAux){
            ouvinte.sendMailNotificationToCustomer(mensagem, customer);
            ouvinte.sendMailNotificationToAdmin(mensagem);
        }
    }


    public void notify2(String tipoDeEvento, String mensagem){
        List<Ouvinte> ouvintesAux = ouvintes.get(tipoDeEvento);
        for(Ouvinte ouvinte: ouvintesAux){
            ouvinte.sendMailNotificationToAdmin(mensagem);
        }
    }

}
