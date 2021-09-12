package teilnehmer.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.javafaker.Address;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import teilnehmer.model.Adresse;
import teilnehmer.model.FamilienName;
import teilnehmer.model.Firma;
import teilnehmer.model.KontaktDaten;
import teilnehmer.model.Teilnehmer;
import teilnehmer.model.Telefon;
import teilnehmer.model.TelefonTyp;

@Getter
@Slf4j
public final class TeilnehmerRepository {

	private static final int MAX_ANZAHL_TN_5 = 5;
	private static final String STR_EMAIL_SUFFIX_COM = ".com";
	private static final String STR_EMAIL_SEPARTOR = "@";
	private static final String STR_PUNKT = ".";

	private List<Teilnehmer> teilnehmer;
	private ValidatorFactory factory;
	private Validator validator;

	private TeilnehmerRepository() {
		this.teilnehmer = new ArrayList<>();
		this.factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
		final int randomAnzahlTn = new Random().nextInt(MAX_ANZAHL_TN_5);
		log.debug("--- Warteliste mit {} Teilnehmer ---");	
		generiereTn(randomAnzahlTn);
	}

	public static TeilnehmerRepository getInstanz() {
		return new TeilnehmerRepository();
	}

	private void generiereTn(final int anzahl) {

		Faker faker = new Faker(Locale.GERMANY);

		for (int i = 1; i <= anzahl; i++) {
			final String familienName = faker.name().lastName();
			final String vorname = faker.name().firstName();
			final String domainName = faker.internet().domainWord();
			Company company = faker.company();
			final String firma = company.name();
			Address address = faker.address();
			final String adrStrasse = address.streetName();
			final String adrHnr = address.buildingNumber();
			final String adrPlz = address.zipCode();
			final String adrLand = address.countryCode();
			final String adrOrt = address.cityName();
			PhoneNumber phoneNumber = faker.phoneNumber();
			final String pTelnr = phoneNumber.phoneNumber();
			final String gTelNr = faker.phoneNumber().phoneNumber();
			final String mTelNr = phoneNumber.cellPhone();
			//@formatter:off
			final String email = new StringBuilder(vorname)
					.append(STR_PUNKT)
					.append(familienName)
					.append(STR_EMAIL_SEPARTOR)
					.append(domainName)
					.append(STR_EMAIL_SUFFIX_COM)
					.toString();
			//@formatter:on
			Teilnehmer tn = neuerTn(familienName, vorname, email, 
					firma, pTelnr, gTelNr, mTelNr, adrStrasse, adrHnr, adrPlz, adrOrt, adrLand);
			if (null != tn) {
				log.debug("--- Registrierung von {} fertig ---", tn);
				teilnehmer.add(tn);
			}
		}

	}

	public Teilnehmer neuerTn(final String name, final String vornamen, final String email, 
			final String firma, final String pTelNr, final String gTelNr, final String mTelNr,
			final String str, final String hnr, final String plz, final String ort, final String land) {

		FamilienName familienName = new FamilienName(name);
		if (validateName(familienName)) {
			// @formatter:off
			Telefon pTel = null;
			if(StringUtils.isNotEmpty(pTelNr)){
				pTel = Telefon.builder().typ(TelefonTyp.PRIVAT).nummer(pTelNr).build();
			}
			Telefon gTel = null;
			if(StringUtils.isNotEmpty(gTelNr)){
				gTel = Telefon.builder().typ(TelefonTyp.GESCHAEFTLICH).nummer(gTelNr).build();
			}
			Telefon mTel = null;
			if(StringUtils.isNotEmpty(mTelNr)){
				mTel = Telefon.builder().typ(TelefonTyp.MOBILE).nummer(mTelNr).build();
			}
			KontaktDaten kontaktDaten = 
					KontaktDaten.builder()
					.email(email)
					.gTel(gTel)
					.pTel(pTel)
					.mTel(mTel)
					.build();
			Adresse adresse = Adresse.builder()
					.strasse(str)
					.hnr(hnr)
					.plz(plz)
					.ort(ort)
					.laenderIsoCode(land)
					.build();
			Firma unternehmen = 
					Firma.builder()
					.name(firma)
					.anschrift(adresse)
					.build();
			return Teilnehmer.builder()
					.vorname(vornamen)
					.name(familienName)
					.email(email)
					.firma(unternehmen)
					.kontaktDaten(kontaktDaten)
					.build();
			// @formatter:on
		}

		return null;
	}

	boolean validateName(final @Valid FamilienName name) {
		Set<ConstraintViolation<FamilienName>> validierungsfehler = validator.validate(name);
		if(CollectionUtils.isNotEmpty(validierungsfehler)) {
			validierungsfehler.stream()
				.forEach(f -> log.warn("--- Validierungsfehler:'{}' ---", f.getMessage()));
		}
		return CollectionUtils.isEmpty(validierungsfehler);
	}

}
