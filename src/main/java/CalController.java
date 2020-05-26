import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class CalController {
	
	@GetMapping("/home")
	public String home(ModelMap model) {
		double result = Main.calculate("3+4*7");
		model.addAttribute("result", result);
	    return "home";
	}

}