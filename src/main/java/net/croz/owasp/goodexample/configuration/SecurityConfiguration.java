package net.croz.owasp.goodexample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //TODO: ovaj headers je za h2-konzolu
            .headers().frameOptions().disable()
            .and()
            .csrf()
                .ignoringAntMatchers("/auth/login", "/auth/password-reset", "/h2-console/**", "/product/**")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .formLogin()
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/index.html?error=true")
            .and()
            .authorizeRequests()
                .antMatchers("/auth/login", "/auth/password-reset", "/h2-console/**", "/product/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .logout()
                .logoutUrl("/auth/logout")
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES)))
                .deleteCookies("JSESSIONID", "X-XSRF-TOKEN")
            .and()
            .sessionManagement()
                .maximumSessions(1);

        return http.build();
    }

}
