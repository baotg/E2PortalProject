package se.iuh.e2portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import se.iuh.e2portal.config.jwt.JwtAuthenticationEntryPoint;
import se.iuh.e2portal.config.jwt.JwtTokenAuthenticationFilter;
import se.iuh.e2portal.service.UserAccountService;

@EnableWebSecurity
public class MultiHttpSecurityConfig {
	@Autowired
    private UserAccountService userAccountService;
	
    @Configuration
    @Order(1)
    public class ApiSecurityAdapter extends WebSecurityConfigurerAdapter {
        @Bean
        public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
            return new JwtTokenAuthenticationFilter();
        }

        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                    .cors()
                    .and()
                    .csrf()
                    .disable().authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }
    
    @Configuration
    public class WebSecurityAdapter extends WebSecurityConfigurerAdapter{
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
         
        @Bean(BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }
        
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
            provider.setUserDetailsService(userAccountService);
            provider.setPasswordEncoder(passwordEncoder());
            auth.authenticationProvider(provider);
        }
        
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/login").permitAll().and().csrf().disable().authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("id")
                    .passwordParameter("password").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .permitAll();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**","/mif/**");
        }
    }
}
