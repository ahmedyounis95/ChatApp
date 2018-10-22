
package chatapp.ayounis.com.chatapp.util;

public class TextUtils {

    public static boolean isValidString(String str) {
        if (str != null
                && str.length() > 0
                && !str.isEmpty()
                && !str.equalsIgnoreCase("")) {
            return true;
        }

        return false;
    }
}
