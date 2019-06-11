package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Tela para adição de descrição à doença, aberta ao clique do botão "Descrição" na tela de doença
public class DescricaoView extends BorderPane {
    
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Layouts
    private VBox layoutDados;
    
    //Common nodes
    private TextField fieldPropagacao;
    private TextField fieldTratamento;
    private TextField fieldDuracao;
    private ComboBox boxDiagnosticavel;
    private TextField fieldMedicos;
    private TextArea areaDescricaoGeral;
    private Button buttonConcluir;
    
    public DescricaoView(){
        window = new Stage();
        
        //WorkArea
            //Field prograpagação
            fieldPropagacao = new TextField();
            fieldPropagacao.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldPropagacao.setPromptText("Propagação");
            fieldPropagacao.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));

            //Field tratamento
            fieldTratamento = new TextField();
            fieldTratamento.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldTratamento.setPromptText("Tratamento");
            fieldTratamento.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));

            //Field duração
            fieldDuracao = new TextField();
            fieldDuracao.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldDuracao.setPromptText("Duração");
            fieldDuracao.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));

            //Box diagnosticavel
            boxDiagnosticavel = new ComboBox();
            boxDiagnosticavel.setPrefWidth(250);
            boxDiagnosticavel.getItems().addAll("Sim", "Não");
            boxDiagnosticavel.setPromptText("Diagnosticável?");

            //Field medicos
            fieldMedicos = new TextField();
            fieldMedicos.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldMedicos.setPromptText("Médico(s)");
            fieldMedicos.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));

            //Area descrição
            areaDescricaoGeral = new TextArea();
            areaDescricaoGeral.setPrefSize(230, 75);
            areaDescricaoGeral.setWrapText(true);
            areaDescricaoGeral.setPromptText("Descrição geral");
            areaDescricaoGeral.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_AREA_MAX_CHARS() ? change : null));

            //Button concluir
            buttonConcluir = new Button("Concluir");
            buttonConcluir.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
            BorderPane.setAlignment(buttonConcluir, Pos.CENTER);
            
            //LayoutDados
            layoutDados = new VBox();
            layoutDados.setAlignment(Pos.CENTER);
            layoutDados.setSpacing(5);
            layoutDados.setPrefWidth(230);
            BorderPane.setMargin(layoutDados, new Insets(2, 2, 2, 2));
            layoutDados.getChildren().addAll(fieldPropagacao, fieldTratamento, fieldDuracao, boxDiagnosticavel, fieldMedicos, areaDescricaoGeral, buttonConcluir);
        //
            
        //Adicionando componentes
        this.setCenter(layoutDados);
        //
        
        //Scene
        cena = new Scene(this);
        cena.getStylesheets().addAll(DescricaoView.class.getResource("/FrontEnd/CSS/StyleTextArea.css").toExternalForm());
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Descrição");
        window.setResizable(false);
        window.setScene(cena);
    }
    
    //Embebedar campos
    public void atualizarFieldPropagacao(String text){
        fieldPropagacao.setText(text);
    }
    
    public void atualizarFieldTratamento(String text){
        fieldTratamento.setText(text);
    }
    
    public void atualizarFieldDuracao(String text){
        fieldDuracao.setText(text);
    }
    
    public void atualizarBoxDiagnosticavel(int indice){
        boxDiagnosticavel.getSelectionModel().select(indice);
    }
    
    public void atualizarFieldMedicos(String text){
        fieldMedicos.setText(text);
    }
    
    public void atualizarAreaDescricaoGeral(String text){
        areaDescricaoGeral.setText(text);
    }
    //
    
    //Add listeners
    public void addButtonConcluirListener(EventHandler<ActionEvent> eventHandler){
        buttonConcluir.setOnAction(eventHandler);
    }
    
    public void addSceneListener(EventHandler<KeyEvent> eventHandler){
        cena.setOnKeyPressed(eventHandler);
    }
    //

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Scene getCena() {
        return cena;
    }

    public void setCena(Scene cena) {
        this.cena = cena;
    }

    public VBox getLayoutDados() {
        return layoutDados;
    }

    public void setLayoutDados(VBox layoutDados) {
        this.layoutDados = layoutDados;
    }

    public TextField getFieldPropagacao() {
        return fieldPropagacao;
    }

    public void setFieldPropagacao(TextField fieldPropagacao) {
        this.fieldPropagacao = fieldPropagacao;
    }

    public TextField getFieldTratamento() {
        return fieldTratamento;
    }

    public void setFieldTratamento(TextField fieldTratamento) {
        this.fieldTratamento = fieldTratamento;
    }

    public TextField getFieldDuracao() {
        return fieldDuracao;
    }

    public void setFieldDuracao(TextField fieldDuracao) {
        this.fieldDuracao = fieldDuracao;
    }

    public ComboBox getBoxDiagnosticavel() {
        return boxDiagnosticavel;
    }

    public void setBoxDiagnosticavel(ComboBox boxDiagnosticavel) {
        this.boxDiagnosticavel = boxDiagnosticavel;
    }

    public TextField getFieldMedicos() {
        return fieldMedicos;
    }

    public void setFieldMedicos(TextField fieldMedicos) {
        this.fieldMedicos = fieldMedicos;
    }

    public TextArea getAreaDescricaoGeral() {
        return areaDescricaoGeral;
    }

    public void setAreaDescricaoGeral(TextArea areaDescricaoGeral) {
        this.areaDescricaoGeral = areaDescricaoGeral;
    }

    public Button getButtonConcluir() {
        return buttonConcluir;
    }

    public void setButtonConcluir(Button buttonConcluir) {
        this.buttonConcluir = buttonConcluir;
    }
    
}
