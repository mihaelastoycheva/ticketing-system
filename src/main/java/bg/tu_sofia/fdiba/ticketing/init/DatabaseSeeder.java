package bg.tu_sofia.fdiba.ticketing.init;

import bg.tu_sofia.fdiba.ticketing.dao.UserRepository;
import bg.tu_sofia.fdiba.ticketing.enumeration.CardType;
import bg.tu_sofia.fdiba.ticketing.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
