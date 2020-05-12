package cn.edu.scujcc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public Response register(@RequestBody User u) {
		 Response result = new Response();
		 logger.debug("用户注册"+u);
		try {
			User saved =service.register(u);
			result.setStatus(Response.STATUS_OK);
			result.setData(saved);
		} catch (UserExistException e) {
			logger.error("用户名已经存在，不能注册!");
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("用户已经存在，不能注册！");
		}
		return result;
	}
	@GetMapping("/login/{username}/{password}")
	public Response login(@PathVariable("username") String username,@PathVariable("password")String password) {
		Response result =new Response();
		User saved =service.login(username,password);
		if(saved !=null) {
			result.setStatus(Response.STATUS_OK);
			result.setData(saved);
		}else {
			logger.error("用户已经存在，不能注册");
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("密码错误");
		}
		return result;
	}
}
