package jwt.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {

	@GetMapping("/")
	public int printHello() {
		System.out.println(" I am in users Controlller printing hi from here " );
		return HttpStatus.OK.value();
	}
	
}
