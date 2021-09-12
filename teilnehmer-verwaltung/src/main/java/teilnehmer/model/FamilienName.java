package teilnehmer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import validierung.Name;
import validierung.ValidierungContants;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FamilienName {

	//@formatter:off
	@Name(
		minLaenge = ValidierungContants.MIN_LAENGE_NAME, 
		maxLaenge = ValidierungContants.MAX_LAENGE_NAME
	)
	//@formatter:on
	private String value;

}
