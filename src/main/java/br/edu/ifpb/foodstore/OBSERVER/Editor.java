package br.edu.ifpb.foodstore.OBSERVER;

import br.edu.ifpb.foodstore.domain.Customer;


public class Editor {

    public GerenteDeEventos eventos;
    private String mensagem;

    public Editor(){
        this.eventos = new GerenteDeEventos("sucesso","recusado","cancelado");
    }

    public void aprovar(Customer customer, String mensagem) throws Exception {
        eventos.notify("sucesso", mensagem, customer);
    }


    public void recusar(String mensagem){
        eventos.notify2("recusado", mensagem);
    }

    public void cancelar(Customer customer, String mensagem) throws Exception {
        eventos.notify("cancelado", mensagem, customer);
    }

}
