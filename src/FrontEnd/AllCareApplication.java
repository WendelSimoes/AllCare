package FrontEnd;

import DataBase.ConectionFactory;
import FrontEnd.Telas.Controllers.DoencaController;
import FrontEnd.Telas.Controllers.AddSintomaController;
import FrontEnd.Telas.Controllers.ApagarSintomasController;
import FrontEnd.Telas.Controllers.DescricaoController;
import FrontEnd.Telas.Controllers.SintomaToDoencaController;
import FrontEnd.Telas.Controllers.TelaDoutorController;
import FrontEnd.Telas.Controllers.TelaInicialController;
import FrontEnd.Telas.Controllers.TelaPacienteController;
import FrontEnd.Telas.Views.OhayoGoodByeView;
import javafx.application.Application;
import javafx.stage.Stage;

/*
Classe destinada à instancia e disponibilidade estatica de todos os crontrolers
para qualquer outra classe, permitindo-as obter e exibir qualquer tela
*/
public class AllCareApplication extends Application {
    
    private final static ConectionFactory CON = new ConectionFactory();;
    private static boolean conected = true;
    
    private static TelaInicialController telaInicialController;

    private static TelaDoutorController telaDoutorController;
    private static AddSintomaController addSintomaController;
    private static ApagarSintomasController apagarSintomaController;
    private static DoencaController doencaController;
    private static SintomaToDoencaController sintomaToDoencaController;
    private static DescricaoController descricaoController;
    private static TelaPacienteController telaPacienteController;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AllCareApplication.conectar();
        
        telaInicialController = new TelaInicialController();
        
        telaDoutorController = new TelaDoutorController();
        addSintomaController = new AddSintomaController();
        apagarSintomaController = new ApagarSintomasController();
        doencaController = new DoencaController();
        sintomaToDoencaController = new SintomaToDoencaController();
        descricaoController = new DescricaoController();
        telaPacienteController = new TelaPacienteController();
        
        telaInicialController.build(primaryStage);
        OhayoGoodByeView.display(true);
        telaInicialController.show();
    }
    
    //Conecção com o banco de dados
    public static void conectar(){
        if(CON.conectar()){
            System.out.println("A conexão foi estabelecida com success");
        }else{
            System.out.println("A conexão não foi estabelecida :/");
            conected = false;
        }
    }

    public static ConectionFactory getCON() {
        return CON;
    }
    
    public static boolean isConected(){
        return conected;
    }

    public static TelaInicialController getTelaInicialController() {
        return telaInicialController;
    }

    public static TelaDoutorController getTelaDoutorController() {
        return telaDoutorController;
    }

    public static AddSintomaController getAddSintomaController() {
        return addSintomaController;
    }

    public static ApagarSintomasController getApagarSintomaController() {
        return apagarSintomaController;
    }

    public static DoencaController getDoencaController() {
        return doencaController;
    }

    public static SintomaToDoencaController getSintomaToDoencaController() {
        return sintomaToDoencaController;
    }

    public static DescricaoController getDescricaoController() {
        return descricaoController;
    }

    public static TelaPacienteController getTelaPacienteController() {
        return telaPacienteController;
    }
    
}
