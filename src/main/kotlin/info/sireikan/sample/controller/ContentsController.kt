package info.sireikan.sample.controller

import info.sireikan.sample.module.ApplicationService.common.impl.ContentsService
import info.sireikan.sample.module.domain.common.User
import info.sireikan.sample.module.infrastructure.common.SimpleLoginUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping("members")
class ContentsController {
    var contentsService: ContentsService

    constructor(contentsService: ContentsService) {
        this.contentsService = contentsService
    }

    @GetMapping("/")
    fun any(principal: Principal) : String {
        val authentication: Authentication = principal as Authentication
        contentsService.doService()
        return "members/index"
    }

    @GetMapping("user")
    fun user(@AuthenticationPrincipal loginUser: SimpleLoginUser) : String {
        return "member/user/index"
    }

    @GetMapping("admin")
    fun admin(@AuthenticationPrincipal(expression = "user") user: User) : String {
        return "members/admin/index"
    }
}
