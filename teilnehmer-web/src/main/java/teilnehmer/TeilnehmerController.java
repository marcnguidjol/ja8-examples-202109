package teilnehmer;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teilnehmer.model.Teilnehmer;
import teilnehmer.service.TeilnehmerService;

@RestController
@Slf4j
@NoArgsConstructor
public class TeilnehmerController {
	
	private TeilnehmerService teilnehmerService;
	
	@PostConstruct
	public void init() {
		this.teilnehmerService = new TeilnehmerService();
		log.debug("--- Spring Controller 'TeilnehmerController'.INIT fertig ---");
	}
	
	@GetMapping("/teilnehmer")
	public ResponseEntity<List<Teilnehmer>> alleTeilnehmer(){
		List<Teilnehmer> teilnehmerList = this.teilnehmerService.alleTn();
		log.debug("--- #Teilnehmer {} ---", teilnehmerList.size());
		return ResponseEntity.ok(teilnehmerList);
	}

}
