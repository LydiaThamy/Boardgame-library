package sg.edu.nus.iss.day27_lecture;

import org.bson.Document;

import sg.edu.nus.iss.day27_lecture.model.Comment;
import sg.edu.nus.iss.day27_lecture.model.Game;

public class Utility {

    public static Game toGame(Document doc) {
        return new Game(
                doc.getInteger("gid"),
                doc.getString("name"),
                doc.getInteger("year"),
                doc.getInteger("ranking"),
                doc.getInteger("users_rated"),
                doc.getString("url"),
                doc.getString("image"));
    }

    public static Comment toComment(Document doc) {
        return new Comment(
            doc.getString("user"),
            doc.getInteger("rating"),
            doc.getString("c_text"),
            doc.getInteger("gid")
        );
    }

}
