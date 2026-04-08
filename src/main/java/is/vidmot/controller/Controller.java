package is.vidmot.controller;
import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.Leikmadur;
import is.vinnsla.Ludo;
import is.vinnsla.Ped;
import is.vinnsla.Reitur;
import is.vinnsla.Teningur;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
    @FXML private ImageView bleikurKall1;
    @FXML private ImageView bleikurKall2;
    @FXML private ImageView bleikurKall3;
    @FXML private ImageView bleikurKall4;
    @FXML private ImageView graennKall1;
    @FXML private ImageView graennKall2;
    @FXML private ImageView graennKall3;
    @FXML private ImageView graennKall4;
    @FXML private ImageView gulurKall1;
    @FXML private ImageView gulurKall2;
    @FXML private ImageView gulurKall3;
    @FXML private ImageView gulurKall4;
    @FXML private ImageView blarKall1;
    @FXML private ImageView blarKall2;
    @FXML private ImageView blarKall3;
    @FXML private ImageView blarKall4;

    //Leikborð skilgreint
    public static HashMap<Integer, StackPane> ludoBord = new HashMap<>();
    // Leikborð 2.0
    private final Map<Integer, StackPane> vidmotLeid = new HashMap<>();
    
    private final int[] bleikurUpphafsreitir = {57,58,59,60}; 	//leikmaður 1
    private final int[] graennUpphafsreitir = {61,62,63,64};	//leikmaður 2
    private final int[] gulurUpphafsreitir = {65,66,67,68};		//leikmaður 4
    private final int[] blarUpphafsreitir = {69,70,71,72};		//leikmaður 3
    
    /*
     * Array sem heldur utan um breytilegan fjölda leikmanna
     * Fær fjöldan úr upphafsglugga ásamt nöfnum(placeholder sett inn)
     */
    private final boolean[] ERVIRKUR = {false,false,false,false};
    private final int FJOLDI = 4;
    private final String[] NOFN = {"Leikmaður 1", "Leikmaður 2", "Leikmaður 3", "Leikmaður 4"};
    
    
    private Ludo ludo;
    private Teningur teningur = new Teningur();
    private Reitur reitur = new Reitur();

    /**
     *Þegar ýtt er á tening
     * kallar fallið á öll rétt föll til að kasta tening,færa kall og breyta texta
     */
    public void onKastaTening() {
        additionalText.setText("");
        ludo.leikaLeik();
        int ten = teningur.getTala();
        int hverGera = Leikmadur.getNaestiLeikmadur();
        //ludo.getLeikmadur(hverGera).faeraLeikmann(ten, 0, hverGera); // sökudólgur >:C
        welcomeText.setText(
        		switch(hverGera) {
        		case 0 -> "Bleikur færist " + ten + " áfram";
				case 1 -> "Grænn færist " + ten + " áfram";
				case 2 -> "Blar færist " + ten + " áfram";
				case 3 -> "Gulur færist " + ten + " áfram";
				default -> "";
        		});
        
        /*
        //System.out.println("hverGera: " + hverGera);
        if(hverGera==2){
        	hvadaPed(2);
        	ludo.getLeikmadur(hverGera).faeraLeikmann(ten, 0, hverGera);
            welcomeText.setText("Grænn færist " + ten + " áfram");
            
            reitur.faeraLeikmann(2, ten);
            hreyfaGraenann(reitur.getReitur(2));
            System.out.println("Graenn: "+reitur.getReitur(2));
        } else {
            welcomeText.setText("Bleikur færist " + ten + " áfram");
            hvadaPed(1);
            reitur.faeraLeikmann(1, ten);
            hreyfaBleikann(reitur.getReitur(1));
            System.out.println("Bleikur: "+reitur.getReitur(1));
        }
        */
        
        //Prentar sigurtexta eftir því hver vann
        if(ludo.getLeikmadur(hverGera).erSigurvegari()) {
        	switch(hverGera) {
        	case 0 -> additionalText.setText("Bleikur vann!");
        	case 1 -> additionalText.setText("Grænn vann!");
        	case 2 -> additionalText.setText("Blár vann!");
        	case 3 -> additionalText.setText("Gulur vann!");
        	}
        	Ludo.setLeikLokid(true);
        }
        /*
        if(Ludo.getLeikLokid()) {
            if(Leikmadur.hvadaKall()==1){
            	additionalText.setText("Bleikur vann!");
            	hreyfaBleikann(56);
            } else {
                additionalText.setText("Grænn vann!");
                hreyfaGraenann(56);
            }
        }
        */

        if (erSamiReitur()) {
            //gera nýja if s
        }

        /*
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
         */
    }

    /**
     *
     * @return boolean segir til hvort þeir séu á sama reit
     */
    public boolean erSamiReitur() {
        int reitur1 = reitur.reiturPlayer1Property().get();
        int reitur2 = reitur.reiturPlayer2Property().get();
        if (reitur1==reitur2){
            return true;
        }
        return false;
    }

    /**
     *Þegar ýtt er á "nýr leikur"
     */
    public void onNyrLeikur(){
        welcomeText.setText("Bleikur gerir fyrst");
        additionalText.setText("Ýttu á tening til að hefja leik");
        Leikmadur.setFjoldi(FJOLDI);
        Leikmadur.setLeikmadur(0);
        ludo.endurstillaLeid();
        Ludo.setLeikLokid(false);
        System.out.println(Ludo.getLeikLokid());
    }

    /**
     * @param ignored
     * Breytir senunni í valmynd senuna
     */
    @FXML
    public void onValmynd(ActionEvent ignored) {
        ViewSwitcher.switchTo(View.VALMYND,false, null);
    }

    /**
     * @param stadsetning //hvert á ludobordid á að færa
     * Hreyfir græna kallinn
     */
    /*
    public void hreyfaGraenann(int stadsetning) {
        Pane targetTile = vidmotLeid.get(stadsetning);
        if (targetTile == null) return;
        Pane currentParent = (Pane) graennKall1.getParent();
        if (currentParent != null) {
            currentParent.getChildren().remove(graennKall1);
        }
        targetTile.getChildren().add(graennKall1);
    }
    */

    /**
     * @param stadsetning //hvert á ludobordid á að færa
     * Hreyfir bleika kallinn
     */
    /*
    public void hreyfaBleikann(int stadsetning) {
        Pane targetTile = vidmotLeid.get(stadsetning);
        if (targetTile == null) return;
        Pane currentParent = (Pane) bleikurKall1.getParent();
        if (currentParent != null) {
            currentParent.getChildren().remove(bleikurKall1);
        }
        targetTile.getChildren().add(bleikurKall1);
    }
    */
    
    
    /**
     * Færir leikmann sem á næsta leik
     * @param stadsetning Núverandi staðsetning peðs
     */
    /*
    public void hreyfaLeikmann(int stadsetning) {
    	Pane targetTile = vidmotLeid.get(stadsetning);
    	if(targetTile == null) return;
    	int currentPlayer = Leikmadur.hvadaKall();
    	int currentPed = ludo.getLeikmadur(currentPlayer).getPedCounter(); //TODO fá return frá hvadaPed() aðferð að neðan : placeholder counter settur þangað til
    	Node ped = null;
    	switch(currentPlayer) {
    		case 1 : {
    			switch(currentPed) {
    			case 1 -> ped = bleikurKall1;
    			case 2 -> ped = bleikurKall2;
    			case 3 -> ped = bleikurKall3;
    			case 4 -> ped = bleikurKall4;
    			}
    			break;
    		}
    		case 2 : {
    			switch(currentPed) {
    			case 1 -> ped = graennKall1;
    			case 2 -> ped = graennKall2;
				case 3 -> ped = graennKall3;
				case 4 -> ped = graennKall4;
    			}
    			break;
    		}
    		//peð 3
    		//peð 4
    	}
    	Pane currentParent = (Pane) ped.getParent();
    	targetTile.getChildren().add(ped);
    	if(currentParent != null) 
    		currentParent.getChildren().remove(ped);
    }
    */
    
    
    /**
     * Gefur leikmanni valkost á hvaða peði hann/hún vill hreyfa af þeim sem eru virk
     * @param leikmadur Númer leikmanns
     * //TODO Seinna bæta við return value með vali leikmanns
     */
    private void hvadaPed(int leikmadur) {
    	HashMap<Ped, Integer> virkPed; //TODO Viljum örugglega nota þetta :D
    }

    public void initialize() {
    	buaTilLeid();
    	Leikmadur.setFjoldi(FJOLDI);
        welcomeText.setText("Bleikur gerir fyrst");
        additionalText.setText("Ýttu á tening til að hefja leik");

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
        Leikmadur.setFjoldi(FJOLDI);
        ludo = new Ludo(FJOLDI,NOFN);
        
        // tengir viðmótshluti leikmanna við property með listeners með hjálparaðferðum.
        for(int i = 0; i < FJOLDI; i++) {
    		for(int j = 0; j < 4; j++) {
    			Ped ped = ludo.getLeikmenn()[i].getPed(j); 
    			System.out.println("Bind: " + NOFN[i] + "| ped:" + j);
    			switch(i) {
	    			case 0: {
	    				switch(j) {
		    			case 0 -> bindaPed(ped, bleikurKall1, i, j);
		    			case 1 -> bindaPed(ped, bleikurKall2, i, j);
		    			case 2 -> bindaPed(ped, bleikurKall3, i, j);
		    			case 3 -> bindaPed(ped, bleikurKall4, i, j);
	    				}
	    				break;
	    			}
	    			case 1: {
	    				switch(j) {
	    				case 0 -> bindaPed(ped, graennKall1, i, j);
	    				case 1 -> bindaPed(ped, graennKall2, i, j);
	    				case 2 -> bindaPed(ped, graennKall3, i, j);
	    				case 3 -> bindaPed(ped, graennKall4, i, j);
	    				}
	    				break;
	    			}
	    			case 2: {
	    				switch(j) {
	    				case 0 -> bindaPed(ped, blarKall1, i, j);
	    				case 1 -> bindaPed(ped, blarKall2, i, j);
	    				case 2 -> bindaPed(ped, blarKall3, i, j);
	    				case 3 -> bindaPed(ped, blarKall4, i, j);
	    				}
	    				break;
	    			}
	    			case 3: {
		    			switch(j) {
		    			case 0 -> bindaPed(ped, gulurKall1, i, j);
		    			case 1 -> bindaPed(ped, gulurKall2, i, j);
		    			case 2 -> bindaPed(ped, gulurKall3, i, j);
		    			case 3 -> bindaPed(ped, gulurKall4, i, j);
		    			}
		    			break;
	    			}
				}
	    	}
		}
        Leikmadur.setLeikmadur(0);
        ludo.endurstillaLeid();
    }
    
    /**
     * Færir viðmótshlut peðs á leikborði
     * Viðmótshluturinn er fjarlægður fyrst frá StackPane foreldri,
     * því næst er því bætt við nýtt StackPane foreldri.
     * 
     * @param ped Viðmótshlutur peðs
     * @param stadsetning Stadsetning sem á að færa til
     */
    private void hreyfaPed(ImageView ped , int stadsetning) {
    	if(ped.getParent() instanceof Pane parent) {
    		parent.getChildren().remove(ped);
    	}
    	StackPane targetTile = vidmotLeid.get(stadsetning);
    	if(stadsetning == 99) return; // staðsetning 99 er til að "fela" peð þegar það kemst í mark(placeholder?)
		if(targetTile != null) {
			targetTile.getChildren().add(ped);
		}
		
    }
    
    /**
     * Hjálparaðferð
     * Bætir við listener sem hlustar á IntegerProperty peðs og uppfærir viðmótshlut samsvarandi peðs.
     * @param ped Peð leikmanns
     * @param pedImage Viðmótshlutur á leikborði
     * @param leikmadurNr Númer leikmanns
     * @param pedNr Númer peðs
     */
    private void bindaPed(Ped ped, ImageView pedImage, int leikmadurNr, int pedNr) {
    	ped.stadurProperty().addListener((obs, oldVal, newVal) -> {
    		int raunStadur = Reitur.getReitur(leikmadurNr+1, pedNr, newVal.intValue());
    		hreyfaPed(pedImage, raunStadur);
    	});
    	int raunStadur = Reitur.getReitur(leikmadurNr+1, pedNr, ped.getStadsetning());
    	hreyfaPed(pedImage, raunStadur);
    }
    /**
     * Finnur öll stackpane sem eru á leikborði og tengir þær við staðsetningar.
     * Býr til HashMap sem notar index sem táknar staðsetningu(key) 
     * og StackPane(value) er viðmótshluturinn.
     */
    private void buaTilLeid() {
    	int index = 0;
    	for(Node node: fxGrid.getChildren()) {
    		if(node instanceof StackPane && index < 73) {
    			vidmotLeid.put(index++, (StackPane) node);
    		}
    	}
    	vidmotLeid.forEach((i, pane) -> System.out.println("index: " + i + " | pane : " + pane));
    }
}

