package no.bekk;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    private final List<String> nameList = Collections.synchronizedList(new ArrayList<String>());


    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new Label("hello", "Hello World!"));

        add(new CommentForm("commentForm"));

        add(new Label("numberOfNames", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return String.valueOf(nameList.size());
            }
        }));
    }

    private class Commenter implements Serializable {

        private String navn;

        private String getNavn() {
            return navn;
        }
    }

    private class CommentForm extends Form<Commenter> {
        public CommentForm(String id) {
            super(id, new CompoundPropertyModel<Commenter>(new Commenter()));
            add(new TextField<String>("navn"));
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            Commenter commenter = getModelObject();
            nameList.add(commenter.getNavn());

        }
    }
}
