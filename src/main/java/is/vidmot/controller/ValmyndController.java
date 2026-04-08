package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ValmyndController {
	
    /**
     * @param ignored
     * Breytir senunni í leik senuna
     */
    @FXML
    public void onHefjaLeik(ActionEvent ignored) {
        ViewSwitcher.switchTo(View.BORD,false, null);
    }
}
