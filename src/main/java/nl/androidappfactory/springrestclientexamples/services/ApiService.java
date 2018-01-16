package nl.androidappfactory.springrestclientexamples.services;

import java.util.List;

import nl.androidappfactory.api.domain.User;

public interface ApiService {

	List<User> getUsers(Integer limit);
}
