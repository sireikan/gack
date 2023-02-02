package info.sireikan.sample.module.domain.repository

import info.sireikan.sample.module.domain.common.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepositoryInterface: JpaRepository<User, Long> {
    fun findByEmail(email: String) : Optional<User>
//    fun register(name: String, email: String, password: String, roles: List<String>)
}
