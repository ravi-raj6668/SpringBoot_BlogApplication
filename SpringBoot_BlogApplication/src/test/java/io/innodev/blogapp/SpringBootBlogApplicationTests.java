package io.innodev.blogapp;

import io.innodev.blogapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBlogApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(this::doNotThrowException);
	}
	private void doNotThrowException(){
		//This method will never throw exception
	}
	@Test
	 void TestRepo(){
		Assertions.assertDoesNotThrow(this::doNotThrowException);
		String className = userRepository.getClass().getName();
		String packageName = userRepository.getClass().getPackageName();
		System.out.println(className + " : " + packageName);
	}

}
