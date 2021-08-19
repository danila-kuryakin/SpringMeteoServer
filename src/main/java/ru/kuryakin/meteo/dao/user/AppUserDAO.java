package ru.kuryakin.meteo.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.models.user.AppUser;
import ru.kuryakin.meteo.repositories.user.AppUserRepository;

@Component
public class AppUserDAO {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser findUserAccount(String userName) {
        return appUserRepository.findByUserName(userName).get();
    }

}
