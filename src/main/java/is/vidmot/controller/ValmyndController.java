package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ValmyndController {
    @FXML private TextField Spilari1;
    @FXML private TextField Spilari2;
    @FXML private TextField Spilari3;
    @FXML private TextField Spilari4;
    @FXML private CheckBox checkEinn;
    @FXML private CheckBox checkTveir;
    @FXML private CheckBox checkThrir;
    @FXML private CheckBox checkFjorir;
    @FXML private Button hefjaLeik;
    public static boolean [] hvadaLit = {false,false,false,false};
    public static String [] nafnSpilara = {"","","",""};

    public void initialize() {
        Spilari1.disableProperty().
                bind(checkEinn.selectedProperty().not());
        Spilari2.disableProperty().
                bind(checkTveir.selectedProperty().not());
        Spilari3.disableProperty().
                bind(checkThrir.selectedProperty().not());
        Spilari4.disableProperty().
                bind(checkFjorir.selectedProperty().not());
        hefjaLeik.setDisable(true);
    }
    @FXML
    public void onChecked(ActionEvent ignored) {
        int counter = 0;
        if(checkEinn.isSelected()){
            counter++;
        }
        if(checkTveir.isSelected()){
            counter++;
        }
        if(checkThrir.isSelected()){
            counter++;
        }
        if(checkFjorir.isSelected()){
            counter++;
        }
        System.out.println(counter);
        if (counter>1){
            hefjaLeik.setDisable(false);
        } else {
            hefjaLeik.setDisable(true);
        }
        hvadaLit[0] = checkEinn.isSelected();
        hvadaLit[1] = checkTveir.isSelected();
        hvadaLit[2] = checkThrir.isSelected();
        hvadaLit[3] = checkFjorir.isSelected();
    }

    /**
     *
     * @return boolean fylki sem segir hvaða litir eru í spilun
     */
    public static boolean[] hvadaLit() {
        return hvadaLit;
    }

    /**
     * @param ignored
     * Breytir senunni í leik senuna
     */
    @FXML
    public void onHefjaLeik(ActionEvent ignored) {
        if (hvadaLit[0]) { nafnSpilara[0] = Spilari1.getText();
            if (Spilari1.getText().isEmpty()) { nafnSpilara[0] = "Bjarni Bleiki"; }
        }
        if (hvadaLit[1]) { nafnSpilara[1] = Spilari2.getText();
            if (Spilari2.getText().isEmpty()) { nafnSpilara[1] = "Gerður Græna"; }
        }
        if (hvadaLit[2]) { nafnSpilara[2] = Spilari3.getText();
            if (Spilari3.getText().isEmpty()) { nafnSpilara[2] = "Stjáni Blái"; }
        }
        if (hvadaLit[3]) { nafnSpilara[3] = Spilari4.getText();
            if (Spilari4.getText().isEmpty()) { nafnSpilara[3] = "Gyða Gula"; }
        }
        System.out.println(nafnSpilara[0] + nafnSpilara[1] + nafnSpilara[2] + nafnSpilara[3]);
        ViewSwitcher.switchTo(View.BORD,false, null);
    }
}
