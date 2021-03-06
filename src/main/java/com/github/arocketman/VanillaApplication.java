package com.github.arocketman;

import com.github.arocketman.config.CustomUserDetails;
import com.github.arocketman.entities.Role;
import com.github.arocketman.entities.User;
import com.github.arocketman.repositories.UserRepository;
import com.github.arocketman.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@SpringBootApplication
public class VanillaApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(VanillaApplication.class, args);
	}

	/**
	 * Password grants are switched on by injecting an AuthenticationManager.
	 * Here, we setup the builder so that the userDetailsService is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
     */
//	@Autowired
//	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service) throws Exception {
//		//Setup a default user if db is empty
//		if (repository.count()==0)
//			service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
//		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
//	}
        
//        localhost:8181/private?access_token=8d4d9663-3dfb-4da3-94fe-8f64e5e20f79
        @Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception {
		if(repository.count()==0)
                    repository.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService((String s) -> new CustomUserDetails(repository.findByUsername(s)));
                
	}
        

//	/**
//	 * We return an istance of our CustomUserDetails.
//	 * @param repository
//	 * @return
//     */
//	private UserDetailsService userDetailsService(final UserRepository repository) {
//		return username -> new CustomUserDetails(repository.findByUsername(username));
//	}

}
