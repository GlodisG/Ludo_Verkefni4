package is.vinnsla;

/******************************************************************************
 *  Lýsing  : Inniheldur leið leikmanns og segir hvenær leikmaður er kominn í mark
 *
 *****************************************************************************/

public class Reitur {
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
}
