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

    /**
     *Spilar leikinn
     * Kastar tening
     * skoðar hvaða leikmaður er að gera og bætir við leiðina
     */
    public static void leikaLeik() {
        teningur.kasta();

        //bætir í leið eftir hver er að gera
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
    public static void endurstillaLeid() {
        bleikurLeid.set(0);
        graennLeid.set(0);
    }
}
