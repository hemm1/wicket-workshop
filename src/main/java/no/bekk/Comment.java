package no.bekk;

import java.io.Serializable;

public class Comment implements Serializable {
    private String name;
    private String email;
    private String commentText;

    public Comment() {
    }

    public Comment(String name, String email, String commentText) {
        this.name = name;
        this.email = email;
        this.commentText = commentText;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCommentText() {
        return commentText;
    }
}
