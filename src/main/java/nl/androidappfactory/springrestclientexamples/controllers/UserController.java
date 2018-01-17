package nl.androidappfactory.springrestclientexamples.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.domain.User;
import nl.androidappfactory.springrestclientexamples.services.ApiService;
import reactor.core.publisher.Flux;

/**
 * Created by jt on 9/22/17.
 */
@Slf4j
@Controller
public class UserController {

	private ApiService apiService;

	public UserController(ApiService apiService) {
		this.apiService = apiService;
	}

	@GetMapping({ "", "/", "/index" })
	public String index() {
		return "index";
	}

	@PostMapping("/users")
	public String formPost(Model model, ServerWebExchange serverWebExchange) {

		log.debug("in formPost: ");

		// non reactive approach

		// MultiValueMap<String, String> map = serverWebExchange.getFormData().block();
		//
		// Integer limit = new Integer(map.get("limit").get(0));
		//
		// log.debug("Received Limit value: " + limit);
		// // default if null or zero
		// if (limit == null || limit == 0) {
		// log.debug("Setting limit to default of 10");
		// limit = 10;
		// }

		// Reactive approach

		Flux<User> users = apiService.getUsers(serverWebExchange.getFormData()
				.map(data -> {
					Integer limit = 10;
					if (data.getFirst("limit") != null && !data.getFirst("limit").isEmpty()
							&& !data.getFirst("limit").equals("0")) {
						limit = new Integer(data.getFirst("limit"));
					}
					return limit;
				}));

		model.addAttribute("users", users);

		return "userlist";
	}
}