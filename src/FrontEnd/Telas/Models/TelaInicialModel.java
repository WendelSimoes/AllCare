package FrontEnd.Telas.Models;

import allcare.Medico;
import allcare.Paciente;
import allcare.Usuario;

public class TelaInicialModel {
    
    private Usuario user;
    
    public Usuario getPaciente(String user, String pass){
        this.user = new Paciente();
        this.user.setId_user(user);
        this.user.setSenha(pass);
        return this.user;
    }
    
    public Usuario getMedico(String user, String pass){
        this.user = new Medico();
        this.user.setId_user(user);
        this.user.setSenha(pass);
        return this.user;
    }

}
