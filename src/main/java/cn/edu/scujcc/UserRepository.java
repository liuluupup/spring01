package cn.edu.scujcc;

import org.springframework.data.mongodb.repository.MongoRepository;
import cn.edu.scujcc.User;
public interface UserRepository extends MongoRepository<User,String> {
        public User findFirstByUsername(String username);
        
        public User findOneByUsernameAndPassword(String u,String p);
}
