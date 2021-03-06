package br.com.conteudou.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/cursos").permitAll()
                .antMatchers(HttpMethod.GET, "/materias").permitAll()
                .antMatchers(HttpMethod.GET, "/sub-materias").permitAll()
                .antMatchers(HttpMethod.GET, "/links").permitAll()
                .anyRequest().authenticated();
    }
}
