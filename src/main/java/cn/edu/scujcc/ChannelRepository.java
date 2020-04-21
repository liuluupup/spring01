package cn.edu.scujcc;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.Channel;
@Repository
public interface ChannelRepository extends MongoRepository<Channel,String> {
   List<Channel> findByTitle(String t);
   List<Channel> findByQuality(String q);
   List<Channel> findByTitleAndQuality(String t,String q);
   public List<Channel> findByCommentsDtAfter(LocalDateTime theDt);
}
