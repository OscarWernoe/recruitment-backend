package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.Role;
import se.kth.iv1201.recruitment.domain.SignUpRequest;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import se.kth.iv1201.recruitment.repository.RoleRepository;

/**
 * The service that specifies tasks to be performed by the domain layer.
 */
@Service
@Transactional
public class RecruitmentService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * A constructor for creating a RecruitmentService instance.
     *
     * @param personRepository The PersonRepository instance
     * @param roleRepository   The RoleRepository instance
     * @param passwordEncoder  The PasswordEncoder instance
     */
    @Autowired
    public RecruitmentService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new account and saves it in the database.
     *
     * @param signUpRequest
     * @throws Exception If the account isn't successfully created.
     */
    public void createApplicant(SignUpRequest signUpRequest) throws Exception {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Role role = roleRepository.findByName("applicant")
                .orElseThrow(Exception::new);
        Person person = new Person(signUpRequest.getName(), signUpRequest.getSurname(), signUpRequest.getEmail(), signUpRequest.getSsn(), signUpRequest.getUsername(), signUpRequest.getPassword(), role);
        personRepository.save(person);
    }
}
