package no.bekk;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
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

        add(new Label("numberOfNames", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return String.valueOf(comments.size());
            }
        }));
    }

    private class Comment implements Serializable {

        private String navn;

        private String getNavn() {
            return navn;
        }
    }

    private class CommentForm extends Form<Comment> {
        public CommentForm(String id) {
            super(id, new CompoundPropertyModel<Comment>(new Comment()));
            add(new TextField<String>("navn"));
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            Comment comment = getModelObject();
            comments.add(comment);
        }
    }
}
