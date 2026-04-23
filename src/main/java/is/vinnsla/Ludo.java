package is.vinnsla;
import is.vidmot.controller.ValmyndController;
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
    public static final SimpleBooleanProperty leikLokid = new SimpleBooleanProperty(false);
    private final static int LEIKMADUR2HLIDRUN = 10;
	private final static int LEIKMADUR3HLIDRUN = 20;
	private final static int LEIKMADUR4HLIDRUN = 30;
	private final static int LOKAHLIDRUN = 4;
	private final static int[] LEIKMADUR1UPPHAF = {57,58,59,60};
	private final static int[] LEIKMADUR2UPPHAF = {61,62,63,64}; 
	private final static int[] LEIKMADUR3UPPHAF = {69,70,71,72};
	private final static int[] LEIKMADUR4UPPHAF = {65,66,67,68};
    private Leikmadur[] leikmenn;
	private int[] hverjirVirkir;
	
	public Ludo(int fjoldi, String[] nofn, int[] hverjirVirkir) {
		this.hverjirVirkir = hverjirVirkir;
		leikmenn = new Leikmadur[fjoldi];
		for(int i = 0; i < fjoldi; i++) {
			leikmenn[i] = new Leikmadur(nofn[i], i);
		}
	}
     
    
    /**
     *Spilar leikinn
     * Kastar tening
     * skoðar hvaða leikmaður er að gera og bætir við leiðina
     */
    public void leikaLeik() {
        teningur.kasta();
        Leikmadur.setNaestiLeikmadur(hverjirVirkir);
        int leikUmferd = Leikmadur.getNaestiLeikmadur();
        
        //bætir í leið eftir hver er að gera
        //reitur.faeraLeikmann(leikUmferd, teningur.getTala());
        leikmenn[leikUmferd].faeraLeikmann(teningur.getTala(), 0, leikUmferd); // Vill á endanum nota þessa aðferð
        
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
    }
    
    /**
     * @param leikmadurNumer Númer leikmanns
     * @return Leikmaður
     */
    public Leikmadur getLeikmadur(int leikmadurNumer) {
    	return leikmenn[leikmadurNumer];
    }
    
    public Leikmadur[] getLeikmenn() {
    	return leikmenn;
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
    	for(Leikmadur i : leikmenn) {
    		i.endurstillaLeikmann();
    	}
    }


	/**
	 * Finnur hvar leikmaður er staðsettur út frá hans offset
	 * Tekur vegalengd sem peð hefur ferðast og umbreytir í raunstöðu á leikborði
	 * @param leikmadur Númer leikmanns sem nær í reitinn
	 * @param stadsetning Staðsetning sem þarf að finna
	 * @param ped Númer peðs
	 * @return raunveruleg staðsetning peð leikmanns
	 */
	public static int getReitur(int leikmadur, int ped,  int stadsetning) {
		switch(leikmadur) {
	    	case 1 : {
	    		if(stadsetning == -1) return LEIKMADUR1UPPHAF[ped];
	    		return stadsetning;
	    	}
			case 2 : {
				if(stadsetning == -1) return LEIKMADUR2UPPHAF[ped];
				if(stadsetning >= 40) {
					return stadsetning + LOKAHLIDRUN * (LEIKMADUR2HLIDRUN/10);
				}
				if(stadsetning + LEIKMADUR2HLIDRUN >= 40) {
					return (stadsetning + LEIKMADUR2HLIDRUN) % 40;
				}
				return stadsetning + LEIKMADUR2HLIDRUN;
			}
			case 3 : {
				if(stadsetning == -1) return LEIKMADUR3UPPHAF[ped];
				if(stadsetning >= 40) {
					return stadsetning + LOKAHLIDRUN * (LEIKMADUR3HLIDRUN/10);
				}
				if(stadsetning + LEIKMADUR3HLIDRUN >= 40) {
					return (stadsetning + LEIKMADUR3HLIDRUN) % 40;
				}
				return stadsetning + LEIKMADUR3HLIDRUN;
			}
			case 4 : {
				if(stadsetning == -1) return LEIKMADUR4UPPHAF[ped];
				if(stadsetning >= 	 40) {
					return stadsetning + LOKAHLIDRUN * (LEIKMADUR4HLIDRUN/10);
				}
				if(stadsetning + LEIKMADUR4HLIDRUN >= 40) {
					return (stadsetning + LEIKMADUR4HLIDRUN) % 40;
				}
				return stadsetning + LEIKMADUR4HLIDRUN;
			}
			default : return 0;
		}    	
	}
}
