package info.sireikan.sample.controller

import info.sireikan.sample.controller.form.SignupForm
import info.sireikan.sample.module.domain.common.User
import info.sireikan.sample.module.domain.repository.UserRepositoryInterface
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    private var userRepository: UserRepositoryInterface

    constructor(userRepository: UserRepositoryInterface) {
        this.userRepository = userRepository
    }

    @GetMapping("/")
    fun index(@ModelAttribute("signup") signupForm: SignupForm, model: Model) : String {
        var userList: List<User> = this.userRepository.findAll()
        model.addAttribute("users", userList)
        return "index"
    }
}
