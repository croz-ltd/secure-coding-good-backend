package net.croz.owasp.goodexample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //TODO: ovaj headers je za h2-konzolu
            .cors()
            .and()
            .headers().frameOptions().disable()
            .and()
            .csrf()
                .ignoringAntMatchers("/auth/login", "/auth/password-reset", "/h2-console/**")
                .csrfTokenRepository(getCsrfTokenRepository())
            .and()
            .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value()))
                .failureHandler((req, res, ex) -> res.setStatus(HttpStatus.UNAUTHORIZED.value()))
            .and()
            .authorizeRequests()
                .antMatchers("/auth/login", "/auth/password-reset", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .logout()
                .logoutUrl("/auth/logout")
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES)))
                .deleteCookies("JSESSIONID", "XSRF-TOKEN")
            .and()
            .sessionManagement()
                .maximumSessions(1);

        return http.build();
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        tokenRepository.setCookieDomain("owasp-guidelines-good.com");
        return tokenRepository;
    }

    //TODO: ispravi ovaj CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://owasp-guidelines-good.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
            .allowCredentials(true)
            .maxAge(3600);
    }


}
