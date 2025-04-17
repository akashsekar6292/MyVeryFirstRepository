package com.example.EmbarkXSecurityCheck1;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration     //provide configuration to the application context.
@EnableWebSecurity	//to enable spring security features in the application
@EnableMethodSecurity
public class SecurityConfig {
	@Bean   //make defaultSecurityFilterChain available as a bean provider
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/h2-console/**").permitAll() 		
				.anyRequest().authenticated());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user=User.withUsername("user").password("{noop}userpassword").roles("USER").build();
		UserDetails admin=User.withUsername("admin").password("{noop}adminpassword").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user,admin);
	}
}