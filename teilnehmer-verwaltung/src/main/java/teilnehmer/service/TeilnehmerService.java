package teilnehmer.service;

import java.util.List;
import java.util.Optional;

import teilnehmer.data.TeilnehmerRepository;
import teilnehmer.model.Teilnehmer;

public class TeilnehmerService {

	private TeilnehmerRepository teilnehmerRepository;

	public TeilnehmerService() {
		this.teilnehmerRepository = TeilnehmerRepository.getInstanz();
	}

	public List<Teilnehmer> alleTn(){
		return this.teilnehmerRepository.getTeilnehmer();
	}
	
	public Teilnehmer selektiereTn() {
		Teilnehmer teilnehmer = null;
		Optional<Teilnehmer> optional = this.teilnehmerRepository.getTeilnehmer().stream().findAny();
		if (optional.isPresent()) {
			teilnehmer = optional.get();
		}
		return teilnehmer;
	}
	
	public Teilnehmer erzeugeTn(final String name, final String vorname, final String email) {
		return this.teilnehmerRepository.neuerTn(name, vorname, email, null,
				null, null, null, null, null, null, null, null);
	}
	
}
