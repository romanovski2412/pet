package com.example.pet.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("$2a$12$y0vvQUF8yPGSohQZ9symq.FYFyQ7qTzAZVQO0uwT3f4aWXSNuu9f2")  //password1
                .roles("USER")
                .and()
                .withUser("admin")
                .password("$2a$12$m.gI7tRpo3IMyiwVJW/qkONosxx5vzbHHT3PSWsJ3XTW5EPvnOayi") //password2
                .roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/delete**", "/update**", "/create**")
                .hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}
