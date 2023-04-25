package com.bds.thuebds.security;

import com.bds.thuebds.filterSecurity.CustomAuthenticationFilter;
import com.bds.thuebds.filterSecurity.CustomAuthorizationFilter;
import com.bds.thuebds.repository.UserRepository;
import com.bds.thuebds.service.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordService passwordService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordService.passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
		corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
		corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization"));
//        corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization"));
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), userRepository);
//        custom login api url
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		http.csrf().disable();
//        http.cors().disable();
		http.sessionManagement().sessionCreationPolicy(STATELESS).and();
//      this line will accept some url that can be public without authentication
		http.authorizeRequests()
			.antMatchers("/v3/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**").permitAll()
			.antMatchers("/api/v1/post/list/**").permitAll()
			.antMatchers("/api/v1/post/{postId}/**").permitAll()
				.antMatchers("/api/v1/comment/**").permitAll()
				.antMatchers("/api/v1/user/new/**").permitAll()
				.antMatchers("/api/v1/search/filters").permitAll()
			.antMatchers("/**").hasAnyAuthority("admin", "mod", "guest", "member")
			.anyRequest().authenticated().and().cors().configurationSource(request -> corsConfiguration);
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
	}


}
