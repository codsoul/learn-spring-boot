package com.latteyan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.latteyan.dao.UserMapper;
import com.latteyan.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void add() throws Exception {
		// insert by parameter
		userMapper.insertByParameter("zhangShan", 20);
		
		// insert by object
		User user = new User();
		user.setAge(21);
		user.setName("LiSi");
		userMapper.insertByObject(user);
		
		// insert by map
		Map<String, Object> map = new HashMap<>();
		map.put("name", "huangwu");
		map.put("age", 22);
		userMapper.insertByMap(map);
		
		User zhangShan = userMapper.findByName("zhangShan");
		Assert.assertEquals(20, zhangShan.getAge().intValue());
		User LiSi = userMapper.findByName("LiSi");
		Assert.assertEquals(21, LiSi.getAge().intValue());
		User huangwu = userMapper.findByName("huangwu");
		Assert.assertEquals(22, huangwu.getAge().intValue());
	}
	
	@Test
	public void udpate() throws Exception {
		User user = userMapper.findByName("zhangShan");
		user.setAge(50);
		userMapper.update(user);
		
		user = userMapper.findByName("zhangShan");
		Assert.assertEquals(50, user.getAge().intValue());
		
		userMapper.delete(user.getId());
	}
	
	@Test
	public void findAll() throws Exception {
		List<User> users = userMapper.findAll();
		Assert.assertNotNull(users);
	}

}
