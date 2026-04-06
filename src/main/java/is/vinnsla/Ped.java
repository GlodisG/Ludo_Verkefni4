package is.vinnsla;

import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;

public class Ped {
	private boolean erABordi = false;
	private boolean erSigrari = false;
	private final String leikmadur;
	private final SimpleIntegerProperty stadur = new SimpleIntegerProperty(-1);
	
	public Ped(String leikmadur) {
		this.leikmadur = leikmadur;
	}
	
	public void faeraPed(int teningur) {
		stadur.set(stadur.get() + teningur);
	}
	public void endurstillaPed() {
		stadur.set(-1);
		erABordi = false;
		erSigrari = false;
	}
	
	public void setABordi(boolean erABordi) {
		this.erABordi = erABordi;
	}
	public void setErSigrari(boolean erSigrari) {
		this.erSigrari = erSigrari;
	}
	
	public int getStadsetning() {
		return stadur.intValue();
	}
	
	public boolean getABordi() {
		return erABordi;
	}
	public boolean getErSigrari() {
		return erSigrari;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 
				"Nafn(" + leikmadur + 
				"): Stadsetning: " + stadur.get() +
				" (Sigurvegari: " + (erSigrari ? "Ja" : "Nei") +
				") (A Bordi: " + (erABordi ? "Ja" : "Nei") + ")"; 
	}
}
