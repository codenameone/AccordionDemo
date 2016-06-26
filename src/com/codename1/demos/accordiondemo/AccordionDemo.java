package com.codename1.demos.accordiondemo;

import com.codename1.components.Accordion;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;

/**
 * A quick demo of the Codename One 
 * <a href="https://www.codenameone.com/javadoc/com/codename1/components/Accordion.html">Accordion component</a> 
 * that also demonstrates lead components blockLead capability
 */
public class AccordionDemo {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form f = new Form("Accordion", new BorderLayout());        
        Accordion accr = new Accordion();
        f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> addEntry(accr));
        addEntry(accr);
        f.add(BorderLayout.CENTER, accr);        
        f.show();
    }

    void addEntry(Accordion accr) {
        TextArea t = new TextArea("New Entry");
        Button delete = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        Label title = new Label(t.getText());
        t.addActionListener(ee -> title.setText(t.getText()));
        delete.addActionListener(ee -> {
            accr.removeContent(t);
            accr.animateLayout(200);
        });
        delete.setBlockLead(true);
        delete.setUIID("Label");
        Container header = BorderLayout.center(title).
                add(BorderLayout.EAST, delete);
        accr.addContent(header, t);
        accr.animateLayout(200);        
    }
    
    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
