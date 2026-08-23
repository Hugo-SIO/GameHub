package fr.hugoal.gamehubbackend.services;

import fr.hugoal.gamehubbackend.models.Game;
import fr.hugoal.gamehubbackend.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository){
        this.repository = repository;
    }

    public List<Game> getAllGames(){
        return repository.findAll();
    }

    public Game createGame(Game game){
        return repository.save(game);
    }
}
