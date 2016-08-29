package com.latteyan.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latteyan.dao.UserMapper;
import com.latteyan.entity.User;


@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={"/users"}, method=RequestMethod.GET)
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
    	userMapper.insertByObject(user);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
    	return userMapper.findById(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = userMapper.findById(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        userMapper.update(user);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
    	userMapper.delete(id);
        return "success";
    }

}