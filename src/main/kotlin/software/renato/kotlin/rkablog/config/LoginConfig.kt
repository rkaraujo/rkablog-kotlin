package software.renato.kotlin.rkablog.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
open class LoginConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.
            authorizeRequests()?.
                antMatchers("/admin/**")?.hasRole("ADMIN")?.
            and()?.
            formLogin()?.
                loginPage("/login.html")?.
                permitAll()?.
            and()?.
            logout()?.
                permitAll()
    }

}
