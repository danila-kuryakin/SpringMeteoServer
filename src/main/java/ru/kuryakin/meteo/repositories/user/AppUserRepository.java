package ru.kuryakin.meteo.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuryakin.meteo.models.user.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
}
