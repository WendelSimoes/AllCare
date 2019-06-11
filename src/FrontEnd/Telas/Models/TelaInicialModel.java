package FrontEnd.Telas.Models;

import allcare.Medico;
import allcare.Paciente;
import allcare.Usuario;

public class TelaInicialModel {
    
    private Usuario user;
    
    //Fazer paciente com nome e senha
    public Usuario getPaciente(String user, String pass){
        this.user = new Paciente();
        this.user.setId_user(user);
        this.user.setSenha(pass);
        return this.user;
    }
    
    //Fazer medico com nome e senha
    public Usuario getMedico(String user, String pass){
        this.user = new Medico();
        this.user.setId_user(user);
        this.user.setSenha(pass);
        return this.user;
    }

}
