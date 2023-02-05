package info.sireikan.sample.module.ApplicationService.common

import info.sireikan.sample.module.domain.common.User
import java.util.*

interface UserApplicationServiceInterface {
    fun findByEmail(email: String) : Optional<User>
    fun findAll(): List<User>
    fun register(name: String, email: String, password: String, roles: List<String>)
}
