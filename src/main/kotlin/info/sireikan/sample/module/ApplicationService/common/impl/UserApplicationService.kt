package info.sireikan.sample.module.ApplicationService.common.impl

import info.sireikan.sample.module.ApplicationService.common.UserApplicationServiceInterface
import info.sireikan.sample.module.domain.common.User
import info.sireikan.sample.module.infrastructure.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*
import java.util.stream.Collectors

@Service
class UserApplicationService : UserApplicationServiceInterface {
    private var userRepository: UserRepository
    private var passwordEncoder: PasswordEncoder

    constructor(userRepository: UserRepository, passwordEncoder: PasswordEncoder) {
        this.userRepository = userRepository
        this.passwordEncoder = passwordEncoder
    }

    @Transactional(readOnly = true)
    override fun findByEmail(email: String) : Optional<User> {
        return this.userRepository.findByEmail(email)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<User> {
        return this.userRepository.findAll()
    }

    @Transactional
    override fun register(name: String, email: String, password: String, roles: List<String>) {
        if (this.userRepository.findByEmail(email).isPresent) {
            throw RuntimeException("invalid.")
        }
        var encodedPassword = this.passwordEncoder.encode(password)
        var joinedRoles = joinRoles(roles)
        var user: User = User(null, name, email, encodedPassword, joinedRoles, true)
        this.userRepository.saveAndFlush(user)
    }

    private fun joinRoles(roles: List<String>): String {
        if (roles.isEmpty()) {
            return ""
        }
        return roles.stream().map { it.trim() }.map { it.uppercase(Locale.getDefault()) }.collect(Collectors.joining(","))
    }
}
