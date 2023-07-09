package sg.edu.nus.iss.day27_lecture.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day27_lecture.Utility;
import sg.edu.nus.iss.day27_lecture.model.Comment;

@Repository
public class CommentRepository {

    @Autowired
    private MongoTemplate template;

    private static final String C_COMMENTS = "comments";

    private static final String F_ID = "_id";
    private static final String F_C_ID = "c_id";
    private static final String F_USER = "user";
    private static final String F_RATING = "rating";
    private static final String F_C_TEXT = "c_text";
    private static final String F_GID = "gid";

    public Optional<List<Comment>> getComments(Integer gid) {

        Criteria criteria = Criteria.where(F_GID).is(gid);

        Query query = Query.query(criteria)
                .with(
                        Sort.by(Sort.Direction.DESC, F_RATING))
                .limit(5);

        List<Document> result = template.find(query, Document.class, C_COMMENTS);

        if (result.isEmpty())
            return Optional.empty();

        List<Comment> comments = new ArrayList<>();

        for (Document comment : result)
            comments.add(Utility.toComment(comment));

        return Optional.of(comments);
    }

    public Boolean postComment(Comment comment) {

        try {
            template.insert(
                    new Document()
                    // new Document(F_ID, new ObjectId())
                            .append(F_C_ID, UUID.randomUUID().toString().substring(0, 7))
                            .append(F_USER, comment.getUser())
                            .append(F_RATING, comment.getRating())
                            .append(F_C_TEXT, comment.getCText())
                            .append(F_GID, comment.getGid()),
                    C_COMMENTS);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
