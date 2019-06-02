package FrontEnd.CSS;

public class DefaultStyles {
    
    private final static String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    private final static String STYLE_PADRAO_FIELD_ERRO = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px; -fx-border-color: #ff0000";
    private final static String STYLE_PADRAO_BUTTON = "-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)";
    private static final String STYLE_PADRAO_LABELS_LEFT_DESCRICAO = "-fx-font-weight: bold";
    private static final String STYLE_PADRAO_LABELS_RIGHT_DESCRICAO = "-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px";
    private final static int TEXT_FIELD_MAX_CHARS = 50;
    private static final int TEXT_AREA_MAX_CHARS = 300;

    public static String getSTYLE_PADRAO_FIELD() {
        return STYLE_PADRAO_FIELD;
    }

    public static String getSTYLE_PADRAO_BUTTON() {
        return STYLE_PADRAO_BUTTON;
    }

    public static String getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO() {
        return STYLE_PADRAO_LABELS_LEFT_DESCRICAO;
    }

    public static String getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO() {
        return STYLE_PADRAO_LABELS_RIGHT_DESCRICAO;
    }

    public static int getTEXT_FIELD_MAX_CHARS() {
        return TEXT_FIELD_MAX_CHARS;
    }

    public static int getTEXT_AREA_MAX_CHARS() {
        return TEXT_AREA_MAX_CHARS;
    }

    public static String getSTYLE_PADRAO_FIELD_ERRO() {
        return STYLE_PADRAO_FIELD_ERRO;
    }
    
}
