package com.workout.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workout.api.dto.UserDTO;
import com.workout.api.model.User;
import com.workout.api.repository.UserRepository;
import com.workout.api.security.SecurityUtils;
import com.workout.api.web.rest.errors.EmailAlreadyUsedException;
import com.workout.api.web.rest.vm.ManagedUserVM;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(UserDTO userDTO, String password) {
		
		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
			throw new EmailAlreadyUsedException();
		});
		User newUser = new User();
		String encryptedPassword = passwordEncoder.encode(password);
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setEmail(userDTO.getEmail().toLowerCase());
		// new user gets registration key
		userRepository.save(newUser);
		log.debug("User Information for User: {}", newUser);
		return newUser;
	}

	public User createUser(ManagedUserVM userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail().toLowerCase());
		String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		return user;
	}

	public void deleteUser(String email) {
		userRepository.findOneByEmailIgnoreCase(email).ifPresent(user -> {
			userRepository.delete(user);
			log.debug("Deleted User: {}", user);
		});
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByEmailIgnoreCase);
	}

}
