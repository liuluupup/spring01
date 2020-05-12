package cn.edu.scujcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	/*
	 * 自动注入autowired
	 * */
	@Autowired
     private UserRepository repo;
	 private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	 /*
	  * 用户注册，把用户信息保存
	  * user 是用户信息
	  * 
	  * */
	 public User register(User user) throws UserExistException {
		 User result =null;
		 User saved = repo.findFirstByUsername(user.getUsername());
		 if(saved ==null) {
			 result =repo.save(user);
		 }else {
			 logger.error("用户"+user.getUsername()+"已存在");
			 throw new UserExistException();
		 }
		 return result;
	 }
	 /*
	  * 用户登陆
	  * */
	 public User login(String u,String p) {
		 User result =null;
		 result = repo.findOneByUsernameAndPassword(u, p);
		 return result;
		 
	 }
}
