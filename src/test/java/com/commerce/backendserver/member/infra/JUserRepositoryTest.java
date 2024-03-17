package com.commerce.backendserver.member.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.commerce.backendserver.member.domain.Users;

@SpringBootTest
class JUserRepositoryTest {

	@Autowired
	private JUserRepository userRepository;

	@Test
	void test() {
		Users users = userRepository.find();

		System.out.println(users.toString());
	}
}