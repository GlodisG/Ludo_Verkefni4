package is.vinnsla;

public class Ped {
	private boolean erABordi = false;
	private boolean erSigrari = false;
	
	public Ped() {}
	
	public void setABordi(boolean erABordi) {
		this.erABordi = erABordi;
	}
	public void setErSigrari(boolean erSigrari) {
		this.erSigrari = erSigrari;
	}
	
	public boolean getABordi() {
		return erABordi;
	}
	public boolean getErSigrari() {
		return erSigrari;
	}
}
