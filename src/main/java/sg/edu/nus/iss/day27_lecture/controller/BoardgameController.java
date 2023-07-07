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

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.day27_lecture.model.Game;
import sg.edu.nus.iss.day27_lecture.service.BoardgameService;

@Controller
@RequestMapping
public class BoardgameController {
    
    @Autowired
    private BoardgameService gameService;

    @GetMapping(path = {"/", "/index"})
    public String getIndex() {
        return "index";
    }

    @GetMapping("/game")
    public String getGame(@RequestParam String name, Model m, HttpSession session) {

        // use service to find game details
        Optional<List<Game>> games = gameService.getGameByName(name);

        if (games.isEmpty()) {
            m.addAttribute("searchUnsuccessful", name + " does not exist!");
            return "index";
        }

        m.addAttribute("games", games.get());
        session.setAttribute("games", games);

        return "result";
    }

    @GetMapping("/game/{gameId}")
    public String getGame(@PathVariable Integer gameId, Model m, HttpSession session) {

        Game game;

        // check if a comment has been posted
        // String commented = (String) session.getAttribute("commentSuccessful");
        
        // use service to find game details
        // if (commented == null) {
            game = gameService.getGameById(gameId).get();
            session.setAttribute("game", game);
            
        // } else {
        //     m.addAttribute("commentSuccessful", commented);
        //     game = (Game) session.getAttribute("game");
        // }
        
        m.addAttribute("game", game);
        
        return "game";
    }

    // @GetMapping("/game/{gameId}")
    // public String getGame(@PathVariable Integer gameId, Model m, HttpSession session) {

    //     Game game;

    //     // check if a comment has been posted
    //     String commented = (String) session.getAttribute("commentSuccessful");
        
    //     // use service to find game details
    //     if (commented == null) {
    //         game = gameService.getGameById(gameId).get();
    //         session.setAttribute("game", game);
            
    //     } else {
    //         m.addAttribute("commentSuccessful", commented);
    //         game = (Game) session.getAttribute("game");
    //     }
        
    //     m.addAttribute("game", game);
        
    //     return "game";
    // }

    @PostMapping("/game/{gameId}")
    public String postComment(@ModelAttribute String comment, @PathVariable Integer gameId, Model m, HttpSession session) {
        
        // service method to insert new comment
        m.addAttribute("commentSuccessful", "Your comment has been posted!");

        Optional<Game> game = gameService.getGameById(gameId);
        if (game.isEmpty()) {
            session.setAttribute("invalidSession", "Your session has expired");
            return "index";
        }
            
        m.addAttribute("game", game.get());
        return "game";
    }

}
