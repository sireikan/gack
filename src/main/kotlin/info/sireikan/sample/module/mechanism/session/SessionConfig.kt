package info.sireikan.sample.module.mechanism.session

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer


@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
class SessionConfig : AbstractHttpSessionApplicationInitializer() {
    @Value("\${spring.data.redis.host}")
    private val host: String? = null

    @Value("\${spring.data.redis.port}")
    private val port: Int? = null

    @Value("\${spring.data.redis.password}")
    private val password: String? = null
    @Bean
    fun lettuceConnectionFactory(): RedisConnectionFactory {
        val standaloneConfiguration = RedisStandaloneConfiguration(host!!, port!!)
        standaloneConfiguration.password = RedisPassword.of(password)
        return LettuceConnectionFactory(standaloneConfiguration)
    }
}
