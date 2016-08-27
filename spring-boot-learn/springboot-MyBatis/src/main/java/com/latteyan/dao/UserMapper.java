package com.latteyan.dao;

import java.util.List;
import java.util.Map;

import com.latteyan.entity.User;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	// 通过Parameter新增
	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
	int insertByParameter(@Param("name") String name, @Param("age") Integer age);
	
	// 通过Map新增
	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
	int insertByMap(Map<String, Object> map);  
	
	// 通过Object新增
	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
	int insertByObject(User user);
	
	// Delete By Id
	@Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);
	
	// Update
	@Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);
	
	// Find by Parameter
	@Select("SELECT * FROM USER WHERE NAME = #{name}")
	User findByName(@Param("name") String name);
	
	// 通过@Results，绑定返回值
	@Results({
	    @Result(property = "name", column = "name"),
	    @Result(property = "age", column = "age")
	})
	
	@Select("SELECT name, age FROM user")
	List<User> findAll();  
}
