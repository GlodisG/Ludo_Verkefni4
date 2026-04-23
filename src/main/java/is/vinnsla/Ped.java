package is.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

public class Ped {
	private boolean erABordi = false;
	private boolean erSigrari = false;
	private final String leikmadur;
	private final SimpleIntegerProperty stadur = new SimpleIntegerProperty(-1);

	/**
	 * Skilgreinir leikmann
	 * @param leikmadur
	 */
	public Ped(String leikmadur) {
		this.leikmadur = leikmadur;
	}

	/**
	 * Stillir staður breytuna
	 * @param teningur
	 */
	public void faeraPed(int teningur) {
		stadur.set(stadur.get() + teningur);
	}

	/**
	 * Setur peð á upphafsreit og ensurstillir breytur fyrir nýjan leik
	 * @param upphafsReitur
	 */
	public void endurstillaPed(int upphafsReitur) {
		stadur.set(upphafsReitur);
		erABordi = false;
		erSigrari = false;
	}

	/**
	 * Athugar hvort peð sé á leikborðinu
	 * @return
	 */
	public boolean getABordi() {
		return erABordi;
	}

	/**
	 * Stillir þannig að peð er á leikborði
	 * @param erABordi
	 */
	public void setABordi(boolean erABordi) {
		this.erABordi = erABordi;
	}

	/**
	 * Athugar hvort peð sé komið í mark
	 * @return
	 */
	public boolean getErSigrari() {
		return erSigrari;
	}

	/**
	 * Stillir hvort peð sé komið í mark
	 * @param erSigrari
	 */
	public void setErSigrari(boolean erSigrari) {
		this.erSigrari = erSigrari;
	}

	/**
	 * Skilar staðsetningu sem heiltölu
	 * @return
	 */
	public int getStadsetning() {
		return stadur.intValue();
	}

	/**
	 * Skilar property á staður breytunni
	 * @return
	 */
	public SimpleIntegerProperty stadurProperty() {
		return stadur;
	}

	/**
	 * Styllir staðsetningu peðs út fyrir leikvöll til að fela
	 */
	public void felaPed() {
		stadur.set(99);
	}

	@Override
	public String toString() {
		return 
				"Nafn(" + leikmadur + 
				"): Stadsetning: " + stadur.get() +
				" (Sigurvegari: " + (erSigrari ? "Ja" : "Nei") +
				") (A Bordi: " + (erABordi ? "Ja" : "Nei") + ")"; 
	}
}
