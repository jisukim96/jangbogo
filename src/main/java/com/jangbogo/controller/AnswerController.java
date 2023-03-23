package com.jangbogo.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.jangbogo.domain.Answer;
import com.jangbogo.domain.Board;
import com.jangbogo.domain.Member;
import com.jangbogo.domain.Reply;
import com.jangbogo.dto.AnswerDto;
import com.jangbogo.dto.ReplyDto;
import com.jangbogo.service.AnswerService;
import com.jangbogo.service.BoardService;
import com.jangbogo.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final MemberService memberService;
	private final BoardService boardService;
	private final AnswerService answerService;
	
	// 답변 달기
//	@PostMapping("/answer/create/{id}")
// 	public String createReply(Model model, @PathVariable("id") Long id, @Valid
// 			AnswerDto answerDto, BindingResult bindingResult, Principal principal){
// 		
// 		Board board = this.boardService.getBoard(id);
// 		Member member = this.memberService.getMember(principal.getName());
// 		
// 		if(bindingResult.hasErrors()) {
// 			model.addAttribute("board", board);
// 			return "board_detail";
// 		}
//		
//		Answer answer = this.answerService.createAnswer(board, answerDto.getContent(), member);
//		return String.format("redirect:/board/detail/$s#reply_%s", answer.getBoard().getId(), answer.getId());
// 	}
 
	
	//댓글수정 댓글 수정 form 으로 보냄
    @GetMapping("/answer/modify/{id}")
    public String modifyAnswer(AnswerDto answerDto, @PathVariable("id") Long id, Principal principal) {
        
    	Answer answer = this.answerService.getAnswer(id);
    	
        if (!answer.getAuthor().getNickName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerDto.setContent(answer.getContent());
        return "reply_form";
    }
    
    // 댓글수정 처리
//    @PostMapping("/answer/modify/{id}")
//    	public String answerModify(@Valid AnswerDto answerDto, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
//    		if(bindingResult.hasErrors()) {
//    			return "answer_form";
//    		}
//    		Answer answer = this.answerService.getAnswer(id);
//    		if(!answer.getAuthor().getNickName().equals(principal.getName())) {
//    			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//    		}
//    		this.answerService.modifyAnswer(answer, answerDto.getContent());
//    		
//    		return String.format("redirect:/board/detail/%s#answer_%s", answer.getBoard().getId, answer.getId());
//    	}
    
    //댓글 삭제 
//	 @GetMapping("/answer/delete/{id}") 
//	 public String answerDelete(Principal principal, @PathVariable("id") Long id) {
//	 
//	 Answer answer = this.answerService.getAnswer(id);
//	 
//	 if (!answer.getAuthor().getNickName().equals(principal.getName())) { throw
//	 new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다."); }
//	 
//	 this.answerService.deleteAnswer(answer);
//	 
//	 return String.format("redirect:/board/detail/%s", answer.getBoard().getId());
//	 
//	 }
    
}
