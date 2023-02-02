package info.sireikan.sample.module.domain.common

import jakarta.persistence.*
import lombok.ToString


@Table(name = "user")
@Entity
@ToString(exclude = ["email", "password"])
class User() {
    constructor(
        id: Long?,
        name: String,
        email: String,
        password: String,
        roles : String,
        enable : Boolean
    ) : this() {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
        this.roles = roles
        this.enable = enable
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = 0

    @Column(name = "name", length = 60, nullable = false)
    lateinit var name : String

    @Column(name = "email", length = 120, nullable = false, unique = true)
    lateinit var email : String

    @Column(name = "password", length = 120, nullable = false)
    lateinit var password : String

    @Column(name = "roles", length = 120, nullable = false)
    lateinit var roles : String

    @Column(name = "enable_flag", nullable = false)
    var enable : Boolean = true
}
