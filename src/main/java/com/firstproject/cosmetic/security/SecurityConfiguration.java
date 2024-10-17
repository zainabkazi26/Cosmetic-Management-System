package com.firstproject.cosmetic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.firstproject.cosmetic.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(makeupAuthProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider makeupAuthProvider() {
		DaoAuthenticationProvider makeupDao=new DaoAuthenticationProvider();
		makeupDao.setUserDetailsService(makeupUser());
		makeupDao.setPasswordEncoder(makeupPassword());
		return makeupDao;
	}
	@Bean
	public BCryptPasswordEncoder makeupPassword() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService makeupUser() {
		return new UserDetailsServiceImpl();
	}

	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		        http.authorizeRequests()
		            .antMatchers("/","/save","/showFormForAdd","/403").hasAnyAuthority("USER","ADMIN")
		            .antMatchers("/showFormForUpdate","/delete").hasAuthority("ADMIN")
		            .anyRequest().authenticated()
		            .and()
		            .formLogin().loginProcessingUrl("/login").successForwardUrl("/list").permitAll()
		            .and()
		            .logout().logoutSuccessUrl("/login").permitAll()
		            .and()
		            .exceptionHandling().accessDeniedPage("/403")
		            .and()
		            .cors().and().csrf().disable();
	}


}

