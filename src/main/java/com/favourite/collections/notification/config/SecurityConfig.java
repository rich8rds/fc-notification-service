/* Collections #2025 */
package com.favourite.collections.notification.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.security.SecureRandom;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {


	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {

		http.csrf(ServerHttpSecurity.CsrfSpec::disable).cors(Customizer.withDefaults())
				.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
						.anyExchange().permitAll())
				.exceptionHandling(handler -> handler.authenticationEntryPoint(new AuthEntryPoint()));
		return http.build();
	}


//	@Bean
//	public ReactiveAuthenticationManager authenticationManager() {
//		UserDetailsRepositoryReactiveAuthenticationManager authManager =
//				new UserDetailsRepositoryReactiveAuthenticationManager(appUserDetailsService);
//		authManager.setPasswordEncoder(passwordEncoder());
//		return authManager;
//	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(15, new SecureRandom());
	}

	@Bean
	public HttpMessageConverters messageConverters() {
		return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
	}

}
