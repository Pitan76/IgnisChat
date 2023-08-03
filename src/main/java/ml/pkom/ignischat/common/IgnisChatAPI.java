package ml.pkom.ignischat.common;

public class IgnisChatAPI {

    public static boolean containsUnicode(String string) {
        for(int i = 0 ; i < string.length() ; i++) {
            char ch = string.charAt(i);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);

            if (Character.UnicodeBlock.HIRAGANA.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.KATAKANA.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION.equals(unicodeBlock))
                return true;
        }
        return false;
    }

    public static String replaceAmpersand(String string) {
        string = string.replace("&a", "§a");
        string = string.replace("&b", "§b");
        string = string.replace("&c", "§c");
        string = string.replace("&d", "§d");
        string = string.replace("&e", "§e");
        string = string.replace("&f", "§f");
        string = string.replace("&0", "§0");
        string = string.replace("&1", "§1");
        string = string.replace("&2", "§2");
        string = string.replace("&3", "§3");
        string = string.replace("&4", "§4");
        string = string.replace("&5", "§5");
        string = string.replace("&6", "§6");
        string = string.replace("&7", "§7");
        string = string.replace("&8", "§8");
        string = string.replace("&9", "§9");
        string = string.replace("&r", "§r");
        string = string.replace("&l", "§l");
        string = string.replace("&m", "§m");
        string = string.replace("&n", "§n");
        return string;
    }

    public static String removeAmpersand(String string) {
        string = string.replace("&a", "");
        string = string.replace("&b", "");
        string = string.replace("&c", "");
        string = string.replace("&d", "");
        string = string.replace("&e", "");
        string = string.replace("&f", "");
        string = string.replace("&0", "");
        string = string.replace("&1", "");
        string = string.replace("&2", "");
        string = string.replace("&3", "");
        string = string.replace("&4", "");
        string = string.replace("&5", "");
        string = string.replace("&6", "");
        string = string.replace("&7", "");
        string = string.replace("&8", "");
        string = string.replace("&9", "");
        string = string.replace("&r", "");
        string = string.replace("&l", "");
        string = string.replace("&m", "");
        string = string.replace("&n", "");
        return string;
    }
}
