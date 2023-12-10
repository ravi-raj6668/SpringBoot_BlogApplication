package io.innodev.blogapp;

import io.innodev.blogapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBlogApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
	}

	@Test
	public void TestRepo(){
		String className = userRepository.getClass().getName();
		String packageName = userRepository.getClass().getPackageName();
		System.out.println(className + " : " + packageName);
	}

}
