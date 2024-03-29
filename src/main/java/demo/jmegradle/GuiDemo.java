package demo.jmegradle;

import iboxdb.localserver.*;
import static demo.jmegradle.App.*;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.*;
import com.jme3.system.AppSettings;
import com.simsilica.lemur.*;

public class GuiDemo extends SimpleApplication {

    public static void main(String... args) {

        GuiDemo main = new GuiDemo();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");
        main.setSettings(settings);

        main.start();

    }

    @Override
    public void simpleInitApp() {

        Mesh b = createBox(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Orange);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);

        GuiGlobals.initialize(this);

        Container myWindow = new Container();
        guiNode.attachChild(myWindow);

        myWindow.setLocalTranslation(30, 450, 0);

        // Add some elements
        Label la = new Label("Move the Camera");
        myWindow.addChild(la);
        Button clickMe = myWindow.addChild(new Button("Click Me"));

        clickMe.addClickCommands((Command<Button>) (Button source) -> {
            try (IBox box = cube()) {
                Ason ason = box.d("Table", 1L).replace(Ason.class);
                Long l = ason.set(Count);
                if (l == null) {
                    l = 0L;
                }
                ason.set(Count, l + 1L);
                CommitResult cr = box.commit();
            }
            String text = auto.count("from Table") + " , ";
            text += auto.select("from Table").toString();
            la.setText(text);
        });

    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
    }

    @Override
    public void destroy() {
        App.destroy();
        super.destroy();

    }

    public static Mesh createBox(float x, float y, float z) {
        return new com.jme3.scene.shape.Box(x, y, z);
    }

}
