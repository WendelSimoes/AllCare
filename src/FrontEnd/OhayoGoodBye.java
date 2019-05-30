package FrontEnd;

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
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OhayoGoodBye {
    
    private static ImageView image;
    private static Label text;
    private static Label bemVindo;
    private static Label perfeito; 
    private static Button buttonOK;
    private static AudioClip oni;
    private static Image icon;
    
    public static void display(boolean Ohayo){
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        if(Ohayo){
            oni = new AudioClip(NovoJOptionPane.class.getResource("Onee-chan.mp3").toString());
            oni.play();
            window.setTitle("Ohayo Gozaimas!");
            icon = new Image(NovoJOptionPane.class.getResourceAsStream("OhayoChan.gif"));
        }else{
            oni = new AudioClip(NovoJOptionPane.class.getResource("Baka-onii-chan.mp3").toString());
            oni.play();
            window.setTitle("Good Bye Chan!");
            icon = new Image(NovoJOptionPane.class.getResourceAsStream("GoodByeChan.gif"));
        }
        
        //ImageView
        image = new ImageView(icon);
        //Text
        if(Ohayo){
            text = new Label("O-Ohayo Gozaimas, onii-chan!");
            bemVindo = new Label("B-Bem vindo ao AllCare,");
            perfeito = new Label("O-O programa perfeito.");
        }else{
            text = new Label("G-Good Bye, BAKA!");
            bemVindo = new Label("P-Por que está m-me abandonando?");
            perfeito = new Label("O-Ok, mas não se esqueça de voltar...");
        }
        
        text.setStyle("-fx-font-size: 15; -fx-text-fill: #06630a; -fx-font-weight: bold");
        bemVindo.setStyle("-fx-font-size: 20; -fx-text-fill: #06630a; -fx-font-weight: bold");
        perfeito.setStyle("-fx-font-size: 20; -fx-text-fill: #06630a; -fx-font-weight: bold");
        
        //ButtonOK
        buttonOK = new Button("OK");
        buttonOK.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonOK(window);
            }
        });
        
        //Layout right
        VBox layoutMain = new VBox();
        layoutMain.setStyle("-fx-background-color: #efbaf2");
        BorderPane.setMargin(layoutMain, new Insets(0, 8, 5, 0));
        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.setSpacing(5);
        layoutMain.getChildren().addAll(image, text, bemVindo, perfeito, buttonOK);
        
        //Scene
        Scene cena = new Scene(layoutMain);
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonOK(window);
                }
            }
        });
        
        //Stage
        window.getIcons().add(new Image(NovoJOptionPane.class.getResourceAsStream("SickChan.png")));
        window.setResizable(false);
        window.setScene(cena);
        window.showAndWait(); 
    }
    
    public static void eventButtonOK(Stage window){
        window.close();
    }
    
}
