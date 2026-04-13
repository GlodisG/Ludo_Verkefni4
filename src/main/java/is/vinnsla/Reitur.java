package is.vinnsla;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Lýsing  : Inniheldur leið leikmanns og segir hvenær leikmaður er kominn í mark
 *
 *****************************************************************************/

public class Reitur {
	private final SimpleIntegerProperty reiturPlayer1 = new SimpleIntegerProperty(-1);
	private final SimpleIntegerProperty reiturPlayer2 = new SimpleIntegerProperty(-1);
	private final SimpleIntegerProperty reiturPlayer3 = new SimpleIntegerProperty(-1);
	private final SimpleIntegerProperty reiturPlayer4 = new SimpleIntegerProperty(-1);
	private final static int PLAYER2OFFSET = 10;
	private final static int PLAYER3OFFSET = 20;
	private final static int PLAYER4OFFSET = 30;
	private final static int FINISHOFFSET = 4;
	private final static int[] PLAYER1START = {57,58,59,60};
	private final static int[] PLAYER2START = {61,62,63,64}; 
	private final static int[] PLAYER3START = {69,70,71,72};
	private final static int[] PLAYER4START = {65,66,67,68};
	
	
	/**
	 * Default constructor
	 */
	public Reitur() {}

	/**
     * Færir leikmann á leikborði
     * @param leikmadur númer leikmanns
     * @param teningur vegalend sem á að ferðast um
     */
    public  void faeraLeikmann(int leikmadur, int teningur) {
    	switch(leikmadur) {
	    	case 1 -> reiturPlayer1.set(reiturPlayer1.get()+teningur);
			case 2 -> reiturPlayer2.set(reiturPlayer2.get()+teningur);
			case 3 -> reiturPlayer3.set(reiturPlayer3.get()+teningur);
			case 4 -> reiturPlayer4.set(reiturPlayer4.get()+teningur);
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
	    		if(stadsetning == -1) return PLAYER1START[ped];
	    		return stadsetning;
	    	}
			case 2 : {
				if(stadsetning == -1) return PLAYER2START[ped];
				if(stadsetning >= 40) {
					return stadsetning + FINISHOFFSET * (PLAYER2OFFSET/10);
				}
				if(stadsetning + PLAYER2OFFSET >= 40) {
					return (stadsetning + PLAYER2OFFSET) % 40;
				}
				return stadsetning + PLAYER2OFFSET;
			}
			case 3 : {
				if(stadsetning == -1) return PLAYER3START[ped];
				if(stadsetning >= 40) {
					return stadsetning + FINISHOFFSET * (PLAYER3OFFSET/10);
				}
				if(stadsetning + PLAYER3OFFSET >= 40) {
					return (stadsetning + PLAYER3OFFSET) % 40;
				}
				return stadsetning + PLAYER3OFFSET;
			}
			case 4 : {
				if(stadsetning == -1) return PLAYER4START[ped];
				if(stadsetning >= 	 40) {
					return stadsetning + FINISHOFFSET * (PLAYER4OFFSET/10);
				}
				if(stadsetning + PLAYER4OFFSET >= 40) {
					return (stadsetning + PLAYER4OFFSET) % 40;
				}
				return stadsetning + PLAYER4OFFSET;
			}
			default : return 0;
    	}    	
    }
    
    public void endursetjaLeid() {
    	reiturPlayer1.set(-1);
    	reiturPlayer2.set(-1);
    	reiturPlayer3.set(-1);
    	reiturPlayer4.set(-1);
    }
}
