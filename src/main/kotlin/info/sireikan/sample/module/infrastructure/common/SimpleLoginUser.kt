package info.sireikan.sample.module.infrastructure.common

import info.sireikan.sample.module.domain.common.User
import lombok.extern.slf4j.Slf4j
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

@Slf4j
class SimpleLoginUser : org.springframework.security.core.userdetails.User {
    private var user : User

    private constructor(
        user: User,
        username: String,
        password: String,
        enabled: Boolean,
        accountNonExpired: Boolean,
        credentialsNonExpired: Boolean,
        accountNonLocked: Boolean,
        authorities: Collection<GrantedAuthority>
    ) : super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
        this.user = user
    }

    companion object {
        fun create(user: User): SimpleLoginUser {
            return SimpleLoginUser(
                user,
                user.name,
                user.password,
                user.enable,
                true,
                true,
                true,
                convertGrantedAuthorities(user.roles)
            )
        }

        private fun convertGrantedAuthorities(roles: String): Collection<GrantedAuthority> {
            if (roles.isEmpty()) {
                return Collections.emptySet()
            }
            var roleList = roles.split(",")
            return roleList.stream().map { role -> SimpleGrantedAuthority(role) }.collect(Collectors.toSet())
        }
    }

    fun getUser() : User {
        return this.user
    }
}
