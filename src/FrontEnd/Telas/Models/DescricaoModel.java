package FrontEnd.Telas.Models;

import FrontEnd.AllCareApplication;

public class DescricaoModel {
    
    //Descrição da doença cromprimido em string 
    private String descricao;
    
    //Construtor que inicia a descrição caso o usuário tenha escolhido uma doença para editar
    //ou caso ele tenha dado uma descrição e na mesma tela de doença volte para alterar
    public DescricaoModel(){
        descricao = "";
        descricao = AllCareApplication.getDoencaController().getDoencaModel().getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    //Atribui a descrição preenchida na view à string descrição, posteriormente
    //atribuido esta descrição à descrição da tela de doença
    public void concluir(String descricao) {
        this.descricao = descricao;
        AllCareApplication.getDoencaController().getDoencaModel().setDescricao(this.descricao);
    }
    
}
