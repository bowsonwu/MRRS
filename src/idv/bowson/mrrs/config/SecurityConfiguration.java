package idv.bowson.mrrs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import idv.bowson.mrrs.security.AuthTokenAuthenticationFilter;
import idv.bowson.mrrs.security.AuthTokenAuthenticationProvider;
import idv.bowson.mrrs.security.MRRSAuthenticationEntryPoint;

/**
 * Spring Security相關設定
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthTokenAuthenticationProvider authAuthenticationProvider() {
        return new AuthTokenAuthenticationProvider();
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenAuthenticationFilter authTokenAuthenticationFilter() throws Exception {
        AuthTokenAuthenticationFilter authTokenAuthenticationFilter = new AuthTokenAuthenticationFilter();
        return authTokenAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/logout").permitAll()
                .anyRequest().authenticated()
                // .and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/index")
                // .failureUrl("/login?error=true")
                // .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().csrf()
                .and()
                .addFilterBefore(this.authTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .authenticationEntryPoint(new MRRSAuthenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authAuthenticationProvider());
    }

}
