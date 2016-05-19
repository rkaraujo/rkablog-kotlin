package software.renato.kotlin.rkablog.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User(val _username: String, val _password: String, val _authorities: List<GrantedAuthority>) : UserDetails {

    override fun getUsername(): String {
        return _username
    }

    override fun getPassword(): String {
        return _password
    }

    override fun getAuthorities(): List<GrantedAuthority> {
        return _authorities
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
