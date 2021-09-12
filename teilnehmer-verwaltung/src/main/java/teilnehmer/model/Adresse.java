package teilnehmer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ToString
public class Adresse {
	
	private String strasse;
	private String hnr;
	private String plz;
	private String ort;
	private String laenderIsoCode;

}
