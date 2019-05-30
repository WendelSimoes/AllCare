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

public class NovoJOptionPane {
    
    private static ImageView image;
    private static Label text;
    private static Button buttonOK;
    private static AudioClip oni;
    private static Image icon;
    
    public static void display(String message, boolean okChan){
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        if(okChan){
            oni = new AudioClip(NovoJOptionPane.class.getResource("Ok-onii-chan.mp3").toString());
            oni.play();
            window.setTitle("Sucesso");
            icon = new Image(NovoJOptionPane.class.getResourceAsStream("OkChan.png"));
        }else{
            oni = new AudioClip(NovoJOptionPane.class.getResource("Baka-onii-chan.mp3").toString());
            oni.play();
            window.setTitle("Erro");
            icon = new Image(NovoJOptionPane.class.getResourceAsStream("NoChan.png"));
        }
        
        //ImageView
        image = new ImageView(icon);
        BorderPane.setMargin(image, new Insets(5, 0, 0, 0));
        
        //Text
        text = new Label();
        text.setStyle("-fx-font-size: 15; -fx-text-fill: #000000");
        if(okChan){
            text.setText(message + ", onii-chan!");
        }else{
            text.setText(message + ", BAKA!");
        }
        
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
        VBox layoutRight = new VBox();
        BorderPane.setMargin(layoutRight, new Insets(0, 8, 5, 0));
        layoutRight.setAlignment(Pos.BOTTOM_RIGHT);
        layoutRight.setSpacing(5);
        layoutRight.getChildren().addAll(text, buttonOK);
        
        //Layout main
        BorderPane layoutMain = new BorderPane();
        layoutMain.setLeft(image);
        layoutMain.setRight(layoutRight);
        
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
