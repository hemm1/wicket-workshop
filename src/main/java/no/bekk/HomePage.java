package no.bekk;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    private final List<Comment> comments = Collections.synchronizedList(new ArrayList<Comment>());


    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new Label("hello", "Hello World!"));

        add(new CommentForm("commentForm"));

        add(new PropertyListView<Comment>("comments", comments) {

            @Override
            protected void populateItem(ListItem<Comment> item) {
                item.add(
                        new Label("name"),
                        new MultiLineLabel("comment")
                );
            }
        });
    }

    private class Comment implements Serializable {
        private String name;

        private String comment;
    }

    private class CommentForm extends Form<Comment> {
        public CommentForm(String id) {
            super(id, new CompoundPropertyModel<Comment>(new Comment()));
            add(new TextField<String>("name"));
            add(new TextArea<String>("comment"));
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            Comment comment = getModelObject();
            comments.add(comment);
            setModelObject(new Comment());
        }
    }
}
