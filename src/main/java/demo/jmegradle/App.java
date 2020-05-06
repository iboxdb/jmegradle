package demo.jmegradle;

import iBoxDB.LocalServer.*;
import java.io.File;

public class App {

    public static final AutoBox auto = config();

    private static AutoBox config() {

        String path = System.getProperty("user.home");
        path += "/jmedemodata";
        new File(path).mkdirs(); 
        DB.root(path);
        DB db = new DB();

        db.getConfig().ensureTable(new Ason("Id", 0L), "Table");

        return db.open();

    }

    public static Ason set(Ason ason, String name, Object value) {
        Object old = ason.get(name);
        if (old != null && value != null) {
            value = new Variant(value).as(old);
        }
        ason.put(name, value);
        return ason;
    }

    public static <T> T get(Ason ason, String name) {
        return (T) ason.get(name);
    }

    public static final String Count = "Count";

}
