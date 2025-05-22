package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
    attributePaths = {"reservations"})
    @Query("select u from User u ")
    List<User> loadAll();
}
