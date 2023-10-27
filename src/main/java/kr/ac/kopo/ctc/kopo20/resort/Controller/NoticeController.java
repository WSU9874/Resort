package kr.ac.kopo.ctc.kopo20.resort.Controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.kopo20.resort.domain.Member;
import kr.ac.kopo.ctc.kopo20.resort.domain.Notice;
import kr.ac.kopo.ctc.kopo20.resort.dto.NoticeDTO;
import kr.ac.kopo.ctc.kopo20.resort.service.MemberService;
import kr.ac.kopo.ctc.kopo20.resort.service.NoticeCommentServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.NoticeServiceImpl;
import kr.ac.kopo.ctc.kopo20.resort.service.PageService;
import kr.ac.kopo.ctc.kopo20.resort.service.Serv;

@Controller
public class NoticeController {
	@Autowired
	Serv.NoticeService noSer = new NoticeServiceImpl();

	@Autowired
	Serv.NoticeCommentService noCSer = new NoticeCommentServiceImpl();
	
	@Autowired
	MemberService mser = new MemberService();
	
	@Autowired
	PageService pSer = new PageService();
	@GetMapping("/notice")
	public String notice(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		List<Notice> no = noSer.readAllNotice();
		Page<Notice> paging = this.pSer.getList(page);
        model.addAttribute("paging", paging);
		model.addAttribute("notice", no);

		return "notice";
	}

	@GetMapping("/noticeNew")
	public String noticeNew(Model model) {
		return "noticeNew";
	}

	@PostMapping("/noticeNew")
	public String noticeNew(NoticeDTO dto, Model model) {
		LocalDate now = LocalDate.now();

		Notice no = new Notice();
		no.setTitle(dto.getTitle());
		no.setContent(dto.getContent());
		no.setDate(now);
		no.setWriter("admin");
		no.setViewCount((long) 0);

		noSer.create(no);

		return "redirect:notice";
	}

	@GetMapping("/noticeOne")
	public String findById(Model model, @RequestParam("noticeId") Long noticeId, Notice notice, NoticeDTO dto, Principal principal) {
		String userid=null;
		if(principal!=null) {
		userid=principal.getName();
		}
        Member member=mser.findOneUserId(userid).orElse(new Member());
        model.addAttribute("user", member);
		notice = noSer.readOneNotice(noticeId).orElse(new Notice());
		model.addAttribute("notice", notice);
		model.addAttribute("noticeId", noticeId);
		System.out.println(noticeId);
		noSer.viewCount(notice);
		return "noticeOne";
	}
	
	@PostMapping("/deleteDB")
	public String delete(Notice notice, @RequestParam("noticeId") Long noticeId) throws IOException {

		noSer.deleteOneNotice(notice, noticeId);

		return "redirect:notice";
	}

	@PostMapping("/modify/{noticeId}")
	public String boardmodify(@PathVariable("noticeId") long noticeId, Model model) {
		model.addAttribute("notice", noSer.readOneNotice(noticeId).orElse(new Notice()));
		return "modify";
	}

	@PostMapping("/update/{noticeId}")
	public String boardUpdate(@PathVariable("noticeId") long noticeId, Notice notice) {
		Notice noticeTemp = noSer.readOneNotice(noticeId).orElse(new Notice());
		noticeTemp.setTitle(notice.getTitle());
		noticeTemp.setContent(notice.getContent());

		noSer.updateOneNotice(noticeTemp);

		return "redirect:/noticeOne?noticeId={noticeId}";
	}

}
