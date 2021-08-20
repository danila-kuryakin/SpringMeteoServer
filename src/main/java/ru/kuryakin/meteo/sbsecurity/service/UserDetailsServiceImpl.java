package ru.kuryakin.meteo.sbsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kuryakin.meteo.dao.user.AppRoleDAO;
import ru.kuryakin.meteo.dao.user.AppUserDAO;
import ru.kuryakin.meteo.models.user.AppUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Загрузка пользовательских данных.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppRoleDAO appRoleDAO;

    /**
     * Загрузка пользовательских данных по имени.
     * @param userName - имя пользователя.
     * @return данные пользователя.
     * @throws UsernameNotFoundException вызывается еси пользователя не существует.
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserDAO.findUserAccount(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {

                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(appUser.getUserName(), appUser.getPassword(), grantList);

        return userDetails;
    }
}
