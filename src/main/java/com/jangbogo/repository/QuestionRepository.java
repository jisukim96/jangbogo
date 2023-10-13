package com.jangbogo.repository;

import java.util.List;
import java.util.Optional;

import com.jangbogo.domain.board.Question;
import com.jangbogo.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	Optional<Question> findById(Long id);
	
	Question findBySubject(String subject);    

	Question findByContent(String content);

	List<Question> findByName(Member member);

	List<Question> findByContentLike(String content); 
	
	List<Question> findBySubjectLikeOrContentLike (String subject, String content); 
	
	List<Question> findBySubjectLikeOrderByCreatedAtAsc(String subject);
	List<Question> findBySubjectLikeOrderByCreatedAtDesc(String subject);

	List <Question> findAllByOrderByCreatedAtAsc(); 
	List <Question> findAllByOrderByCreatedAtDesc();

}
