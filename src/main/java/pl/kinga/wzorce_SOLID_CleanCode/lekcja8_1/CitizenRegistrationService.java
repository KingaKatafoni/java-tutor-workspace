package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

public class CitizenRegistrationService {

    private final CitizenRepository citizenRepository;
    private final CitizenDataValidator dataValidator;
    private final CitizenLogFormatter logFormatter;
    private final ConfirmationFormatter confirmationFormatter;
    private final CitizenIdGenerator idGenerator;

    public CitizenRegistrationService(CitizenRepository citizenRepository, CitizenDataValidator dataValidator, CitizenLogFormatter logFormatter, ConfirmationFormatter confirmationFormatter, CitizenIdGenerator idGenerator) {
        this.citizenRepository = citizenRepository;
        this.dataValidator = dataValidator;
        this.logFormatter = logFormatter;
        this.confirmationFormatter = confirmationFormatter;
        this.idGenerator = idGenerator;
    }

    public String registerCitizen(String pesel, String firstName, String lastName, int age, String city) {
        dataValidator.validatePesel(pesel);
        dataValidator.validateAge(age);
        dataValidator.validateFirstName(firstName);
        dataValidator.validateLastName(lastName);

        Citizen citizen = new Citizen(pesel, firstName, lastName, age, city);

        citizenRepository.save(citizen);
        logFormatter.formatLog(citizen);
        String generatedId = idGenerator.generateId(pesel);
        return confirmationFormatter.formatConfirmation(citizen, generatedId);
    }
}
