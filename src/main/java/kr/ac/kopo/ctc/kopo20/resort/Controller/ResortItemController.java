package kr.ac.kopo.ctc.kopo20.resort.Controller;

import javax.management.AttributeValueExp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.engine.AttributeName;

@Controller
public class ResortItemController {
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("var", 1);
		return "index";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("var", 2);
		return "about";
	}

	
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	
	@GetMapping("/room")
	public String room() {
		return "room";
	}
	
//	@GetMapping("/contact")
//	public String top() {
//		return "contact";
//	}
//	
//	@GetMapping("/contact")
//	public String top() {
//		return "contact";
//	}

	
	

}
