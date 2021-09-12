package teilnehmer.model;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
public class Teilnehmer {

	private @Valid FamilienName name;
	private String vorname;
	private String email;
	private Firma firma;
	private @Valid KontaktDaten kontaktDaten;

}
