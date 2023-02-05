package info.sireikan.sample.module.ApplicationService.common.impl

import info.sireikan.sample.module.ApplicationService.common.ContentsServiceInterface
import info.sireikan.sample.module.infrastructure.common.SimpleLoginUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ContentsService : ContentsServiceInterface {

    override fun doService() {
        val context: SecurityContext = SecurityContextHolder.getContext()
        val authentication: Authentication = context.authentication
        val loginUser: SimpleLoginUser = authentication.principal as SimpleLoginUser
    }
}
