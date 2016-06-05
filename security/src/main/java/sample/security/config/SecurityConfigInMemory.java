package sample.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
public class SecurityConfigInMemory extends WebSecurityConfigurerAdapter {

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
            .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                    // password = userpassword
                    .password("$2a$10$nodSZsM7Lyi34l3/YEg61uTVDRIgH.DkM/WF4AM0BKTCGINBOnFOm")
                    .authorities("ROLE_USER")
                    .and()
                .withUser("admin")
                    // password = adminpassword
                    .password("$2a$10$0VRu6ZC4zcuXZiS34AaPF.gDcbq25Z5lX01gnf97iBNdl4WeCbCXC")
                    .roles("ADMIN");
    }
}
