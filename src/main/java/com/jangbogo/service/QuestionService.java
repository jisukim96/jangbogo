package com.jangbogo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jangbogo.config.security.token.UserPrincipal;
import com.jangbogo.repository.BoardRepository;
import com.jangbogo.repository.MemberRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.jangbogo.exeption.DataNotFoundException;
import com.jangbogo.domain.board.Answer;
import com.jangbogo.domain.board.Board;
import com.jangbogo.domain.board.Question;
import com.jangbogo.domain.member.entity.Member;
import com.jangbogo.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    
    
    //내가 쓴 글 조회
    public ResponseEntity<List<Question>> getMyBoard(UserPrincipal userPrincipal){
        Member member = memberRepository.findByEmail(userPrincipal.getEmail()).orElse(null);
        List<Question> myboard = questionRepository.findByName(member);
        return ResponseEntity.ok(myboard);
    }
	
	// 한개 조회
	public Question getQuestion(Long id) {
		
		Optional<Question> op = this.questionRepository.findById(id) ; 
		if ( op.isPresent()) {
			return op.get();
		}else {
			throw new DataNotFoundException("요청한 파일을 찾지 못했습니다. "); 
		}		 
	}

	
    public Board saveBoardType(Long board_id) {

        Board ob = boardRepository.findById(board_id).orElse(null);
        if (ob == null) {
            ob = new Board("community", "region");
        }

        boardRepository.save(ob);

        return ob;
    }

	public void create(Long board_id, String region, String subject, String content, Member name) {

            Board ob = saveBoardType(board_id);

            Question q = new Question();
            q.setBoard(ob);
            q.setSubject(subject);
            q.setContent(content);
            q.setRegion(region);
            q.setName(name);

            this.questionRepository.save(q);
	}
	
	// 수정
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifiedAt(LocalDateTime.now());

        this.questionRepository.save(question);
    }
    
    // 삭제
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }
    
    // 추천
    public void vote(Question question, Member name) {
    	if(question.getVoter().contains(name)) {
    		question.getVoter().remove(name);
    	}else {
    		question.getVoter().add(name);
    	}
        this.questionRepository.save(question);
    }
    
    public Question save(Question question) {
        return questionRepository.save(question);
    }
	
}
