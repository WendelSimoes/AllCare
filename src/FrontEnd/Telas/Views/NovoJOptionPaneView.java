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
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Tela para exibição de messagens negativas ou positivas
public class NovoJOptionPaneView {
    
    //Stage & scene
    private static Stage window;
    private static Scene cena;
    
    //Layouts
    private static BorderPane layoutMain;
    private static VBox layoutRight;
    
    //Common nodes
    private static ImageView image;
    private static Label text;
    private static Button buttonOK;
    private static AudioClip audioClip;
    private static Image icon;
    
    public static void display(String message, boolean okChan){
        window = new Stage();
        
        if(okChan){
            audioClip = new AudioClip(NovoJOptionPaneView.class.getResource("/FrontEnd/Midia/Ok-onii-chan.mp3").toString());
            window.setTitle("Sucesso");
            icon = new Image(NovoJOptionPaneView.class.getResourceAsStream("/FrontEnd/Midia/OkChan.png"));
        }else{
            audioClip = new AudioClip(NovoJOptionPaneView.class.getResource("/FrontEnd/Midia/Baka-onii-chan.mp3").toString());
            window.setTitle("Erro");
            icon = new Image(NovoJOptionPaneView.class.getResourceAsStream("/FrontEnd/Midia/NoChan.png"));
        }
        
        //WorkArea
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
            buttonOK.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            buttonOK.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    eventButtonOK();
                }
            });
            
            //Layout right
            layoutRight = new VBox();
            BorderPane.setMargin(layoutRight, new Insets(0, 8, 5, 0));
            layoutRight.setAlignment(Pos.BOTTOM_RIGHT);
            layoutRight.setSpacing(5);
            layoutRight.getChildren().addAll(text, buttonOK);
            
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
