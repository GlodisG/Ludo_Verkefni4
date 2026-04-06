package is.vinnsla;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Lýsing  : Kallar á önnur föll og spilar leik
 *
 *****************************************************************************/
public class Ludo {
    private static final Teningur teningur = new Teningur();
    public static final IntegerProperty bleikurLeid = new SimpleIntegerProperty(0);
    public static final IntegerProperty graennLeid = new SimpleIntegerProperty(0);
    public static final SimpleBooleanProperty leikLokid = new SimpleBooleanProperty(false);
    
    
    private static final Reitur reitur = new Reitur();
    private static int leikUmferd = 1;
    private static int fjoldiLeikmanna = 2; //tala úr upphafsskjá
    private Leikmadur[] leikmenn = new Leikmadur[fjoldiLeikmanna];
	private String[] nofn = {"Leikmaður 1", "Leikmaður 2"};
	
	public Ludo() {
		for(int i = 0; i < fjoldiLeikmanna; i++) {
			leikmenn[i] = new Leikmadur(nofn[i]);
		}
	}
     
    
    /**
     *Spilar leikinn
     * Kastar tening
     * skoðar hvaða leikmaður er að gera og bætir við leiðina
     */
    public void leikaLeik() {
        teningur.kasta();
        if(leikUmferd >= fjoldiLeikmanna)
        	leikUmferd = 1;
        //Leikmadur.setLeikmadur(leikUmferd);
        
        
        
        //bætir í leið eftir hver er að gera
        reitur.faeraLeikmann(leikUmferd, teningur.getTala());
        leikmenn[leikUmferd-1].faeraLeikmann(teningur.getTala(), 0, leikUmferd); // Vill á endanum nota þessa aðferð
        
        leikUmferd++;
        
        // Leikstaða fyrir debugging -----------------------------------------
        System.out.println("Leikstada:\n");
        int cnt = 1;
        for(Leikmadur i : leikmenn) {
        	System.out.println("Leikmadur " + cnt++);
        	for(int j = 0; j < 4; j++) {
        		System.out.println("Ped " + (j + 1) + ": " + i.getPed(j).toString());
        	}
        	System.out.println();
        }
        // -------------------------------------------------------------------
        
        if(Leikmadur.hvadaKall()==2){
            graennLeid.set(graennLeid.get()+teningur.getTala());
        } else {
            bleikurLeid.set(bleikurLeid.get()+teningur.getTala());
        }

        //Skoðar hvort annar spilari vann
        if (graennLeid.get()>=45){
            //láta grænann vinna og enda leik
            setLeikLokid(true);
        }
        if (bleikurLeid.get()>=45) {
            //láta bleikann vinna og enda leik
            setLeikLokid(true);
        }
        if(leikmenn[leikUmferd-1].erSigurvegari()) {
        	setLeikLokid(true);
        }
        
        
        
    }
    
    /**
     * @param leikmadurNumer Númer leikmanns
     * @return Leikmaður
     */
    public Leikmadur getLeikmadur(int leikmadurNumer) {
    	return leikmenn[leikmadurNumer-1];
    }

    /**
     * @return int //skilar hvar á leiðinni bleikur er
     */
    public static int getBleikurLeid(){
        return bleikurLeid.get();
    }

    /**
     * @return int //skilar hvar á leiðinni grænn er
     */
    public static int getGraennLeid(){
        return graennLeid.get();
    }

    /**
     * @return Teningur //Skilar tening.
     */
    public static Teningur getTeningur() {
        return teningur;
    }

    /**
     * @return boolean //segir til hvort leik sé lokið eða ekki
     */
    public static boolean getLeikLokid(){
        return leikLokid.get();
    }

    /**
     * @param a //tekur in boolean gildi til að skrá hvort leik sé lokið
     */
    public static void setLeikLokid(boolean a){
        leikLokid.set(a);
    }

    /**
     * @return booleanProperty //skilar hvort leik sé lokið
     */
    public static BooleanProperty leikLokidProperty() {
        return leikLokid;
    }

    /**
     * Núllstillir hvar bleikur og grænn eru
     */
    public void endurstillaLeid() {
        bleikurLeid.set(0);
        graennLeid.set(0);
        for(Leikmadur i: leikmenn) {
        	i.endurstillaLeikmann();
        }
    }
}
