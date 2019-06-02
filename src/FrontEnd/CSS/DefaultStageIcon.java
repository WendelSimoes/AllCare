package FrontEnd.CSS;

import javafx.scene.image.Image;

public class DefaultStageIcon {
    private static final Image stageIcon = new Image(DefaultStageIcon.class.getResourceAsStream("/FrontEnd/Midia/SickChan.png"));

    public static Image getStageIcon() {
        return stageIcon;
    }
}
