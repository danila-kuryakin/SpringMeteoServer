package ru.kuryakin.meteo.sbsecurity.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/***
 * Шифрование пароля
 */
public class EncrytedPasswordUtils {
    /***
     * Шифрование пароль с помощью BCryptPasswordEncoder.
     * @param password - пароль.
     * @return хеш пароля.
     */
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /***
     * Запуск шифрования.
     */
    public static void main(String[] args) {
        String password = "pass";
        String encrytedPassword = encrytePassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);
    }
}
