package FrontEnd.Telas.Models;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import allcare.Doenca;
import allcare.Sintoma;
import java.util.ArrayList;

public class DoencaModel {
    
    //ArrayList de todas as doenças, necessarias para a verificação de já existencia pelo nome
    private ArrayList<Doenca> todas_doencas;
    //ArrayList de todos os sintomas, para caso sejá uma edição de doença, pegar os sintomas antigos
    private ArrayList<Sintoma> todosSintomas;
    //ArrayList de sintomas escolhidos na tela "SintomaToDoenca", o que sobrescreve os sintomas antigos, caso sejá uma edição
    private ArrayList<Sintoma> sintomas_escolhidos;
    //Descrição utilizada na criação da doença, pega na tela de descrição, podendo já estar preenchida caso sejá uma edição
    private String descricao;
    //String para nomear a doença, podendo já estar escrito para edição
    private String doencaSelecionada;
    
    //Construtor para criação de nova doença
    public DoencaModel(){
        descricao = "";
        doencaSelecionada = "";
        todas_doencas = new ArrayList<Doenca>();
        sintomas_escolhidos = new ArrayList<Sintoma>();
        todosSintomas = new ArrayList<Sintoma>();
        todas_doencas = Banco_de_Valores.getAllDoencas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
    }
    
    //Contrutor para edição
    public DoencaModel(String doencaSelecionada){
        descricao = "";
        this.doencaSelecionada = "";
        this.doencaSelecionada = doencaSelecionada;
        todas_doencas = new ArrayList<Doenca>();
        sintomas_escolhidos = new ArrayList<Sintoma>();
        todosSintomas = new ArrayList<Sintoma>();
        todas_doencas = Banco_de_Valores.getAllDoencas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
        
        ArrayList<String> sintomasAntigos = new ArrayList<String>();
        sintomasAntigos = Banco_de_Valores.sintomas_de_doenca_especifica(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas(), doencaSelecionada);
        for(Sintoma sintoma : todosSintomas){
            for(String sintomax : sintomasAntigos){
                if(sintoma.getNome().equalsIgnoreCase(sintomax)){
                    sintomas_escolhidos.add(sintoma);
                }
            }
        }
        
        for(Doenca doencax : todas_doencas){
            if(doencax.getNome().equalsIgnoreCase(doencaSelecionada)){
                descricao = doencax.getDescricao();
            }
        }
    }
    
    public void adicionar(String doencaNome){
        Doenca doenca = new Doenca();
        doenca.setNome(doencaNome);
        doenca.setDescricao(descricao);
        Banco_de_Valores.empurra_Doenca(AllCareApplication.getCON().getDeclaracao_de_comandos(), doenca, sintomas_escolhidos, AllCareApplication.getCON().getResult_consultas());
    }
    
    public void editar(String doencaNovoNome){
        Banco_de_Valores.modifica_Sintomas_e_nome_da_Doenca(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas(), doencaSelecionada, sintomas_escolhidos, doencaNovoNome, descricao);
    }
    
    public boolean verificarExistencia(String nome){
        for(Doenca disease : todas_doencas){
            if(nome.equalsIgnoreCase(disease.getNome()) && !nome.equalsIgnoreCase(doencaSelecionada)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Sintoma> getSintomas_escolhidos() {
        return sintomas_escolhidos;
    }

    public void setSintomas_escolhidos(ArrayList<Sintoma> sintomas_escolhidos) {
        this.sintomas_escolhidos = sintomas_escolhidos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDoencaSelecionada() {
        return doencaSelecionada;
    }

    public void setDoencaSelecionada(String doencaSelecionada) {
        this.doencaSelecionada = doencaSelecionada;
    }

    public ArrayList<Doenca> getTodas_doencas() {
        return todas_doencas;
    }

    public void setTodas_doencas(ArrayList<Doenca> todas_doencas) {
        this.todas_doencas = todas_doencas;
    }

    public ArrayList<Sintoma> getTodosSintomas() {
        return todosSintomas;
    }

    public void setTodosSintomas(ArrayList<Sintoma> todosSintomas) {
        this.todosSintomas = todosSintomas;
    }
    
}
