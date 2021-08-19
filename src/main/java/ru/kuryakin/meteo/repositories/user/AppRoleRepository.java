package ru.kuryakin.meteo.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kuryakin.meteo.models.user.AppRole;

import java.util.List;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    @Query(value = "select app_role.name " +
            "from app_role " +
            "join user_role ur on app_role.id = ur.role_id " +
            "where ur.user_id = ?1", nativeQuery = true)
    List<String> findByRoleName(Long userId);
}
