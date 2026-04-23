package is.vidmot;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/******************************************************************************
 *  Lýsing  : Einfalt aðalforrit
 *
 *
 *****************************************************************************/
public class LudoApp extends javafx.application.Application {
    /**
     * Ræsir appið
     *
     * @param stage glugginn
     * @throws Exception undnantekning sem verður ef villla
     */
    @Override
    public void start(Stage stage) {
        var scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.VALMYND, true);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
