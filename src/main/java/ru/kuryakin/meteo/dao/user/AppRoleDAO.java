package ru.kuryakin.meteo.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.models.user.AppRole;
import ru.kuryakin.meteo.repositories.user.AppRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация взаимодействия с таблицей app_role.
 */
@Component
public class AppRoleDAO {

    /**
     * Репозиторий.
     */
    @Autowired
    private AppRoleRepository appRoleRepository;

    /**
     * Возвращает список ролей по id пользователя.
     * @param userId - id пользователя.
     * @return список ролей.
     */
    public List<String> getRoleNames(Long userId) {
        return appRoleRepository.findByRoleName(userId);
    }
}
