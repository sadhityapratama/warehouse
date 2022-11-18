package com.miniproject.warehouse.config;

import com.miniproject.warehouse.service.MyUserDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class GeneralConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .cors().and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/asset/**").hasAnyAuthority("admin","sa","employee")
                .mvcMatchers(HttpMethod.POST,"/asset/**").hasAnyAuthority("admin")
                .mvcMatchers(HttpMethod.PUT, "/asset/**").hasAuthority("admin")
                .mvcMatchers(HttpMethod.DELETE, "/asset/**").hasAnyAuthority("admin")
                .mvcMatchers(HttpMethod.GET, "/warehouse/**").hasAnyAuthority("admin","sa","employee")
                .mvcMatchers(HttpMethod.POST,"/warehouse/**").hasAnyAuthority("admin")
                .mvcMatchers(HttpMethod.PUT, "/warehouse/**").hasAuthority("admin")
                .mvcMatchers(HttpMethod.DELETE, "/warehouse/**").hasAnyAuthority("admin")
                .mvcMatchers(HttpMethod.GET, "/transaction/**").hasAnyAuthority("employee","admin")
                .mvcMatchers(HttpMethod.POST,"/transaction/**").hasAnyAuthority("employee")
                .mvcMatchers(HttpMethod.PUT, "/transaction/**").hasAuthority("employee")
                .mvcMatchers(HttpMethod.DELETE, "/transaction/**").hasAnyAuthority("employee")
                .mvcMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("sa")
                .mvcMatchers(HttpMethod.POST,"/user/**").hasAnyAuthority("sa")
                .mvcMatchers(HttpMethod.PUT, "/user/**").hasAuthority("sa")
                .mvcMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("sa")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:4100"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
