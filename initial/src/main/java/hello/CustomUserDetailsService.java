package hello;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository = new UserRepository();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    class UserRepository {
        Optional<CustomUser> findUserByName(String name) {
            switch (name) {
                case "user":
                    return Optional.of(new CustomUser("user", "password"));
                case "admin":
                    return Optional.of(new CustomUser("admin", "admin"));
                default:
                    return Optional.empty();
            }
        }
    }

    public class CustomUser implements UserDetails {
        private String name;
        private String password;

        CustomUser(String name, String password) {
            this.name = name;
            this.password = password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            switch (name) {
                case "user":
                    return AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
                case "admin":
                    return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
                default:
                    return AuthorityUtils.NO_AUTHORITIES;
            }
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return name;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
