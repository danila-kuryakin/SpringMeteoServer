package ru.kuryakin.meteo.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.models.user.AppUser;
import ru.kuryakin.meteo.repositories.user.AppUserRepository;

/***
 * Реализация взаимодействия с таблицей app_user.
 */
@Component
public class AppUserDAO {

    /**
     * Репозиторий.
     */
    @Autowired
    private AppUserRepository appUserRepository;

    /**
     * Возвращает данные пользователя из таблицы по имени.
     * @param userName - имя пользователя.
     * @return данные пользователя
     */
    public AppUser findUserAccount(String userName) {
        return appUserRepository.findByUserName(userName).get();
    }

}
