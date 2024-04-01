package com.finathon;

import com.finathon.exceptions.UserAlreadyPresentException;
import com.finathon.model.LoginUser;
import com.finathon.repository.LoginRepository;
import com.finathon.service.LoginService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.when;

@SpringBootTest
class FinathonApplicationTests {
	@Mock
	private LoginRepository loginRepository;

	@InjectMocks
	private LoginService loginService;

	@Test
	void contextLoads() {
	}

	@Test
	public void userAlreadyExistTest(){
		LoginUser user= new LoginUser();
		user.setUserName("userName");

		when(loginRepository.existsByUserName("userName")).thenReturn(true);
		try {
			loginService.saveUser(user);
		}
		catch (UserAlreadyPresentException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
