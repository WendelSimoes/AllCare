package FrontEnd.Telas.Models;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import allcare.Doenca;
import allcare.Sintoma;
import java.util.ArrayList;

public class TelaPacienteModel {
    
    private ArrayList<Doenca> todasDoencas;
    private ArrayList<Sintoma> todosSintomas;
    private ArrayList<Sintoma> sintomas_escolhidos;
    private ArrayList<Doenca> doencas;
    
    public TelaPacienteModel(){
        todasDoencas = new ArrayList<Doenca>();
        todosSintomas = new ArrayList<Sintoma>();
        sintomas_escolhidos = new ArrayList<Sintoma>();
        doencas = new ArrayList<Doenca>();
        
        todasDoencas = Banco_de_Valores.getAllDoencas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
    }

    public ArrayList<Doenca> getTodasDoencas() {
        return todasDoencas;
    }

    public void setTodasDoencas(ArrayList<Doenca> todasDoencas) {
        this.todasDoencas = todasDoencas;
    }

    public ArrayList<Sintoma> getTodosSintomas() {
        return todosSintomas;
    }

    public void setTodosSintomas(ArrayList<Sintoma> todosSintomas) {
        this.todosSintomas = todosSintomas;
    }

    public ArrayList<Sintoma> getSintomas_escolhidos() {
        return sintomas_escolhidos;
    }

    public void setSintomas_escolhidos(ArrayList<Sintoma> sintomas_escolhidos) {
        this.sintomas_escolhidos = sintomas_escolhidos;
    }

    public ArrayList<Doenca> getDoencas() {
        return doencas;
    }

    public void setDoencas(ArrayList<Doenca> doencas) {
        this.doencas = doencas;
    }
    
}
