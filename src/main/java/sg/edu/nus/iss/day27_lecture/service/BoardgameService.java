package sg.edu.nus.iss.day27_lecture.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day27_lecture.model.Comment;
import sg.edu.nus.iss.day27_lecture.model.Game;
import sg.edu.nus.iss.day27_lecture.repository.CommentRepository;
import sg.edu.nus.iss.day27_lecture.repository.GameRepository;

@Service
public class BoardgameService {

    @Autowired
    private CommentRepository commmentRepo;

    @Autowired
    private GameRepository gameRepo;

    public Optional<List<Game>> getGameByName(String name) {
        return gameRepo.getGameByName(name);
    }

    public Optional<Game> getGameById(Integer gid) {
        return gameRepo.getGameById(gid);
    }

    public Optional<List<Comment>> getComments(Integer gid) {
        return commmentRepo.getComments(gid);
    }

    public Boolean postComment(Comment comment) {
        return commmentRepo.postComment(comment);
    }
    
}
