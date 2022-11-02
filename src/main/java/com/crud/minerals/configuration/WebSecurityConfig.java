package com.crud.minerals.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/")
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**")
                .antMatchers("/mineral_search")
                .antMatchers("/mineral_search/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/admin_panel").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/admin_login")
                .defaultSuccessUrl("/admin_panel")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/admin_login").permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("123")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
