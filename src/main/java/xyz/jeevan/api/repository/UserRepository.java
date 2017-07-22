package xyz.jeevan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
