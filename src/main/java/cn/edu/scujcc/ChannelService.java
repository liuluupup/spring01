package cn.edu.scujcc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
 
 /*
  * 获取所有频道
  * */
 public List<Channel> getAllChannels(){
	 return repo.findAll();
 }
 
 /*
  * 获取一个频道
  * @param id
  * @return
  * */
 public Channel getChannel(String channelId) {
	Optional<Channel> result=repo.findById(channelId);
	if(result.isPresent()) {
		return result.get();
	}else {
		return null;
	}
 }
 /*
  * 
  * 删除一个频道
  * */
 public boolean deleteChannel(String channelId) {
	 boolean result = true;
	 repo.deleteById(channelId);
	 return result;
 }
 /*
  * 
  * 更新一个频道
  * 
  * */
 public Channel updateChannel(cn.edu.scujcc.Channel c) {
	 Channel saved = getChannel(c.getId());
	 if(saved != null) {
		 if(c.getTitle()!=null) {
			 saved.setTitle(c.getTitle());
		 }
		 if(c.getQuality()!=null) {
			 saved.setQuality(c.getQuality());
		 }
		 if(c.getUrl()!=null) {
			 saved.setUrl(c.getUrl());
		 }
         if(c.getComments()!=null) {
        	 saved.getComments().addAll(c.getComments());
         }else {
        	 saved.setComments(c.getComments());
         }
	 }
	 if(c.getCover()!=null) {
		 saved.setCover(c.getCover());
	 }
	 return repo.save(saved);
 }
 /*
  * 新建
  * */
 public Channel creatChannel(Channel c) {
//	 c.setId(this.channels.get(this.channels.size() -1).getId() +1);
//	 this.channels.add(c);
	 return repo.save(c);
 }
 public List<Channel> chaxun(String title){
	 return repo.findByTitle(title);
 }
 public List<Channel> chaxun1(String quality){
	 return repo.findByQuality(quality);
 }
 public List<Channel> search(String title,String quality){
	 return repo.findByTitleAndQuality(title, quality);
 }
public List<Channel> getLatestCommentsChannel(){
	LocalDateTime now =LocalDateTime.now();
	LocalDateTime today =LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0);
	return repo.findByCommentsDtAfter(today);
}
/*
 * 向指定的频道增加一条评论
 * channelId 评论的频道编号
 * Comment  将要更新的评论对象
 * */
public Channel addComment(String channelId,Comment comment) {
	Channel saved = getChannel(channelId);
	if(saved != null) {
		saved.addComment(comment);
		return repo.save(saved);
	}
	return null;
}
public List<Comment> hotComments(String channelId){
	List<Comment> result = new ArrayList<>();
	Channel saved = getChannel(channelId);
	if(saved != null && saved.getComments()!=null) {
		//根据评论的star进行排序
		saved.getComments().sort(new Comparator<Comment>() {
			@Override
			public int compare(Comment o1, Comment o2) {
				if(o1.getStar() == o2.getStar()) {
					return 0;
				}else if(o1.getStar() < o2.getStar()) {
				return 1;
			}else {
				return -1;
			}
			}
		});
		if(saved.getComments().size()>3) {
			result = saved.getComments().subList(0, 3);
		}else {
			result = saved.getComments();
		}
	}
	
	return result;
	
}
}
