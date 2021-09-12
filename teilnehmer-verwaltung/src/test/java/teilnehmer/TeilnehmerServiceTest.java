package teilnehmer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import teilnehmer.model.Teilnehmer;
import teilnehmer.service.TeilnehmerService;

class TeilnehmerServiceTest {
	
	static TeilnehmerService teilnehmerService;
	
	@BeforeAll
	static void setUp() {
		teilnehmerService = new TeilnehmerService();
	}
	
	@Test
	@DisplayName("Unit-Test:selektiereTn")
	void testSelektiereTn() {
		Teilnehmer teilnehmer = teilnehmerService.selektiereTn();
		Assertions.assertThat(teilnehmer).as("check non null Teilnehmer").isNotNull();
	}
	
	@Test
	@DisplayName("Unit-Test:erzeugeTn-nicht-valid")
	void testNeuerTnMitUngueltigenName() {
		String name = "x";
		Teilnehmer teilnehmer = teilnehmerService.erzeugeTn(name, "Max", "gmail");
		Assertions.assertThat(teilnehmer).as("check null Teilnehmer").isNull();
	}

}
