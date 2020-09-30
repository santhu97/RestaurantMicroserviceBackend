/**
 * 
 */
package com.mindtree.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author M1056182
 *
 */
@Configuration
//@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
          .antMatcher("/**").authorizeRequests()
          .antMatchers(new String[]{"/api/v1/user/getUserByEmail/**","/api/v1/user/home","/api/v1/user/anonymous/**"}).permitAll()
          .anyRequest().authenticated()
          .and()
          .oauth2Login();
		  http.csrf().disable();
	}
	
}
