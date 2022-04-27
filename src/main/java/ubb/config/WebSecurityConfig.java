package ubb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ubb.service.EmployeeService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;

    private final EmployeeService applicationUserService;

    public WebSecurityConfig(PasswordConfig passwordConfig, EmployeeService applicationUserService) {
        this.passwordConfig = passwordConfig;
        this.applicationUserService = applicationUserService;
    }
    @Bean
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Provide AuthenticationServices to the class
     *
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());

        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder2().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder2().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder2().encode("adminPass")).roles("ADMIN");
    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/test/*").permitAll()
//                .antMatchers("/ceva").permitAll()
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }

}



