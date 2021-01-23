package demo.jmegradle;

import iboxdb.localserver.*;
import java.io.File;

public class App {

    public static final AutoBox auto = config();

    public static Box cube() {
        return auto.cube();
    }

    private static AutoBox config() {

        String path = "../jmedemodata3";
        new File(path).mkdirs();
        DB.root(path);
        DB db = new DB();

        db.getConfig().ensureTable(new Ason(Id, 0L), "Table");

        return db.open();

    }

    public static void destroy(){
        auto.getDatabase().close();
    }
    
    public static final String Id = "Id";
    public static final String Count = "Count";

}
