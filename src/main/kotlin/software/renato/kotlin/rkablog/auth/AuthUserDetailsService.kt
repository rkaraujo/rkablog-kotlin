package software.renato.kotlin.rkablog.auth

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.nio.file.Files
import java.nio.file.Paths

const val USERS_FILE = "/export/rkablog/users.txt"
const val FIELDS_SEPARATOR = ':'

class AuthUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(userName: String): UserDetails {
        val usersFileLines: List<String> = Files.readAllLines(Paths.get(USERS_FILE))
        for (userLine in usersFileLines) {
            val split = userLine.split(FIELDS_SEPARATOR)
            if (split[0] == userName) {
                val authority = if (userName == "admin") SimpleGrantedAuthority("ROLE_ADMIN") else SimpleGrantedAuthority("ROLE_USER")
                return User(userName, split[1], arrayListOf(authority))
            }
        }
        throw UsernameNotFoundException("Invalid username and password")
    }

}
