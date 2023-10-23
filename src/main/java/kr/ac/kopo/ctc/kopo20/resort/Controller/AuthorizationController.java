package kr.ac.kopo.ctc.kopo20.resort.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import kr.ac.kopo.ctc.kopo20.resort.dto.MemberDTO;
import kr.ac.kopo.ctc.kopo20.resort.repository.MemberRepository;
import kr.ac.kopo.ctc.kopo20.resort.service.RegisterMemberService;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
	private Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
    
	private final RegisterMemberService registerMemberService;

	public AuthorizationController(RegisterMemberService registerMemberService) {
		this.registerMemberService = registerMemberService;
	}
	@Autowired
	private MemberRepository repository;

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDTO dto, HttpServletRequest request) {
		String backURL = request.getHeader("referer");
		try {
			registerMemberService.join(dto.getUserid(), dto.getPw(), dto.getEmail(),dto.getPhone(),dto.getAddress(),dto.getNickname());
			return ResponseEntity.ok("join success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/idCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	public boolean overlappedID(@RequestParam("userid") String userid) throws Exception {
		boolean result = repository.existsByUserid(userid); // 중복확인한 값을 int로 받음
		return result;
	}
	
	@ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/nickCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	@Transactional
	public boolean overlappedNick(@RequestParam("nickname") String nickname) throws Exception {
		boolean result = repository.existsByNickname(nickname); // 중복확인한 값을 int로 받음
		System.out.println(result);
		return result;
	}

}
