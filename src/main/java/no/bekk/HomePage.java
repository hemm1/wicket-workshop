package no.bekk;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    private final FeedbackPanel feedback;

    private DBService dbService;
    private final CommentPanel commentPanel;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        dbService = DBService.getInstance();

        feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        add(feedback);

        add(new TimePanel("timePanel"));

        add(new Label("hello", "Hello World!"));

        add(new CommentForm("commentForm"));

        commentPanel = new CommentPanel("commentPanel");
        commentPanel.setOutputMarkupId(true);
        add(commentPanel);

        add(new AjaxLink("toggleComments") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                dbService.toggleVisibility();
//                commentPanel.setVisibilityAllowed(!commentPanel.isVisibilityAllowed());
                target.add(commentPanel);
            }
        });

    }

    private class CommentForm extends Form<Comment> {
        public CommentForm(String id) {
            super(id, new CompoundPropertyModel<Comment>(new Comment()));
            add(
                    new TextField<String>("name").setRequired(true),
                    new TextField<String>("email").add(EmailAddressValidator.getInstance()).setRequired(true),
                    new TextArea<String>("commentText").setRequired(true),
                    new AjaxSubmitLink("submit") {
                        @Override
                        protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                            super.onSubmit(target, form);
                            Comment comment = getModelObject();
                            dbService.addComment(comment);
                            setModelObject(new Comment());
                            info("comment saved successfully!");
                            target.add(commentPanel);
                            target.add(feedback);
                        }

                        @Override
                        protected void onError(AjaxRequestTarget target, Form<?> form) {
                            super.onError(target, form);
                            target.add(feedback);
                        }
                    }
            );
        }
    }
}
