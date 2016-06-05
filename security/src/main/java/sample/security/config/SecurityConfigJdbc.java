package sample.security.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfigJdbc extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(GET, "/login.jsp").permitAll()
                .antMatchers(POST, "/processLogin").permitAll()
                .antMatchers(POST, "/logout").authenticated()
                .antMatchers("/top.jsp").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login.jsp")
                .loginProcessingUrl("/processLogin")
                .defaultSuccessUrl("/top.jsp")
                .failureUrl("/login.jsp")
                .usernameParameter("paramLoginId")
                .passwordParameter("paramPassword")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/top.jsp")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/accessDenied.jsp")
                .and()
            .csrf();
    }

    @Autowired
    @Qualifier("authDataSource")
    private DataSource dataSource;

    private static final String USER_QUERY
        = "select LOGIN_ID, PASSWORD, true "
        + "from T_USER "
        + "where LOGIN_ID = ?";

    private static final String ROLES_QUERY
        = "select LOGIN_ID, ROLE_NAME "
        + "from T_ROLE "
        + "inner join T_USER_ROLE on T_ROLE.ID = T_USER_ROLE.ROLE_ID "
        + "inner join T_USER on T_USER_ROLE.USER_ID = T_USER.ID "
        + "where LOGIN_ID = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY);
    }
}
