package org.chinocarbon.judgesystem.service;

import org.chinocarbon.judgesystem.enums.AccountStatus;
import org.chinocarbon.judgesystem.pojo.User;

/**
 * @author ChinoCarbon
 * @since 2022/5/12-4:35 PM
 */
public interface AccountService
{
    AccountStatus checkLogIn(User user);
    AccountStatus checkRegister(User user);
}
