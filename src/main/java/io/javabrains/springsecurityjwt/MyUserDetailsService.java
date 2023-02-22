package io.javabrains.springsecurityjwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.models.MyUserDetails;
import io.javabrains.springsecurityjwt.repo.UserRepository;
import io.javabrains.springsecurityjwt.repo.User;


@Service
public class MyUserDetailsService implements UserDetailsService {

	/*
	 * @Override public UserDetails loadUserByUsername(String s) throws
	 * UsernameNotFoundException { return new User("foo", "foo", new ArrayList<>());
	 * return User.builder() .username("foo") .password("foo") .roles("USER")
	 * .build();
	 * 
	 * }
	 */

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return user.map(MyUserDetails::new).get();
	}

}