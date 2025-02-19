package com.mindworx.alumnibackend.security.config;

import com.mindworx.alumnibackend.dao.IUserdao;
import com.mindworx.alumnibackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 
    private final UserService userService;
    private final PasswordEncoder bEncoder;


    @Autowired
    public WebSecurityConfig(UserService userService, PasswordEncoder bEncoder) {
        this.userService = userService;
        this.bEncoder = bEncoder;
 
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                 //temporary disable the rejection of postmapping for postman testing //form based must be enabled.
                 .authorizeRequests()
                    .antMatchers("/",
                                "/registration**",
                                 "/forgot-password**",
                                 "/styles/js/**",
                                 "/styles/fonts/**",
                                 "/styles/css/**",
                                 "/img/**",
                                 "/webjars/**") //control of client access. everyting from client test
                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll();

        
        }

        /**
         * 
         */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(daoAuthenticationProvider());
        
        
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
