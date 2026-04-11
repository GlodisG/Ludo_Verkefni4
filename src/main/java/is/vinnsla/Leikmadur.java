package is.vinnsla;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Lýsing  : Innheldur hver á að gera og breytir því
 *
 *****************************************************************************/

public class Leikmadur {
    private static final IntegerProperty hvadaKall = new SimpleIntegerProperty(1);
    private final Ped[] pedArray;
    private final IntegerProperty ped1 = new SimpleIntegerProperty(-1);
	private final IntegerProperty ped2 = new SimpleIntegerProperty(-1);
	private final IntegerProperty ped3 = new SimpleIntegerProperty(-1);
	private final IntegerProperty ped4 = new SimpleIntegerProperty(-1);
	private final int[] bleikurUpphafsreitir = {57,58,59,60};
    private final int[] graennUpphafsreitir = {61,62,63,64};
    private final int[] gulurUpphafsreitir = {65,66,67,68};
    private final int[] blarUpphafsreitir = {69,70,71,72};
    private final String nafn;
    private static int fjoldi;
    private static boolean leikHafid = false;
    private HashMap<Ped, Integer> virkPed = new HashMap<>();
    private static int[] nyttFylki;
    private int pedCounter = 0; //placeholder teljari fyrir peð
    public int getPedCounter() {
    	return pedCounter;
    }
    /**
     * Smiður fyrir leikmann, inniheldur fylki af 4 peðum.
     * @param nafn Valkvætt nafn leikmanns
     * @param leikmadurNumer Númer leikmanns, merkir peðin hans.
     */
    public Leikmadur(String nafn, int leikmadurNumer) {
    	pedArray = new Ped[4];
    	for(int i = 0; i < pedArray.length; i++) {
    		switch(leikmadurNumer) {
    		case 0 -> pedArray[i] = new Ped(nafn, bleikurUpphafsreitir[i]);
    		case 1 -> pedArray[i] = new Ped(nafn, graennUpphafsreitir[i]);
			case 2 -> pedArray[i] = new Ped(nafn, blarUpphafsreitir[i]);
			case 3 -> pedArray[i] = new Ped(nafn, gulurUpphafsreitir[i]);
    		}
    	}
    	this.nafn = nafn;
    }
    /*
     * Sækir map af peðum sem eru á leikborði 
     */
    public HashMap<Ped,Integer> getVirkPed() {
    	virkPed.clear();
    	for(int i = 0; i < pedArray.length; i++) {
    		if(pedArray[i].getABordi()) 
    			virkPed.put(pedArray[i], pedArray[i].getStadsetning());
    	}
    	return virkPed;
    }
    
    /**
     * Færir peð leikmanns á leikborði
     * @param teningur Vegalengdin sem á að ferðast um
     * @param ped númer peðs sem á að hreyfa
     * @param leikmadurNumer Númer leikmanns sem á að hreyfa
     */
    public void faeraLeikmann(int teningur, int ped, int leikmadurNumer) {
    	if(teningur == 6) {
    		//valkostur um að bæta við peði ?
    		//kannski hafa þann kóða í controller?
    	}
    	/*
    	 * placeholder kóði, fer í gegnum fylkið, eitt peð í einu, þar til það hefur komist í mark
    	 * notar þá næsta peð eftir á o.s.frv.
    	 */
    	if(!pedArray[pedCounter].getErSigrari()) {
    		if(!pedArray[pedCounter].getABordi())
    			pedArray[pedCounter].setABordi(true);
    		
    		pedArray[pedCounter].faeraPed(teningur);
    		
    		if(pedArray[pedCounter].getStadsetning() >= 44) {
    			System.out.println(">> Ped komst i mark <<");
    			pedArray[pedCounter].setErSigrari(true);
    			pedArray[pedCounter].setABordi(false);
    			pedArray[pedCounter].felaPed();
    			pedCounter++;
    		}    		
    	}
    }
    
    
     /**
      * Skilar hvaða leikmadur á næsta leik
      * @return númer leikmanns
      */
    public static int getNaestiLeikmadur() { // þetta er bara nkl það sama og hvadaKall D:
        /*
        //ifhvergera meira en 4 næsti=0
        //int naesti=hvergera+1
        if (hverGera==1) {
            hvadaKall.set(2);
            return 2;
        }
        hvadaKall.set(1);
        return 1;
        */
        
        //System.out.println("hvadaKall: " + hvadaKall.get());
        return hvadaKall.get();
        
    }
    
    /**
     * Færir næsta leik á næsta leikmann, breytilegt eftir fjölda leikmanna
     * @param fjoldi Fjöldi leikmanna sem eru að spila
     */
    public static void setNaestiLeikmadur(boolean[] fylki) {
		int counter = 0;
    	int hverGera = hvadaKall.get();
    	if(!leikHafid) {
			for(int i=0;i<fylki.length;i++){
				if (fylki[i]) {
					counter++;
				}
			}
			nyttFylki = new int[counter];
			int counter2 = 0;
			for(int i =0;i<fylki.length;i++) {
				if (fylki[i]) {
					nyttFylki[counter2]=i;
					counter2++;
				}
			}
			System.out.println("nyttFylki: " + Arrays.toString(nyttFylki));
    		hvadaKall.set(0);
			leikHafid = true;
    		return;
    	}
    	switch(counter) {
	    	case 2:
	    	{
	    		if(hverGera == nyttFylki[0]) hvadaKall.set(nyttFylki[1]);
	    		if(hverGera == nyttFylki[1]) hvadaKall.set(nyttFylki[0]);
	    		break;
	    	}
	    	case 3:
	    	{
	    		if(hverGera == nyttFylki[0]) hvadaKall.set(nyttFylki[1]);
	    		if(hverGera == nyttFylki[1]) hvadaKall.set(nyttFylki[2]);
	    		if(hverGera == nyttFylki[2]) hvadaKall.set(nyttFylki[0]);
	    		break;
	    	}
	    	case 4: 
	    	{
	    		if(hverGera == nyttFylki[0]) hvadaKall.set(nyttFylki[1]);
	    		if(hverGera == nyttFylki[1]) hvadaKall.set(nyttFylki[2]);
	    		if(hverGera == nyttFylki[2]) hvadaKall.set(nyttFylki[3]);
	    		if(hverGera == nyttFylki[3]) hvadaKall.set(nyttFylki[0]);
	    		break;
	    	}
    	}  	
    }
    
    public IntegerProperty pedProperty(int ped) {
    	switch(ped) {
    	case 1 : return ped1;
		case 2 : return ped2;
		case 3 : return ped3;
		case 4 : return ped4;
		default : return null;
    	}
    }

    /**
     * @return int //nær í hver á að gera 1 eða 2 fyrir föll í öðrum klösum
     */
    public static int hvadaKall() {
        return hvadaKall.get();
    }

    /**
     * Lætur leikmann 1 gera, notað til að hefja nýjann leik
     */
    public static void setLeikmadur(int hver){
        hvadaKall.set(hver);
    }
    
    /**
     * Skilgreinir fjölda leikmanna sem eru að spila
     * @param fjoldi
     */
    public static void setFjoldi(int fjoldiInn) {
    	fjoldi = fjoldiInn;
    }
    
    public Ped getPed(int pedNumer) {
    	return pedArray[pedNumer];
    }
    
    public void endurstillaLeikmann() {
		for(int i = 0; i < 4; i++) {
			pedArray[i].endurstillaPed(-1);    			
		}
		leikHafid = false;
		pedCounter = 0;
    }
    
    /**
     * Fer yfir öll peð leikmann og ákvarðar hvort leikmaður hefur sigrað.
     * @return Satt ef leikmaður hefur sigrað.
     */
    public boolean erSigurvegari() {
		boolean sigurvegari = false;
    	for(Ped i: pedArray) {
    		if(i.getErSigrari())
				sigurvegari = true;
    		else return false;
    	}
    	return sigurvegari;
    }


//    public static void hreyfaBleikann(int newTileIndex) {
//        for (StackPane tile : Controller.ludoBord.values()) {
//            tile.getChildren().remove(Controller.bleikurKall);
//        }
//        Controller.ludoBord.get(newTileIndex).getChildren().add(Controller.bleikurKall);
//    }


//
//    public static void hreyfaGraenann(int newTileIndex) {
//        for (StackPane tile : Controller.ludoBord.values()) {
//            tile.getChildren().remove(Controller.graennKall);
//        }
//        Controller.ludoBord.get(newTileIndex).getChildren().add(Controller.graennKall);
//    }
}
