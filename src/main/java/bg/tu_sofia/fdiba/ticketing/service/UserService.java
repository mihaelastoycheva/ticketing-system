package bg.tu_sofia.fdiba.ticketing.service;

import bg.tu_sofia.fdiba.ticketing.dao.UserRepository;
import bg.tu_sofia.fdiba.ticketing.exception.UserAlreadyExistsException;
import bg.tu_sofia.fdiba.ticketing.exception.UserNotFoundException;
import bg.tu_sofia.fdiba.ticketing.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> registerUser(final User user) {
        final String username = user.getUsername();

        final Optional<User> optionalUser = this.userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        this.userRepository.save(user);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> loginUser(final String username, final String password) {
        final User user = this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
