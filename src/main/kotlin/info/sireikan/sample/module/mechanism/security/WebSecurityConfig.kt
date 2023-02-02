package info.sireikan.sample.module.mechanism.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf().disable()
        http.headers { header ->
            header.frameOptions().disable()
        }
        http.authorizeHttpRequests { authorize ->
            authorize.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
        }
        http.formLogin { form ->
            form.defaultSuccessUrl("/")
        }
        http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/")
        return http.build()
    }

    @Bean
    fun configure(web: WebSecurity) {
        web.debug(false).ignoring().requestMatchers("/images/**", "/js/**", "/css/**")
    }
}
