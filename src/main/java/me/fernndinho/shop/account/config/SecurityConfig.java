package me.fernndinho.shop.account.config;

import me.fernndinho.shop.account.filters.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception { //TODO: configure this correctly
        httpSecurity.cors().and().csrf().disable();
        httpSecurity
                .httpBasic()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests().anyRequest().permitAll();

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
