package ubb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ubb.service.EmployeeService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordConfig passwordConfig;

    @Autowired
    private final EmployeeService applicationUserService;

    public WebSecurityConfig(PasswordConfig passwordConfig, EmployeeService applicationUserService) {
        this.passwordConfig = passwordConfig;
        this.applicationUserService = applicationUserService;
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
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.css").permitAll()

                .antMatchers("/admin/**")
                    .hasAnyRole(ApplicationUserRole.ADMIN.name())
                .antMatchers("/tester/**")
                    .hasAnyRole(ApplicationUserRole.TESTER.name())
                .antMatchers("/programmer/**")
                        .hasAnyRole(ApplicationUserRole.PROGRAMMER.name())
                // gives access-permission only for admin for all paths starting with /admin/{var}
                .antMatchers("/manager/**")
                        .hasAnyRole(ApplicationUserRole.MANAGER.name())
                // gives access-permission to read details
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

}



