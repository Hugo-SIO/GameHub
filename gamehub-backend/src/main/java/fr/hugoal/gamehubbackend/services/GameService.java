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

    public Game getGameById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Game updateGame(Long id, Game game){
        Game existing = repository.findById(id).orElseThrow();

        existing.setTitle(game.getTitle());
        existing.setPlatform(game.getPlatform());
        existing.setPlayTime(game.getPlayTime());
        existing.setRating(game.getRating());

        return repository.save(existing);
    }

    public void deleteGame(Long id){
        repository.deleteById(id);
    }
}
