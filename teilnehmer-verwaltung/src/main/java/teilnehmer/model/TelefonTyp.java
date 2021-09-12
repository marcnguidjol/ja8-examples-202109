package teilnehmer.model;

public enum TelefonTyp {
	
	PRIVAT("P"), GESCHAEFTLICH("G"), MOBILE("M");
	
	private String praefix;
	
	TelefonTyp(final String bezeichnung) {
		this.praefix = bezeichnung;
	}
	
	public String getBezeichnung() {
		return this.praefix;
	}
}
