package info.sireikan.sample.module.infrastructure.repository

import info.sireikan.sample.module.domain.common.User
import info.sireikan.sample.module.domain.repository.UserRepositoryInterface
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

class UserRepository {

    private var userRepository: UserRepositoryInterface
    private var passwordEncoder: PasswordEncoder

    constructor(userRepository: UserRepositoryInterface, passwordEncoder: PasswordEncoder) {
        this.userRepository = userRepository
        this.passwordEncoder = passwordEncoder
    }

    @Transactional(readOnly = true)
    fun findByEmail(email: String) : Optional<User> {
        return this.userRepository.findByEmail(email)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<User> {
        return this.userRepository.findAll()
    }

    fun register(name: String, email: String, password: String, roles: List<String>) {
        if (this.userRepository.findByEmail(email).isPresent) {
            throw RuntimeException("invalid.")
        }
        var encodedPassword = this.passwordEncoder.encode(password)
        var joinedRoles = joinRoles(roles)
        var user: User = User(null, name, email, encodedPassword, joinedRoles, true)
        this.userRepository.saveAndFlush(user)
    }

    private fun joinRoles(roles: List<String>): String {
        if (roles == null || roles.isEmpty()) {
            return ""
        }
        return roles.stream().map { it.trim() }.map { it.toUpperCase() }.collect(Collectors.joining(","))
    }
}
