package cn.edu.scujcc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.Channel;
import cn.edu.scujcc.ChannelService;

@RestController
@RequestMapping("/channel")
public class ChannelController {
	public static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService service;
	/*
	 * 获得全部频道
	 * 
	 * */
	@GetMapping
	public List<Channel> getAllChannels(){
		logger.info("正在查找所有频道信息。。。");
		List<Channel> results = service.getAllChannels();
		
		return results;
	}
	
	/*
	 * 获得一个频道
	 * */
	@GetMapping("/{id}")
  public Channel getChannel(@PathVariable String id) {
	  return this.service.getChannel(id);
  }
	/*
	 * 删除一个频道
	 * 
	 * */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChannel(@PathVariable String id) {
		boolean result =this.service.deleteChannel(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
	}	
	/*
	 * 新建一个频道
	 * */
	@PostMapping
	public Channel creatChannel(@RequestBody Channel c) {
		return this.service.creatChannel(c);
	}
	@PutMapping
	public Channel updateChannel(@RequestBody Channel c) {
		return this.service.updateChannel(c);
	}
	@GetMapping("/t/{title}")
	public List<Channel> chaxun(@PathVariable String title){
		return service.chaxun(title);
	}
	@GetMapping("/q/{quality}")
	public List<Channel> chaxun1(@PathVariable  String quality){
		return service.chaxun1(quality);
	}
	@GetMapping("/s/{title}/{quality}")
	public List<Channel> search(@PathVariable String title,
			@PathVariable  String quality){
		return service.search(title, quality);
	}
	@GetMapping("/hot")
	public List<Channel> getHotChannels(){
		return service.getLatestCommentsChannel();
	}
}

