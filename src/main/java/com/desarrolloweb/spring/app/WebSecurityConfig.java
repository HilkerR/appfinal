package com.desarrolloweb.spring.app;

import com.desarrolloweb.spring.app.Services.UserDetailsServiceImpl;
import com.desarrolloweb.spring.app.util.LoginSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private LoginSuccessHandler successHandler;

    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/","/index").permitAll()
            .antMatchers("/protect/**").hasAnyAuthority("cliente")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .successHandler(successHandler)
                .permitAll()
                .loginPage("/login")
            .and()
            .logout()
                .permitAll();
        super.configure(http);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());
        //super.configure(auth);
    }

}