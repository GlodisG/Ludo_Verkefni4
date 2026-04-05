package is.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

/******************************************************************************
 *  Lýsing  : Inniheldur leið leikmanns og segir hvenær leikmaður er kominn í mark
 *
 *****************************************************************************/

public class Reitur {
	private final SimpleIntegerProperty reiturPlayer1 = new SimpleIntegerProperty(0);
	private final SimpleIntegerProperty reiturPlayer2 = new SimpleIntegerProperty(0);
	private final SimpleIntegerProperty reiturPlayer3 = new SimpleIntegerProperty(0);
	private final SimpleIntegerProperty reiturPlayer4 = new SimpleIntegerProperty(0);
	private final int PLAYER2OFFSET = 10;
	private final int PLAYER3OFFSET = 20;
	private final int PLAYER4OFFSET = 30;
	
    /**
     * @param teningur //hvar í fylkinu spilarinn er
     * @param kall //hvaða spilari
     * @return int //Skilar númer hvað reiturinn er sem spilarinn á að fara á
     */
    public static int Reitur(int teningur, int kall) {
        int[] fylki;
        if(kall==2) {
            fylki = new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43};
        } else {
            fylki = new int[] {10,11,12,13,14,15,16,17,18,19,0,1,2,3,4,5,6,7,8,9,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,0,1,2,3,4,5,6,7,8,9,44,45,46,47};
        }
        if(teningur>43) {
            return fylki[42];
        }

        System.out.println("Teningur fyrir " + kall + "er" + (teningur-1));
        return fylki[teningur-1];
    }
    
    public void faeraLeikmann(int leikmadur, int teningur) {
    	switch(leikmadur) {
	    	case 1 -> reiturPlayer1.set(reiturPlayer1.get()+teningur);
			case 2 -> reiturPlayer2.set(reiturPlayer2.get()+teningur);
    	}
    }
    
	/**
	 * @param leikmadur Númer leikmanns sem nær í reitinn
	 * @return int Skilar reit leikmanns
	 * Staða leikmanns ræðst á hvar hann byrjar á leikborðinu (OFFSET)
	 * og endalengja leikmanns er alltaf 4 lengra en leikmaður með næsta númer á undan.
	 */
    public int getReitur(int leikmadur) {
    	switch(leikmadur) {
	    	case 1 : return reiturPlayer1.get();
			case 2 : {
				if(reiturPlayer2.get() > 40) {
					return reiturPlayer2.get() + 4;
				}
				if(reiturPlayer2.get() + PLAYER2OFFSET >= 40) {
					return (reiturPlayer2.get() + PLAYER2OFFSET) % 40;
				}
				return reiturPlayer2.get() + PLAYER2OFFSET;
			}
			case 3 : {
				if(reiturPlayer3.get() > 40) {
					return reiturPlayer3.get() + 8;
				}
				if(reiturPlayer3.get() + PLAYER3OFFSET >= 40) {
					return (reiturPlayer3.get() + PLAYER3OFFSET) % 40;
				}
				return reiturPlayer3.get() + PLAYER3OFFSET;
			}
			case 4 : {
				if(reiturPlayer4.get() > 40) {
					return reiturPlayer4.get() + 12;
				}
				if(reiturPlayer4.get() + PLAYER4OFFSET >= 40) {
					return (reiturPlayer4.get() + PLAYER4OFFSET) % 40;
				}
				return reiturPlayer4.get() + PLAYER4OFFSET;
			}
			default : return 0;
    	}
    }
}
