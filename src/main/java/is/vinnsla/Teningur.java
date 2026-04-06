package is.vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Random;

/******************************************************************************
 *  Lýsing  : Kastar teningi
 *
 *****************************************************************************/

public class Teningur {
    private static final int MAX = 6;
    private static final IntegerProperty talaProperty = new SimpleIntegerProperty(MAX);
    private final Random randomNum = new Random();

    public IntegerProperty talaProperty() {
        return talaProperty;
    }

    public void kasta() {
        this.talaProperty.set(((int)(Math.random()*MAX)+1));
    }

    public int getTala() {
        return talaProperty.get();
    }

    /**
     * Test forrit fyrir klasann
     * @param args
     */
    public static void main (String [] args) {
        Teningur t = new Teningur();
        System.out.println (t);
    }

}
