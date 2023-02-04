package info.sireikan.sample.controller.form

import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import org.jetbrains.annotations.NotNull

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
class SignupForm {
    @NotNull
    lateinit var email: String
    @NotNull
    lateinit var password: String
    @NotNull
    lateinit var name: String
}
