package ws;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Resource
	private UserDetailsService userService;
	
	@Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/","/home").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers(HttpMethod.POST, "/usuarios/crearDb").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers(HttpMethod.GET, "/espectaculos/**").permitAll()
            .antMatchers(HttpMethod.GET, "/establecimientos/**").permitAll()
            .antMatchers(HttpMethod.POST, "/usuarios/registrar/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll()
            .antMatchers(HttpMethod.GET, "/publicaciones/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/publicaciones/**").authenticated()
            .antMatchers(HttpMethod.POST, "/usuarios/**/friends/**").authenticated()
            .antMatchers(HttpMethod.GET, "/usuarios/**/friends/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/usuarios/**/friends/**").authenticated()
            .antMatchers(HttpMethod.GET, "/usuarios/**/posicion/**").authenticated()
            .antMatchers(HttpMethod.PUT, "/usuarios/**/posicion/**").authenticated()
            .antMatchers(HttpMethod.POST, "/usuarios/**").hasAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.PUT, "/usuarios/**").hasAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.GET, "/usuarios/**").hasAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/js/**").permitAll()
            .anyRequest().authenticated();

            //filtro JWT custom
        http
            .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new RestConfig().corsFilter(), ChannelProcessingFilter.class);
        
    }
    
}
