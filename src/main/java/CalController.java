import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class CalController {
	@GetMapping("/")
	public String home(ModelMap model) {
	    return "home";
	}
	
	@GetMapping("/result")
	public String result(ModelMap model, @RequestParam("formula") String formula) {
		double result = Main.calculate(formula);
		model.addAttribute("formula", formula);
		model.addAttribute("result", result);
	    return "result";
	}

}