package root.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import root.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByChatId(String chatId);
    User findByChatId(String chatId);
}
