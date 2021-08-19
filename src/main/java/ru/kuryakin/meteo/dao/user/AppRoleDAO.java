package ru.kuryakin.meteo.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.models.user.AppRole;
import ru.kuryakin.meteo.repositories.user.AppRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AppRoleDAO {

    @Autowired
    private AppRoleRepository appRoleRepository;

    public List<String> getRoleNames(Long userId) {
        return appRoleRepository.findByRoleName(userId);
    }
}
