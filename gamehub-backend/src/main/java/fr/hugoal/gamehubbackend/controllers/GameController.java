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

    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id){
        return this.service.getGameById(id);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Long id,
                           @RequestBody Game game) {
        return service.updateGame(id, game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        service.deleteGame(id);
    }
}
