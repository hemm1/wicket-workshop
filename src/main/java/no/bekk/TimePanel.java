package no.bekk;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import java.util.Date;

public class TimePanel extends Panel {


    public TimePanel(String id) {
        super(id);

        add(new Label("dateAndTime", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return new Date().toString();
            }
        }));

    }
}
