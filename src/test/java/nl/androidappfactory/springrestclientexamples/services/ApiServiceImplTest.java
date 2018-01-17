package nl.androidappfactory.springrestclientexamples.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.domain.User;
import reactor.core.publisher.Mono;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

	@Autowired
	ApiService apiService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetUsers() {

		List<User> users = apiService.getUsers(3);

		// NOTE, for some reason the api will alway return one more user than the given limit....
		log.debug("after getUsers: " + users);
		assertEquals(4, users.size());
	}

	@Test
	public void testGetUsersReactive() {

		Mono<Integer> limit = Mono.just(new Integer(3));
		List<User> users = apiService.getUsers(limit).collectList().block();

		// NOTE, for some reason the api will alway return one more user than the given limit....
		log.debug("after getUsers #: " + users.size());
		assertEquals(4, users.size());
	}

}
