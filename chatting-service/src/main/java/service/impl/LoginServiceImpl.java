//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package service.impl;

import entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import repository.LoginRepository;
import service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public LoginServiceImpl() {
    }

    public Login getLoginByLogName(String logName) {
        return StringUtils.isEmpty(logName) ? null : this.loginRepository.getLoginByLogName(logName);
    }
}
