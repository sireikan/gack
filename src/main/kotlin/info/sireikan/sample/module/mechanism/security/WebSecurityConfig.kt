package info.sireikan.sample.module.mechanism.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf().disable()
        http.headers { header ->
            header.frameOptions().disable()
        }
        http.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers("/", "/signup").permitAll()
                .requestMatchers("/members/user/**").hasRole("USER")
                .requestMatchers("/members/admin/**").hasRole("ADMIN")
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
        }
        http.formLogin { form ->
            form.defaultSuccessUrl("/")
        }
        http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/")
        return http.build()
    }
}
