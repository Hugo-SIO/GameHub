package fr.hugoal.gamehubbackend.controllers;

import fr.hugoal.gamehubbackend.models.Game;
import fr.hugoal.gamehubbackend.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service){
        this.service = service;
    }

    @GetMapping
    public List<Game> getGames(){
        return service.getAllGames();
    }

    @PostMapping
    public Game createGame(@RequestBody Game game){
        return this.service.createGame(game);
    }
}
