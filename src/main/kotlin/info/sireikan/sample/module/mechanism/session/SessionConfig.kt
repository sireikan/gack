package info.sireikan.sample.module.mechanism.session

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer


@RequiredArgsConstructor
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
class SessionConfig : AbstractHttpSessionApplicationInitializer() {
    @Value("\${spring.redis.host}")
    private val host: String? = null

    @Value("\${spring.redis.port}")
    private val port: Int? = null

    @Value("\${spring.redis.password}")
    private val password: String? = null
    @Bean
    fun lettuceConnectionFactory(): RedisConnectionFactory {
        val standaloneConfiguration = RedisStandaloneConfiguration(host!!, port!!)
        standaloneConfiguration.password = RedisPassword.of(password)
        return LettuceConnectionFactory(standaloneConfiguration)
    }
}
