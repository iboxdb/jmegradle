package demo.jmegradle;

import java.io.*;
import com.jme3.system.*;
import iboxdb.localserver.*;

public class App {

    public static final AutoBox auto = config();

    public static IBox cube() {
        return auto.cube();
    }

    private static AutoBox config() {
        
        ///mnt/sdcard/Android/data/<packagename>/files
        //System.out.println(JmeSystem.getStorageFolder(JmeSystem.StorageFolderType.External).getAbsolutePath());
        
        ///data/data/<packagename>/app_
        //System.out.println(JmeSystem.getStorageFolder(JmeSystem.StorageFolderType.Internal).getAbsolutePath());

        File dir = JmeSystem.getStorageFolder(JmeSystem.StorageFolderType.Internal);
        dir = new File(dir, "ProjectName");
        dir = new File(dir, "DatabaseName");
        dir.mkdir();
        System.out.println(dir.getAbsolutePath());        
        DB.root(dir.getAbsolutePath());
        DB db = new DB();

        db.getConfig().ensureTable(new Ason(Id, 0L), "Table");

        return db.open();

    }

    public static void destroy() {
        auto.getDatabase().close();
    }

    public static final String Id = "Id";
    public static final String Count = "Count";

}
