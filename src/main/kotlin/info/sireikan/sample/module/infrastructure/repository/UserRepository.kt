package info.sireikan.sample.module.infrastructure.repository

import info.sireikan.sample.module.domain.common.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String) : Optional<User>
}
