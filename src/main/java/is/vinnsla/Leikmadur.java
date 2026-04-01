package is.vinnsla;
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
    private final String nafn;
    private HashMap<Ped, Integer> virkPed = new HashMap<>();
    
    /**
     * Smiður fyrir leikmann, inniheldur fylki af 4 peðum.
     * @param nafn Valkvætt nafn leikmanns
     */
    public Leikmadur(String nafn) {
    	pedArray = new Ped[4];
    	for(int i = 0; i < pedArray.length; i++) {
    		pedArray[i] = new Ped();
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
    
    public void faeraLeikmann(int teningur, int ped) {
    	if(teningur == 6) {
    		//valkostur um að bæta við peði ?
    		//kannski hafa þann kóða í controller?
    	}
    	
    	/*
    	 * placeholder kóði, fer í gegnum fylkið, eitt peð í einu, þar til það hefur komist í mark
    	 * notar þá næsta peð eftir á o.s.frv.
    	 */
    	for(Ped i: pedArray) {
    		if(!i.getErSigrari()) {
    			i.faeraPed(teningur);
    			return;
    		}
    	}
    	
    }
    
    /**
     * @return int //sækir hver á að gera úr hvadaKall breytunni og skilgreinir að hinn á næst að gera
     */
    public static int getLeikmadur(int fjoldi) {
        int hverGera= hvadaKall.get();
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
        
        switch(fjoldi) {
	        case 2:
	        {
	        	if(hverGera == 1) hvadaKall.set(2);
	        	if(hverGera == 2) hvadaKall.set(1);
	        	break;
	        }
	        case 3:
	        {
	        	if(hverGera == 1) hvadaKall.set(2);
	        	if(hverGera == 2) hvadaKall.set(3);
	        	if(hverGera == 3) hvadaKall.set(1);
	        	break;
	        }
	        case 4: 
	        {
	        	if(hverGera == 1) hvadaKall.set(2);
	        	if(hverGera == 2) hvadaKall.set(3);
	        	if(hverGera == 3) hvadaKall.set(4);
	        	if(hverGera == 4) hvadaKall.set(1);
	        	break;
	        }
        }
        System.out.println("hvadaKall: " + hvadaKall.get());
        return hvadaKall.get();
        
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
