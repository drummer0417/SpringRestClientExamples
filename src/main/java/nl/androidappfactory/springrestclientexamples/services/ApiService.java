package nl.androidappfactory.springrestclientexamples.services;

import java.util.List;

import nl.androidappfactory.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiService {

	List<User> getUsers(Integer limit);

	Flux<User> getUsers(Mono<Integer> limit);
}
