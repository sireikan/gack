package info.sireikan.sample.controller

import info.sireikan.sample.controller.form.SignupForm
import info.sireikan.sample.module.ApplicationService.common.UserApplicationServiceInterface
import info.sireikan.sample.module.domain.common.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class IndexController {
    private var userApplicationService: UserApplicationServiceInterface

    constructor(userApplicationService: UserApplicationServiceInterface) {
        this.userApplicationService = userApplicationService
    }

    @GetMapping("/")
    fun index(@ModelAttribute("signup") signupForm: SignupForm, model: Model) : String {
        var userList: List<User> = this.userApplicationService.findAll()
        model.addAttribute("users", userList)
        return "index"
    }

    @PostMapping("signup")
    fun signup(@ModelAttribute("signup") sighupForm: SignupForm, redirectAttributes: RedirectAttributes) : String {
        var roles: List<String> = listOf("ROLE_USER", "ROLE_ADMIN")
        this.userApplicationService.register(sighupForm.name, sighupForm.email, sighupForm.password, roles)
        redirectAttributes.addFlashAttribute("successMessage", "アカウントの登録が完了しました")
        return "redirect:/"
    }
}
