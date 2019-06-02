package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OhayoGoodByeView {
    
    //Stage & scene
    private static Stage window;
    private static Scene cena;
    
    //Common nodes
    private static ImageView image;
    private static Label text1, text2, text3;
    private static Button buttonOK;
    private static AudioClip audioClip;
    private static Image icon;
    
    public static void display(boolean ohayo){
        window = new Stage();
        
        if(ohayo){
            window.setTitle("Ohayo Gozaimas!");
            audioClip = new AudioClip(OhayoGoodByeView.class.getResource("/FrontEnd/Midia/Ohayo-onii-chan.mp3").toString());
            icon = new Image(OhayoGoodByeView.class.getResourceAsStream("/FrontEnd/Midia/OhayoChan.gif"));
            text1 = new Label("O-Ohayo Gozaimas, onii-chan!");
            text2 = new Label("B-Bem vindo ao AllCare,");
            text3 = new Label("O-O programa perfeito.");
        }else{
            window.setTitle("Good Bye Chan!");
            audioClip = new AudioClip(OhayoGoodByeView.class.getResource("/FrontEnd/Midia/Baka-onii-chan.mp3").toString());
            icon = new Image(OhayoGoodByeView.class.getResourceAsStream("/FrontEnd/Midia/Baka-chan.gif"));
            text1 = new Label("G-Good Bye, BAKA!");
            text2 = new Label("P-Por que está m-me abandonando?");
            text3 = new Label("O-Ok, mas não se esqueça de voltar...");
        }
        
        //Text style
        text1.setStyle("-fx-font-size: 15; -fx-text-fill: #06630a; -fx-font-weight: bold");
        text2.setStyle("-fx-font-size: 20; -fx-text-fill: #06630a; -fx-font-weight: bold");
        text3.setStyle("-fx-font-size: 20; -fx-text-fill: #06630a; -fx-font-weight: bold");
        
        //ImageView
        image = new ImageView(icon);
        
        //ButtonOK 
        buttonOK = new Button("OK");
        buttonOK.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
        buttonOK.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                eventButtonOK();
            }
        });
        
        //Layout main
        VBox layoutMain = new VBox();
        layoutMain.setStyle("-fx-background-color: #efbaf2");
        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.setSpacing(5);
        layoutMain.getChildren().addAll(image, text1, text2, text3, buttonOK);

        //Scene
        cena = new Scene(layoutMain);
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonOK();
                }
            }
        });
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(cena);
        audioClip.play();
        window.showAndWait();
    }
    
    //Add listeners
    public static void eventButtonOK(){
        window.close();
    }
    //
    
}
