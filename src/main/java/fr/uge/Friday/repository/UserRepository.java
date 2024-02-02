package fr.uge.Friday.repository;

import fr.uge.Friday.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}

