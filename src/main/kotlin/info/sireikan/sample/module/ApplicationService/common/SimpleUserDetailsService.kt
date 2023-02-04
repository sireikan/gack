package info.sireikan.sample.module.ApplicationService.common

import info.sireikan.sample.module.domain.common.User
import org.springframework.security.core.userdetails.UserDetailsService
import info.sireikan.sample.module.infrastructure.repository.UserRepository
import info.sireikan.sample.module.infrastructure.common.SimpleLoginUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimpleUserDetailsService : UserDetailsService {
    var userRepository : UserRepository

    constructor(userRepository : UserRepository) {
        this.userRepository = userRepository
    }

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email).map { user: User -> SimpleLoginUser.create(user) }.orElseThrow { UsernameNotFoundException("User not found") }
    }

}
