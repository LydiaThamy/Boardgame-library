package sg.edu.nus.iss.day27_lecture.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day27_lecture.model.Comment;
import sg.edu.nus.iss.day27_lecture.model.Game;
import sg.edu.nus.iss.day27_lecture.service.BoardgameService;

@Controller
@RequestMapping
public class BoardgameController {

    @Autowired
    private BoardgameService gameService;

    @GetMapping(path = { "/", "/index" })
    public String getIndex(Model m) {
        return "master";
        // return "index";
    }

    @GetMapping("/game")
    public String getGame(@RequestParam(defaultValue = "") String name, Model m) {

        if (name.equals(""))
            return "redirect:/";

        // use service to find game details
        Optional<List<Game>> games = gameService.getGameByName(name);

        if (games.isEmpty()) {
            m.addAttribute("gameNameNotFound", "There is no boardgame with the phrase " + name);
            return "master";
            // return "index";
        }

        if (games.get().size() == 1) {
            // return
        }

        m.addAttribute("searchSuccessful", true);
        m.addAttribute("games", games.get());
        m.addAttribute("name", name);

        return "master";
        // return "result";
    }

    @GetMapping("/game/{gid}")
    public String getGame(@PathVariable Integer gid, Model m) {

        // get game by ID
        Optional<Game> game = gameService.getGameById(gid);

        if (game.isEmpty()) {
            m.addAttribute("gidNotFound", "There is no boardgame with the ID " + gid);
            return "master";
            // return "index";
        }

        // get comments by ID
        Optional<List<Comment>> comments = gameService.getComments(gid);

        if (comments.isPresent())
            m.addAttribute("comments", comments.get());

        m.addAttribute("gameFound", true);
        m.addAttribute("game", game.get());

        m.addAttribute("comment", new Comment(gid));

        return "master";
        // return "game";
    }

    @PostMapping("/game/{gid}")
    public String postComment(
            // @ModelAttribute String user,
            // @ModelAttribute String rating,
            // @ModelAttribute String cText,
            @ModelAttribute Comment comment,
            @PathVariable Integer gid,
            Model m) {

        // service method to insert new comment
        Boolean commentSuccessful = gameService.postComment(comment);
        
        // model
        Optional<Game> game = gameService.getGameById(gid);

        if (game.isEmpty()) {
            m.addAttribute("gidNotFound", "There is no boardgame with the ID " + gid);
            return "master";
            // return "index";
        }

        m.addAttribute("game", game.get());
        m.addAttribute("gameFound", true);

        // get comments by ID
        Optional<List<Comment>> comments = gameService.getComments(gid);

        if (comments.isPresent())
            m.addAttribute("comments", comments.get());
        
        // m.addAttribute("comment", new Comment(gid));

        m.addAttribute("commentSuccessful", commentSuccessful);

        return "master";
        // return "game";
    }

}
