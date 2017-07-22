package xyz.jeevan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
