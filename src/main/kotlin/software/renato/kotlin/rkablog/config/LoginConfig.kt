package software.renato.kotlin.rkablog.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import software.renato.kotlin.rkablog.auth.AuthUserDetailsService

@EnableWebSecurity
open class LoginConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.
            authorizeRequests().
                antMatchers("/admin/**").hasRole("ADMIN").
            and().
            formLogin().
                loginPage("/login.html").
                permitAll().
            and().
            logout().
                permitAll().
            and().
            csrf().disable()
    }

    @Bean
    open fun authUserDetailsService(): AuthUserDetailsService {
        return AuthUserDetailsService()
    }

    @Bean
    open fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}
