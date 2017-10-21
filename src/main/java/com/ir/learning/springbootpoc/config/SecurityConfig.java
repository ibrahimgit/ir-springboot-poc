package com.ir.learning.springbootpoc.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ir.learning.springbootpoc.jwt.JwtTokenManager;
import com.ir.learning.springbootpoc.security.CustomDaoAuthenticationProvider;
import com.ir.learning.springbootpoc.security.CustomJwtAuthenticationFilter;
import com.ir.learning.springbootpoc.security.RestAuthenticationEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.exceptionHandling()
			.authenticationEntryPoint(entrypoint())
		.and()
			.csrf()
			.disable()
			//.anonymous()
			//.disable()
			//.formLogin() // It uses UsernamePasswordAuthenticationFilter and LoginUrlAuthenticationEntryPoint
			.httpBasic() // It uses BasicAuthenticationFilter and BasicAuthenticationEntryPoint
		.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers("/authenticate").permitAll()
			.anyRequest().authenticated()
		.and()
			.addFilterBefore(customAuthenicationFilter(), BasicAuthenticationFilter.class);
			
			 
	}
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Saba").password("ibrahim").roles("LOVE");
	}*/
	
	
	private Filter customAuthenicationFilter() {
		CustomJwtAuthenticationFilter filter = new CustomJwtAuthenticationFilter(jwtTokenManager, entrypoint());
		return filter;
	}
	
	@Bean
	public CustomDaoAuthenticationProvider customProvider() {
		CustomDaoAuthenticationProvider provider = new CustomDaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public RestAuthenticationEntryPoint entrypoint() {
		return new RestAuthenticationEntryPoint();
	}
	
	/*@Bean
	public BasicAuthenticationEntryPoint entrypoint() {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("Learning");
		return entryPoint;
	}*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(customProvider());
		//.userDetailsService(userDetailsService);
		//.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
	

}
