package stepanyuk.productsandproducers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Bean
    public UserDetailsService userDetailService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("password").roles("USER","ADMIN").build());
        
        return manager;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/resources/**", "/home", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout").permitAll();
    }    
}
