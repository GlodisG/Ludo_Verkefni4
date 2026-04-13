package is.vidmot.controller;
import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.Leikmadur;
import is.vinnsla.Ludo;
import is.vinnsla.Ped;
import is.vinnsla.Reitur;
import is.vinnsla.Teningur;
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
    @FXML private Label labelEinn;
    @FXML private Label labelTveir;
    @FXML private Label labelThrir;
    @FXML private Label labelFjorir;

    // Leikborð skilgreint
    private final Map<Integer, StackPane> vidmotLeid = new HashMap<>();
    private final int[] bleikurUpphafsreitir = {57,58,59,60}; 	//leikmaður 1
    private final int[] graennUpphafsreitir = {61,62,63,64};	//leikmaður 2
    private final int[] gulurUpphafsreitir = {65,66,67,68};		//leikmaður 4
    private final int[] blarUpphafsreitir = {69,70,71,72};		//leikmaður 3
    
    /**
     * Array sem heldur utan um breytilegan fjölda leikmanna
     * Fær fjöldan úr upphafsglugga ásamt nöfnum(placeholder sett inn)
     */
    private int[] hverjirVirkir;
    private static boolean[] VIRKIR;
    private static int fjoldi;
    private static String[] nafnSpilara;

    private Ludo ludo;
    private Teningur teningur = new Teningur();
    private Reitur reitur = new Reitur();

    private int teljari0;
    private int teljari1;
    private int teljari2;
    private int teljari3;

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
        		case 0 -> nafnSpilara[0] + " færist " + ten + " áfram";
				case 1 -> nafnSpilara[1] + " færist " + ten + " áfram";
				case 2 -> nafnSpilara[2] + " færist " + ten + " áfram";
				case 3 -> nafnSpilara[3] + " færist " + ten + " áfram";
				default -> "";
        		});

        //Prenta út þegar peð fer i mark
        forIMark();

        //Segir hvaða peð var verið að lenda á ef einhverju
        erSamiReitur(hverGera);

        //Prentar sigurtexta eftir því hver vann
        if(ludo.getLeikmadur(hverGera).erSigurvegari()) {
        	switch(hverGera) {
        	case 0 -> additionalText.setText(nafnSpilara[0] + " vann!");
        	case 1 -> additionalText.setText(nafnSpilara[1] + " vann!");
        	case 2 -> additionalText.setText(nafnSpilara[2] + " vann!");
        	case 3 -> additionalText.setText(nafnSpilara[3] + " vann!");
        	}
        	Ludo.setLeikLokid(true);
        }
    }

    /**
     * Prentar út setningu að peð fór í mark ef það fór í mark
     */
    private void forIMark(){
        if(ludo.getLeikmadur(0).getPed(teljari0).getErSigrari()){
            teljari0++;
            additionalText.setText("Peð fór í mark!");
        }
        if(ludo.getLeikmadur(1).getPed(teljari1).getErSigrari()){
            teljari1++;
            additionalText.setText("Peð fór í mark!");
        }
        if(ludo.getLeikmadur(2).getPed(teljari2).getErSigrari()){
            teljari2++;
            additionalText.setText("Peð fór í mark!");
        }
        if(ludo.getLeikmadur(3).getPed(teljari3).getErSigrari()){
            teljari3++;
            additionalText.setText("Peð fór í mark!");
        }
    }

    /**
     * Telur hversu margir leikmenn eru að spila og upphafsstillir fylki
     * sem inniheldur númer leikmanns sem er virkur
     */
    private void setHverjir() {
    	VIRKIR = ValmyndController.hvadaLit();
    	int counter = 0;
    	int hverjirCounter = 0;
		for(int i = 0; i < VIRKIR.length; i++) {
			if(VIRKIR[i])
				counter++;	
		}
		hverjirVirkir = new int[counter];
		for(int i = 0; i < VIRKIR.length; i++) {
			if(VIRKIR[i]) {
				hverjirVirkir[hverjirCounter] = i;			
				System.out.println(i + " :: " + hverjirVirkir[hverjirCounter]);
				hverjirCounter++;
			}
		}
		System.out.println("FJOLDI: " + counter + " VIRKIR: " + hverjirVirkir.toString());
	}

    /**
     *
     * @param leikmadur
     * @param ped
     * @return númer reits sem spilari er á
     */
    private int hvadaReitur(int leikmadur, int ped){
        return Reitur.getReitur(leikmadur+1,ped,ludo.getLeikmadur(leikmadur).getPed(ped).getStadsetning());
    }

    /**
     * Ef peð lendir á öðru peði þá ver fórnalambið á byrjunarreit
     * lætur notanda vita hvað gerðist
     * @param hverGera Segir til um hvaða peð lenti á hinu
     */
    public void erSamiReitur(int hverGera) {
        int reitaFylki[][]=new int[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                reitaFylki[i][j] = hvadaReitur(i, j);
                System.out.println("Spilari "+ i + ": " + reitaFylki[i][j]);
            }
        }
        int hverGeraTeljari=hverGera; //1
        hverGeraTeljari++; //passar að skoða ekki liðið sem er að gera
        for(int i=0;i<3;i++){ //skoðar 3 lið
            if(hverGeraTeljari>3){
                hverGeraTeljari=0;
            }
            for(int k=0;k<4;k++){ //skoðar öll ped þeirra sem var að gera
                for(int j=0;j<4;j++){ //skoðar öll peð hja hinu lidinu
                    if(hvadaReitur(hverGera, k)==hvadaReitur(hverGeraTeljari,j)){
                        System.out.println("Ped "+ k +" hja  spilara "+ hverGera +" er a sama reit og ped "+ j +" hja spilara "+ hverGeraTeljari);
                        ludo.getLeikmadur(hverGeraTeljari).getPed(j).endurstillaPed(-1);
                        additionalText.setText("Úps! " + nafnSpilara[hverGeraTeljari] + " aftur á byrjunarreit");
                    }
                }
            }
            hverGeraTeljari++;
        }
    }

    /**
     *Þegar ýtt er á "nýr leikur"
     */
    public void onNyrLeikur(){
        teljari0=0;
        teljari1=0;
        teljari2=0;
        teljari3=0;
        setHverByrjarTexti();
        additionalText.setText("Ýttu á tening til að hefja leik");
        Leikmadur.setFjoldi(fjoldi);
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
     * Gefur leikmanni valkost á hvaða peði hann/hún vill hreyfa af þeim sem eru virk
     * @param leikmadur Númer leikmanns
     * //TODO Seinna bæta við return value með vali leikmanns
     */
    private void hvadaPed(int leikmadur) {
    	HashMap<Ped, Integer> virkPed; //TODO Viljum örugglega nota þetta :D
    }

    /**
     * Færir viðmótshlut peðs á leikborði
     * Viðmótshluturinn er fjarlægður fyrst frá StackPane foreldri,
     * því næst er því bætt við nýtt StackPane foreldri.
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

    /**
     * Skrifar nöfn spilara á ludo borðið
     */
    private void stillaNofn() {
    	nafnSpilara = ValmyndController.getNafnSpilara();
    	labelEinn.setText("");
		labelTveir.setText("");
		labelThrir.setText("");
		labelFjorir.setText("");
		labelEinn.setText(nafnSpilara[0]);
        labelTveir.setText(nafnSpilara[1]);
        labelThrir.setText(nafnSpilara[2]);
        labelFjorir.setText(nafnSpilara[3]);
    }

    /**
     * Lætur notanda vita hver byrjar
     */
    private void setHverByrjarTexti(){
        switch (hverjirVirkir[0]) {
            case 0 -> welcomeText.setText(nafnSpilara[0] + " gerir fyrst");
            case 1 -> welcomeText.setText(nafnSpilara[1] + " gerir fyrst");
            case 2 -> welcomeText.setText(nafnSpilara[2] + " gerir fyrst");
            case 3 -> welcomeText.setText(nafnSpilara[3] + " gerir fyrst");
        }
    }
    
    public void initialize() {
    	setHverjir();
    	fjoldi = VIRKIR.length;
    	buaTilLeid();
    	stillaNofn();
    	Leikmadur.setFjoldi(fjoldi);
        System.out.println("Boolean fylkið: " + VIRKIR[0] + " " + VIRKIR[1] + " " + VIRKIR[2] + " " + VIRKIR[3]);
        setHverByrjarTexti();
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
        Leikmadur.setFjoldi(fjoldi);
        ludo = new Ludo(fjoldi,nafnSpilara,hverjirVirkir);
        
        // tengir viðmótshluti leikmanna við property með listeners með hjálparaðferðum.
        for(int i: hverjirVirkir) {
	        System.out.println("Virkur: " + i);
        }
        for(int i = 0; i < hverjirVirkir.length; i++) {
    		for(int j = 0; j < 4; j++) {
    			Ped ped = ludo.getLeikmenn()[hverjirVirkir[i]].getPed(j); 
    			System.out.println("Bind: " + nafnSpilara[hverjirVirkir[i]] + "| ped:" + j);
    			switch(hverjirVirkir[i]) {
	    			case 0: {
	    				switch(j) {
		    			case 0 -> bindaPed(ped, bleikurKall1, hverjirVirkir[i], j);
		    			case 1 -> bindaPed(ped, bleikurKall2, hverjirVirkir[i], j);
		    			case 2 -> bindaPed(ped, bleikurKall3, hverjirVirkir[i], j);
		    			case 3 -> bindaPed(ped, bleikurKall4, hverjirVirkir[i], j);
	    				}
	    				break;
	    			}
	    			case 1: {
	    				switch(j) {
	    				case 0 -> bindaPed(ped, graennKall1, hverjirVirkir[i], j);
	    				case 1 -> bindaPed(ped, graennKall2, hverjirVirkir[i], j);
	    				case 2 -> bindaPed(ped, graennKall3, hverjirVirkir[i], j);
	    				case 3 -> bindaPed(ped, graennKall4, hverjirVirkir[i], j);
	    				}
	    				break;
	    			}
	    			case 2: {
	    				switch(j) {
	    				case 0 -> bindaPed(ped, blarKall1, hverjirVirkir[i], j);
	    				case 1 -> bindaPed(ped, blarKall2, hverjirVirkir[i], j);
	    				case 2 -> bindaPed(ped, blarKall3, hverjirVirkir[i], j);
	    				case 3 -> bindaPed(ped, blarKall4, hverjirVirkir[i], j);
	    				}
	    				break;
	    			}
	    			case 3: {
		    			switch(j) {
		    			case 0 -> bindaPed(ped, gulurKall1, hverjirVirkir[i], j);
		    			case 1 -> bindaPed(ped, gulurKall2, hverjirVirkir[i], j);
		    			case 2 -> bindaPed(ped, gulurKall3, hverjirVirkir[i], j);
		    			case 3 -> bindaPed(ped, gulurKall4, hverjirVirkir[i], j);
		    			}
		    			break;
	    			}
				}
	    	}
		}
        Leikmadur.setLeikmadur(0);
        ludo.endurstillaLeid();
        teljari0=0;
        teljari1=0;
        teljari2=0;
        teljari3=0;
    }
}

