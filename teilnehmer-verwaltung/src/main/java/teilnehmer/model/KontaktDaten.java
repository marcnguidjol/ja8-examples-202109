package teilnehmer.model;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public class KontaktDaten {
	
	private Telefon pTel;
	private Telefon mTel;
	private Telefon gTel;
	
	@Email(message = "Email should be valid")
	private String email;
	
}
