package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Tela para confirmação de exclusão de doença do banco
public class DesejaMesmoApagarView {
    
    //Stage & scene
    private static Stage window;
    private static Scene cena;
    
    //Layouts
    private static BorderPane layoutMain;
    private static VBox layoutRight;
    private static HBox layoutGambiarra;
    
    //Common nodes
    private static ImageView image;
    private static Label text;
    private static Button buttonSim, buttonNao;
    
    private static boolean result;
    
    public static boolean display(){
        window = new Stage();
        
        //WorkArea
            //ImageView
            image = new ImageView(new Image(DesejaMesmoApagarView.class.getResourceAsStream("/FrontEnd/Midia/TemCertezaChan.png")));
            BorderPane.setMargin(image, new Insets(5, 0, 0, 0));

            //Text
            text = new Label("Deseja mesmo apagar a doença? onii-chan!");
            text.setStyle("-fx-font-size: 15; -fx-text-fill: #000000");

            //ButtonSim
            buttonSim = new Button("Sim");
            buttonSim.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            buttonSim.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    DesejaMesmoApagarView.result = true;
                    window.close();
                }
            });
            
            //ButtonNão
            buttonNao = new Button("Não");
            buttonNao.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            buttonNao.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DesejaMesmoApagarView.result = false;
                    window.close();
                }
            });
            
            //Layout gambiarra
            layoutGambiarra = new HBox();
            layoutGambiarra.setAlignment(Pos.CENTER_RIGHT);
            layoutGambiarra.setSpacing(5);
            layoutGambiarra.getChildren().addAll(buttonSim, buttonNao);
            
            //Layout right
            layoutRight = new VBox();
            BorderPane.setMargin(layoutRight, new Insets(0, 8, 5, 0));
            layoutRight.setAlignment(Pos.BOTTOM_RIGHT);
            layoutRight.setSpacing(5);
            layoutRight.getChildren().addAll(text, layoutGambiarra);
            
            //Layout main
            layoutMain = new BorderPane();
            layoutMain.setLeft(image);
            layoutMain.setRight(layoutRight);
        //
        
        //Scene
        cena = new Scene(layoutMain);
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    DesejaMesmoApagarView.result = true;
                    window.close();
                }
            }
        });
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Tem certeza chan?");
        window.setResizable(false);
        window.setScene(cena);
        window.showAndWait();
        
        return result;
    }
    
}

