package com.food.securitymanager.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityManagerConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		 --> this is for default schema with given user(s)
		auth.jdbcAuthentication()
				.withDefaultSchema()
				.withUser(
				User.withUsername("inmemUser")
					.password("inmemPwd")
					.roles("in_mem_user")
		)
		.withUser(
			User.withUsername("inmemUser1")
			.password("inmemPwd1")
			.roles("in_mem_user")
		);
		// --> this is for jdbc with given dataSource.Data source will contain users and authentication info
//			auth.jdbcAuthentication()
//			.dataSource(dataSource);
	}	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//permitAll to all roles
		//hasRole for single role
		//hasAnyRole for multiple roles to specify
		http.authorizeRequests()
			.antMatchers("/**")
			.permitAll();
	}
	

}
