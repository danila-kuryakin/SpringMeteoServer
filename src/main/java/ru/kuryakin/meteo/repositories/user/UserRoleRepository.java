package ru.kuryakin.meteo.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuryakin.meteo.models.user.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    //    List<Weather> findTop10ByOrderByIdDesc();
//    Weather findTopByLocationOrderByIdDesc(Location location);
//    List<Weather> findTop300ByLocationOrderByIdDesc(Location location);
}
