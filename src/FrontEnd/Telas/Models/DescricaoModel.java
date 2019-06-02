package FrontEnd.Telas.Models;

import FrontEnd.AllCareApplication;

public class DescricaoModel {
    
    private String descricao;
    
    public DescricaoModel(){
        descricao = "";
        descricao = AllCareApplication.getDoencaController().getDoencaModel().getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    public void concluir(String descricao) {
        this.descricao = descricao;
        AllCareApplication.getDoencaController().getDoencaModel().setDescricao(this.descricao);
    }
    
}
