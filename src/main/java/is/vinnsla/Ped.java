package is.vinnsla;

import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;

public class Ped {
	private boolean erABordi = false;
	private boolean erSigrari = false;
	private final SimpleIntegerProperty stadur = new SimpleIntegerProperty(0);
	
	public Ped() {}
	
	public void faeraPed(int teningur) {
		stadur.set(stadur.get() + teningur);
	}
	public void endurstillaPed() {
		stadur.set(0);
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
}
