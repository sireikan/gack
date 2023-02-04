package info.sireikan.sample.module.ApplicationService.common

import info.sireikan.sample.module.domain.common.User
import info.sireikan.sample.module.infrastructure.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*
import java.util.stream.Collectors

interface UserApplicationServiceInterface {
    fun findByEmail(email: String) : Optional<User>
    fun findAll(): List<User>
    fun register(name: String, email: String, password: String, roles: List<String>)
}
