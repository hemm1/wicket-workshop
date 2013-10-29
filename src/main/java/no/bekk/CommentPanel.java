package no.bekk;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

public class CommentPanel extends Panel {


    private DBService dbService;

    public CommentPanel(String id) {
        super(id);

        setOutputMarkupPlaceholderTag(true);

        dbService = DBService.getInstance();

        add(new PropertyListView<Comment>("comments", dbService.getComments()) {

            @Override
            protected void populateItem(ListItem<Comment> item) {
                item.add(
                        new Label("name"),
                        new Label("email"),
                        new MultiLineLabel("commentText")
                );
            }
        });

        add(new TimePanel("anotherTimePanel"));

    }

    @Override
    public boolean isVisible() {
        return dbService.isVisible();
    }
}
