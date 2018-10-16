
package chatapp.ayounis.com.chatapp.util;

import java.util.UUID;

public class User {

    public static final String USER_SUFFIX = "_User";
    private static boolean isUpdated = false;

    private static String USER_NAME
            = UUID.randomUUID().toString().substring(0,5) + USER_SUFFIX;

    public static String getUsername() {
        return USER_NAME;
    }

    public static void setUsername(String username) {
        USER_NAME = username;
        isUpdated = true;
    }

    public static boolean isUsernameUpdated() {
        return isUpdated;
    }
}
