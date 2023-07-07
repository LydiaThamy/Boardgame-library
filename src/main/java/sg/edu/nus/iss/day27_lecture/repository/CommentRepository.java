package sg.edu.nus.iss.day27_lecture.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    @Autowired
    private MongoTemplate template;

        private static final String C_COMMENTS = "comments";
        private static final String F_RATING = "rating";
    
}
