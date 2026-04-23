package is.vinnsla;

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
	public void endurstillaPed(int upphafsReitur) {
		stadur.set(upphafsReitur);
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
	public SimpleIntegerProperty stadurProperty() {
		return stadur;
	}
	public void felaPed() {
		stadur.set(99);
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
