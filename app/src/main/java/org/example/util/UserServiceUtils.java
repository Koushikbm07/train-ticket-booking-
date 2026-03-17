package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtils {

    public static String getHashedPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashPassword) {
        return BCrypt.checkpw(plainPassword, hashPassword);
    }
}
