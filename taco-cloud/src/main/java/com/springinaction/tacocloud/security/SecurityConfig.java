package com.springinaction.tacocloud.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  private final PasswordEncoder encoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/design", "/orders")
        .access("hasRole('USER')")
        .antMatchers("/**")
        .access("permitAll")
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/authenticate")
        .usernameParameter("user")
        .passwordParameter("pwd")
        .defaultSuccessUrl("/design")
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .csrf()
        .ignoringAntMatchers("/h2-console/**")

        // Allow pages to be loaded in frames from the same origin; needed for H2-Console
        .and()
        .headers()
        .frameOptions()
        .sameOrigin();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder);
  }
}
