package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/submit")
				.loginPage("/login")
				.defaultSuccessUrl("/item-list", true)
				.failureUrl("/login?error")
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/login"))
				.authorizeHttpRequests(
						authz -> authz
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
								.requestMatchers("/login").permitAll()
								.requestMatchers("/item-list/**").permitAll()
								.requestMatchers("/register").permitAll()
								.requestMatchers("/search/big").permitAll()
								.requestMatchers("/search/searchByParentId").permitAll()
								.requestMatchers("/register/save-user").permitAll()
								.anyRequest().authenticated())
				.userDetailsService(userDetailsService);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
