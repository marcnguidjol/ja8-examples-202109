package teilnehmer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
public class Telefon {
	
//	private String landerCode;
//	private String vorwahl;
	private String nummer;
	private TelefonTyp typ;
	

}
