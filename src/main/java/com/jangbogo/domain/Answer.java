package com.jangbogo.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.jangbogo.domain.common.BaseTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Answer extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "내용을 입력해주세요.")
	private String content;

	@ManyToOne		
	private Board board;
	
	@ManyToOne 
    private Member author;


}
