//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package security;

import entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import service.LoginService;

public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private LoginService loginService;

    public MyUserDetailService() {
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Login login = this.loginService.getLoginByLogName(s);
        if (login == null) {
            throw new UsernameNotFoundException(s);
        } else {
            User user = new User(s, login.getPassword(), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            return user;
        }
    }
}
