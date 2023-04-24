package com.travelAlone.s20230404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelAlone.s20230404.model.Board;
import com.travelAlone.s20230404.model.Warning;
import com.travelAlone.s20230404.service.jh.jhService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class jhController {
	
	private final jhService		js;
	
	
	// 추천 버튼
	@RequestMapping(value = "/boardlike")
    public String like(Board board,Model model) {
		
		log.info("jhController like start");
		int updateCount = js.updateCount(board);
        log.info("jhController like result ->" + updateCount);
        log.info("jhController like board_id ->" + board.getBoard_id());
        model.addAttribute("updateCount", updateCount);
        
        return "redirect:/detailBoard?board_id=" + board.getBoard_id() + "&b_common_board=" + board.getB_common_board();
	}
	
	
	// 신고 버튼
	@RequestMapping(value = "reportMember")
	public String reportMember(Warning warning,Board board,Model model) {
		
		log.info("jhController reportMember start");
		int reportMemberCnt = js.reportMember(warning);
		log.info("jhController reportMember reportMemberCnt -> " + reportMemberCnt);
		log.info("jhController reportMember member_id -> " + warning.getMember_id());	// 회원 ID
		log.info("jhController reportMember u_nickname -> " + warning.getU_nickname());	// 신고자 ID
		model.addAttribute("reportMemberCnt", reportMemberCnt);
		return "redirect:/detailBoard?board_id=" + board.getBoard_id() + "&b_common_board=" + board.getB_common_board();
	}
	
	@RequestMapping(value = "/warning")
	public String warning () {
		return "warning";
	}
	
	
}