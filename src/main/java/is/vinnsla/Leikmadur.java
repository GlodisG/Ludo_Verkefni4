package is.vinnsla;
import java.util.Arrays;
import java.util.HashMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Lýsing  : Innheldur hver á að gera og breytir því
 *
 *****************************************************************************/

public class Leikmadur {
    private static final IntegerProperty hvadaKall = new SimpleIntegerProperty(1);
    private final Ped[] pedFylki;
    private static int fjoldi;
    private static boolean leikHafid = false;
    private int pedTeljari = 0; //placeholder teljari fyrir peð

    /**
     * Smiður fyrir leikmann, inniheldur fylki af 4 peðum.
     * @param nafn Valkvætt nafn leikmanns
     * @param leikmadurNumer Númer leikmanns, merkir peðin hans.
     */
    public Leikmadur(String nafn, int leikmadurNumer) {
    	pedFylki = new Ped[4];
    	for(int i = 0; i < pedFylki.length; i++) {
    		switch(leikmadurNumer) {
    		case 0 -> pedFylki[i] = new Ped(nafn);
    		case 1 -> pedFylki[i] = new Ped(nafn);
			case 2 -> pedFylki[i] = new Ped(nafn);
			case 3 -> pedFylki[i] = new Ped(nafn);
    		}
    	}
    }

    /**
     * Færir peð leikmanns á leikborði
     * @param teningur Vegalengdin sem á að ferðast um
     * @param ped númer peðs sem á að hreyfa
     * @param leikmadurNumer Númer leikmanns sem á að hreyfa
     */
    public void faeraLeikmann(int teningur, int ped, int leikmadurNumer) {
    	if(!pedFylki[pedTeljari].getErSigrari()) {
    		if(!pedFylki[pedTeljari].getABordi())
    			pedFylki[pedTeljari].setABordi(true);
    		
    		pedFylki[pedTeljari].faeraPed(teningur);
    		
    		if(pedFylki[pedTeljari].getStadsetning() >= 44) {
    			System.out.println(">> Ped komst i mark <<");
    			pedFylki[pedTeljari].setErSigrari(true);
    			pedFylki[pedTeljari].setABordi(false);
    			pedFylki[pedTeljari].felaPed();
    			pedTeljari++;
    		}    		
    	}
    }
    
    
     /**
      * Skilar hvaða leikmadur á næsta leik
      * @return númer leikmanns
      */
    public static int getNaestiLeikmadur() {
        return hvadaKall.get();
        
    }
    
    /**
     * Færir næsta leik á næsta leikmann, breytilegt eftir fjölda leikmanna
     * @param
     */
    public static void setNaestiLeikmadur(int[] fylki) {
    	int hverGera = hvadaKall.get();
    	if(!leikHafid) {
    		fjoldi = fylki.length;
			System.out.println("Fjöldi leikmanna: " + fjoldi + " | Hverjir virkir: " + Arrays.toString(fylki));
    		hvadaKall.set(fylki[0]);
			leikHafid = true;
    		return;
    	}
    	switch(fjoldi) {
	    	case 2:
	    	{
	    		if(hverGera == fylki[0]) hvadaKall.set(fylki[1]);
	    		if(hverGera == fylki[1]) hvadaKall.set(fylki[0]);
	    		break;
	    	}
	    	case 3:
	    	{
	    		if(hverGera == fylki[0]) hvadaKall.set(fylki[1]);
	    		if(hverGera == fylki[1]) hvadaKall.set(fylki[2]);
	    		if(hverGera == fylki[2]) hvadaKall.set(fylki[0]);
	    		break;
	    	}
	    	case 4: 
	    	{
	    		if(hverGera == fylki[0]) hvadaKall.set(fylki[1]);
	    		if(hverGera == fylki[1]) hvadaKall.set(fylki[2]);
	    		if(hverGera == fylki[2]) hvadaKall.set(fylki[3]);
	    		if(hverGera == fylki[3]) hvadaKall.set(fylki[0]);
	    		break;
	    	}
    	}  	
    }

    /**
     * Lætur leikmann 1 gera, notað til að hefja nýjann leik
     */
    public static void setLeikmadur(int hver){
        hvadaKall.set(hver);
    }
    
    /**
     * Skilgreinir fjölda leikmanna sem eru að spila
     * @param fjoldiInn
     */
    public static void setFjoldi(int fjoldiInn) {
    	fjoldi = fjoldiInn;
    }
    
    public Ped getPed(int pedNumer) {
    	return pedFylki[pedNumer];
    }
    
    public void endurstillaLeikmann() {
		for(int i = 0; i < 4; i++) {
			pedFylki[i].endurstillaPed(-1);    			
		}
		leikHafid = false;
		pedTeljari = 0;
    }
    
    /**
     * Fer yfir öll peð leikmann og ákvarðar hvort leikmaður hefur sigrað.
     * @return Satt ef leikmaður hefur sigrað.
     */
    public boolean erSigurvegari() {
		boolean sigurvegari = false;
    	for(Ped i: pedFylki) {
    		if(i.getErSigrari())
				sigurvegari = true;
    		else return false;
    	}
    	return sigurvegari;
    }
}
