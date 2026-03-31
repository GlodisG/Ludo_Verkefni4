package is.vinnsla;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Lýsing  : Innheldur hver á að gera og breytir því
 *
 *****************************************************************************/

public class Leikmadur {
    private static final IntegerProperty hvadaKall = new SimpleIntegerProperty(1);
    private final Ped[] pedArray;
    private final String litur;
    private final int leikmadurNumer;
    
    public Leikmadur(int leikmadurNumer, String litur) {
    	pedArray = new Ped[4];
    	for(int i = 0; i < pedArray.length; i++) {
    		pedArray[i] = new Ped();
    	}
    	this.leikmadurNumer = leikmadurNumer;
    	this.litur = litur;
    }
    /**
     * @return int //sækir hver á að gera úr hvadaKall breytunni og skilgreinir að hinn á næst að gera
     */
    public static int getLeikmadur() {
        int hverGera= hvadaKall.get();
        if (hverGera==1) {
            hvadaKall.set(2);
            return 2;
        }
        hvadaKall.set(1);
        return 1;
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
    public static void setLeikmadur(){
        hvadaKall.set(1);
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
