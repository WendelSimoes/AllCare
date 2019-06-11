package FrontEnd.Telas.Models;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import allcare.Sintoma;
import java.util.ArrayList;

public class AddSintomaModel {
    
    //ArrayList de sintomas já cadastrados, para evitar sintomas repetidos
    private ArrayList<Sintoma> todosSintomas;
    //Variavel para criação de sintomas apartir de string
    private Sintoma sintoma;
    
    public AddSintomaModel(){
        todosSintomas = new ArrayList<Sintoma>();
    }
    
    //Atualizar todos os sintomas no banco 
    public void atualizar(){
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
    } 
    
    //Verificar se o sintoma existe no banco pelo nome
    public boolean existe(String sintoma){
        boolean existe = false;

        for(Sintoma sintomaNoBanco : todosSintomas){
            if(sintoma.equalsIgnoreCase(sintomaNoBanco.getNome())){
                existe = true;
            }
        }
        
        return existe;
    }
    
    //Transformação de string em sintoma
    public Sintoma getSintoma(String nome){
        sintoma = new Sintoma();
        sintoma.setNome(nome);
        return sintoma;
    }
}
