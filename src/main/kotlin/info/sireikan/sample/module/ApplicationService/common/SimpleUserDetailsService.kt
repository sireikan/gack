package info.sireikan.sample.module.ApplicationService.common

import info.sireikan.sample.module.domain.common.User
import org.springframework.security.core.userdetails.UserDetailsService
import info.sireikan.sample.module.domain.repository.UserRepositoryInterface
import info.sireikan.sample.module.infrastructure.common.SimpleLoginUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.transaction.annotation.Transactional

class SimpleUserDetailsService : UserDetailsService {
    var userRepositoryInterface : UserRepositoryInterface

    constructor(userRepositoryInterface : UserRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface
    }

    @Transactional(readOnly = true)
    @Override
    public override fun loadUserByUsername(email: String): UserDetails {
        assert(email != null)
        return userRepositoryInterface.findByEmail(email).map { user: User -> SimpleLoginUser.create(user) }.orElseThrow { UsernameNotFoundException("User not found") }
    }

}
