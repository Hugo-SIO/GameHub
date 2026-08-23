package fr.hugoal.gamehubbackend.repositories;

import fr.hugoal.gamehubbackend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}