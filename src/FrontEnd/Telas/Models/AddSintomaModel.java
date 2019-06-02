package FrontEnd.Telas.Models;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import allcare.Sintoma;
import java.util.ArrayList;

public class AddSintomaModel {
    
    private ArrayList<Sintoma> todosSintomas;
    private Sintoma sintoma;
    
    public AddSintomaModel(){
        todosSintomas = new ArrayList<Sintoma>();
    }
    
    public void atualizar(){
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
    } 
    
    public boolean existe(String sintoma){
        boolean existe = false;

        for(Sintoma sintomaNoBanco : todosSintomas){
            if(sintoma.equalsIgnoreCase(sintomaNoBanco.getNome())){
                existe = true;
            }
        }
        
        return existe;
    }
    
    public Sintoma getSintoma(String nome){
        sintoma = new Sintoma();
        sintoma.setNome(nome);
        return sintoma;
    }
}
