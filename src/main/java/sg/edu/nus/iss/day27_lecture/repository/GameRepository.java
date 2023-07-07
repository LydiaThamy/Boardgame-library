package sg.edu.nus.iss.day27_lecture.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day27_lecture.Utility;
import sg.edu.nus.iss.day27_lecture.model.Game;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate template;

    private static final String C_GAMES = "games";

    private static final String F_GAME_ID = "gid";
    private static final String F_NAME = "name";
    private static final String F_YEAR = "year";
    private static final String F_RANKING = "ranking";
    private static final String F_USERS_RATED = "users_rated";
    private static final String F_URL = "urle";
    private static final String F_IMAGE = "image";

    public Optional<List<Game>> getGameByName(String name) {
        
        Criteria criteria = Criteria.where(F_NAME).regex(name, "i");
        Query query = Query.query(criteria);

        // text search
        // TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase(name);
        // TextQuery query = TextQuery.queryText(textCriteria);

        List<Document> result = template.find(query, Document.class, C_GAMES);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        List<Game> games = new ArrayList<>();
        
        for (Document d : result) {
            games.add(Utility.toGame(d));
        }

        return Optional.of(games);
    }

    public Optional<Game> getGameById(Integer gameId) {
        
        Criteria criteria = Criteria.where(F_GAME_ID).is(gameId);
        Query query = Query.query(criteria);

        // text search
        // TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase(name);
        // TextQuery query = TextQuery.queryText(textCriteria);

        List<Document> result = template.find(query, Document.class, C_GAMES);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(Utility.toGame(result.get(0)));
    }
    
}
