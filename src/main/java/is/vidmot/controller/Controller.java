package is.vidmot.controller;
import is.vinnsla.Leikmadur;
import is.vinnsla.Ludo;
import is.vinnsla.Reitur;
import is.vinnsla.Teningur;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/******************************************************************************
 *  Lýsing  : Controllerinn
 *
 *****************************************************************************/

public class Controller {
    private static final String[] myndir = {"one", "two","three","four","five","six"};
    

    //Skilaboð + takkar + grid
    @FXML private Label welcomeText; // Viðmótshlutur sem geymir texta með kveðju
    @FXML private Label additionalText;
    @FXML public Button fxTeningur;
    @FXML public Button fxNyrLeikur;
    @FXML public GridPane fxGrid;
    //Reitir sem Spilarar færast á
    @FXML private StackPane bleikur0;
    @FXML private StackPane bleikur1;
    @FXML private StackPane bleikur2;
    @FXML private StackPane bleikur3;
    @FXML private StackPane bleikur4;
    @FXML private StackPane bleikur5;
    @FXML private StackPane bleikur6;
    @FXML private StackPane bleikur7;
    @FXML private StackPane bleikur8;
    @FXML private StackPane bleikur9;
    @FXML private StackPane bleikurEinn;
    @FXML private StackPane bleikurTveir;
    @FXML private StackPane bleikurThrir;
    @FXML private StackPane bleikurSigur;
    @FXML private StackPane graenn0;
    @FXML private StackPane graenn1;
    @FXML private StackPane graenn2;
    @FXML private StackPane graenn3;
    @FXML private StackPane graenn4;
    @FXML private StackPane graenn5;
    @FXML private StackPane graenn6;
    @FXML private StackPane graenn7;
    @FXML private StackPane graenn8;
    @FXML private StackPane graenn9;
    @FXML private StackPane graennEinn;
    @FXML private StackPane graennTveir;
    @FXML private StackPane graennThrir;
    @FXML private StackPane graennSigur;
    @FXML private StackPane bleikurStart;
    @FXML private StackPane graennStart;
    @FXML private ImageView bleikurKall;
    @FXML private ImageView graennKall;

    //Leikborð skilgreint
    public static HashMap<Integer, StackPane> ludoBord = new HashMap<>();
    // Leikborð 2.0
    private final Map<Integer, StackPane> vidmotLeid = new HashMap<>();
    
    /*
     * Array sem heldur utan um breytilegan fjölda leikmanna
     * Fær fjöldan úr upphafsglugga ásamt nöfnum(placeholder sett inn)
     */
    private final int FJOLDI = 2;
    private final String[] NOFN = {"Leikmaður 1", "Leikmaður 2"};
    private Leikmadur[] leikmenn;
    
    private Ludo ludo = new Ludo();
    private Reitur reitur = new Reitur();

    /**
     *Þegar ýtt er á tening
     * kallar fallið á öll rétt föll til að kasta tening,færa kall og breyta texta
     */
    public void onKastaTening() {
        additionalText.setText("");
        Ludo.leikaLeik();
        int ten = Teningur.getTala();

        int hverGera = Leikmadur.getLeikmadur(FJOLDI);
        System.out.println("hverGera: "+hverGera);
        if(hverGera==2){
            welcomeText.setText("Grænn færist " + ten + " áfram");
            
            reitur.faeraLeikmann(2, ten);
            hreyfaGraenann(reitur.getReitur(2));
            System.out.println("Grænn: "+reitur.getReitur(2));
            /*
            int gTeljari= Ludo.getGraennLeid();
            //int gTeljari= reitur.getReitur(1);
            hreyfaGraenann(Reitur.reitur(gTeljari,hverGera));
             */
        } else {
            welcomeText.setText("Bleikur færist " + ten + " áfram");
            
            reitur.faeraLeikmann(1, ten);
            hreyfaBleikann(reitur.getReitur(1));
            System.out.println("Bleikur: "+reitur.getReitur(1));
            /*
            //int bTeljari= reitur.getReitur(2);
            int bTeljari= Ludo.getBleikurLeid();
            hreyfaBleikann(Reitur.reitur(bTeljari,hverGera));
             */
        }

        //Prentar sigurtexta eftir því hver vann
        if(Ludo.getLeikLokid()) {
            if(Leikmadur.hvadaKall()==1){
            	additionalText.setText("Bleikur vann!");
            	hreyfaBleikann(56);
            } else {
                additionalText.setText("Grænn vann!");
                hreyfaGraenann(56);
            }
        }

        //Skilgreint á hvaða reit báðir spilarar eru
        int reiturG = 0;
        if(Ludo.getGraennLeid()>0){
            reiturG =Reitur.reitur(Ludo.getGraennLeid(),1);
        }
        int reiturB =Reitur.reitur(Ludo.getBleikurLeid(),2);

        //Ef þeir lenda á sama reit
        if(reiturG==reiturB) {
            if (Leikmadur.hvadaKall()==1) {
                Ludo.bleikurLeid.set(0);
                hreyfaBleikann(57);
                additionalText.setText("Úps! Bleikur aftur á byrjunarreit");
            } else {
                Ludo.graennLeid.set(0);
                hreyfaGraenann(61);
                additionalText.setText("Úps! Grænn aftur á byrjunarreit");
            }
        }
    }

    /**
     *Þegar ýtt er á "nýr leikur"
     */
    public void onNyrLeikur(){
        welcomeText.setText("Bleikur gerir fyrst");
        additionalText.setText("Ýttu á tening til að hefja leik");
        Leikmadur.setLeikmadur(1);
        hreyfaGraenann(61);
        hreyfaBleikann(57);
        Ludo.endurstillaLeid();
        reitur.endursetjaLeid(); //
        Ludo.setLeikLokid(false);
        System.out.println(Ludo.getLeikLokid());
        
        leikmenn = new Leikmadur[FJOLDI];
        for(int i = 0; i < leikmenn.length; i++) {
        	leikmenn[i] = new Leikmadur(NOFN[i]);
        }
    }

    /**
     * @param stadsetning //hvert á ludobordid á að færa
     * Hreyfir græna kallinn
     */
    public void hreyfaGraenann(int stadsetning) {
        Pane targetTile = vidmotLeid.get(stadsetning);
        if (targetTile == null) return;
        Pane currentParent = (Pane) graennKall.getParent();
        if (currentParent != null) {
            currentParent.getChildren().remove(graennKall);
        }
        targetTile.getChildren().add(graennKall);
    }

    /**
     * @param stadsetning //hvert á ludobordid á að færa
     * Hreyfir bleika kallinn
     */
    public void hreyfaBleikann(int stadsetning) {
        Pane targetTile = vidmotLeid.get(stadsetning);
        if (targetTile == null) return;
        Pane currentParent = (Pane) bleikurKall.getParent();
        if (currentParent != null) {
            currentParent.getChildren().remove(bleikurKall);
        }
        targetTile.getChildren().add(bleikurKall);
    }
    
    public void hreyfaLeikmann(int stadsetning) {
    	Pane targetTile = vidmotLeid.get(stadsetning);
    	if(targetTile == null) return;
    	int currentPlayer = Leikmadur.hvadaKall();
    	Node ped = null;
    	switch(currentPlayer) {
    		case 1 : ped = bleikurKall;
    		case 2 : ped = graennKall;
    		//peð 3
    		//peð 4
    	}
    	Pane currentParent = (Pane) ped.getParent();
    	targetTile.getChildren().add(ped);
    	if(currentParent != null) 
    		currentParent.getChildren().remove(ped);
    }

    public void initialize() {
    	buaTilLeid();
        welcomeText.setText("Bleikur gerir fyrst");
        additionalText.setText("Ýttu á tening til að hefja leik");

        //ludoBord
        //Reitir í kringum borðið
        ludoBord.put(0, graenn0);
        ludoBord.put(1, graenn1);
        ludoBord.put(2, graenn2);
        ludoBord.put(3, graenn3);
        ludoBord.put(4, graenn4);
        ludoBord.put(5, graenn5);
        ludoBord.put(6, graenn6);
        ludoBord.put(7, graenn7);
        ludoBord.put(8, graenn8);
        ludoBord.put(9, graenn9);
        ludoBord.put(10, bleikur0);
        ludoBord.put(11, bleikur1);
        ludoBord.put(12, bleikur2);
        ludoBord.put(13, bleikur3);
        ludoBord.put(14, bleikur4);
        ludoBord.put(15, bleikur5);
        ludoBord.put(16, bleikur6);
        ludoBord.put(17, bleikur7);
        ludoBord.put(18, bleikur8);
        ludoBord.put(19, bleikur9);
        //Sigurleið hjá grænum
        ludoBord.put(20, graennEinn);
        ludoBord.put(21, graennTveir);
        ludoBord.put(22, graennThrir);
        ludoBord.put(23, graennSigur);
        //Sigurleið hjá bleikum
        ludoBord.put(24, bleikurEinn);
        ludoBord.put(25, bleikurTveir);
        ludoBord.put(26, bleikurThrir);
        ludoBord.put(27, bleikurSigur);
        //Upphafsreitir
        ludoBord.put(28, bleikurStart);
        ludoBord.put(29, graennStart);


        fxNyrLeikur.disableProperty().bind(Ludo.leikLokidProperty().not());

        fxTeningur.disableProperty().bind(fxNyrLeikur.disableProperty().not());

        //Að setja myndirnar af tening eftir teningakasti
        Ludo.getTeningur().talaProperty().addListener(
                (obs, gamla, nytt) -> {

                    fxTeningur.getStyleClass()
                            .removeAll("one","two","three","four","five","six");

                    fxTeningur.getStyleClass()
                            .add(myndir[nytt.intValue() - 1]);
                }
        );
        
        leikmenn = new Leikmadur[FJOLDI];
        for(int i = 0; i < leikmenn.length; i++) {
        	leikmenn[i] = new Leikmadur(NOFN[i]);
        }
    }
    public void buaTilLeid() {
    	int index = 0;
    	for(Node node: fxGrid.getChildren()) {
    		if(node instanceof StackPane && index < 57) {
    			vidmotLeid.put(index++, (StackPane) node);
    		}
    	}
    	vidmotLeid.forEach((i, pane) -> System.out.println("index: " + i + " | pane : " + pane));
    }
}

