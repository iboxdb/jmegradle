package demo.jmegradle;

import iBoxDB.LocalServer.*;
import java.io.File;

public class App {

    public static final AutoBox auto = config();

    public static Box cube() {
        return auto.cube();
    }

    private static AutoBox config() {

        String path = "../jmedemodata";
        new File(path).mkdirs();
        DB.root(path);
        DB db = new DB();

        db.getConfig().ensureTable(new Ason(Id, 0L), "Table");

        return db.open();

    }

    public static void set(Ason ason, String name, Object value) {
        Object old = ason.get(name);
        if (old != null && value != null) {
            value = new Variant(value).as(old);
        }
        ason.put(name, value);
    }

    public static <T> T get(Ason ason, String name) {
        return get(ason, name, null);
    }

    public static <T> T get(Ason ason, String name, T defaultValue) {
        T re = (T) ason.get(name);
        return re != null ? re : defaultValue;
    }

    public static final String Id = "Id";
    public static final String Count = "Count";

}
