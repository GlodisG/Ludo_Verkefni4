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
    @FXML private CheckBox hakEinn;
    @FXML private CheckBox hakTveir;
    @FXML private CheckBox hakThrir;
    @FXML private CheckBox hakFjorir;
    @FXML private Button hefjaLeik;
    private static boolean [] hvadaLit = {false,false,false,false}; //bleikur, grænn, blár, gulur
    private static String [] nafnSpilara = {"","","",""};

    public void initialize() {
        Spilari1.disableProperty().
                bind(hakEinn.selectedProperty().not());
        Spilari2.disableProperty().
                bind(hakTveir.selectedProperty().not());
        Spilari3.disableProperty().
                bind(hakThrir.selectedProperty().not());
        Spilari4.disableProperty().
                bind(hakFjorir.selectedProperty().not());
        hefjaLeik.setDisable(true);
    }
    @FXML
    public void onHakad(ActionEvent ignored) {
        int teljari = 0;
        if(hakEinn.isSelected()){
            teljari++;
        }
        if(hakTveir.isSelected()){
            teljari++;
        }
        if(hakThrir.isSelected()){
            teljari++;
        }
        if(hakFjorir.isSelected()){
            teljari++;
        }
        System.out.println(teljari);
        if (teljari>1){
            hefjaLeik.setDisable(false);
        } else {
            hefjaLeik.setDisable(true);
        }
        hvadaLit[0] = hakEinn.isSelected();
        hvadaLit[1] = hakTveir.isSelected();
        hvadaLit[2] = hakThrir.isSelected();
        hvadaLit[3] = hakFjorir.isSelected();
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
    	endurstillaNofn();
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
        System.out.println(nafnSpilara[0] + " | " + nafnSpilara[1] + " | " + nafnSpilara[2] + " | " + nafnSpilara[3]);
        ViewSwitcher.switchTo(View.BORD,false, null);
    }
    public static String[] getNafnSpilara() {
		return nafnSpilara;
	}
    private void endurstillaNofn() {
    	nafnSpilara[0] = "";
		nafnSpilara[1] = "";
		nafnSpilara[2] = "";
		nafnSpilara[3] = "";
    }
}
