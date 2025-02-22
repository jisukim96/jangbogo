package com.jangbogo.domain;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.jangbogo.domain.common.BaseTimeEntity;
import com.jangbogo.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DirectMessage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String title; // 제목

	@Column(nullable = false)
	private String content; // 내용

	@Column(nullable = false)
	private boolean deletedBySender; // 보낸 쪽지 삭제

	@Column(nullable = false)
	private boolean deletedByReceiver; // 받은 쪽지 삭제

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Member sender; // 보낸 사람

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Member receiver; // 받는 사람

    public DirectMessage(String title, String content, Member sender, Member receiver, LocalDateTime createAt) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.deletedBySender = false;
        this.deletedByReceiver = false;
        this.setCreatedAt(createAt);
    }

	// 편지 삭제와 관련된 메소드 
	// 발신자가 메세지를 삭제하면 필드의 값을 true로 변경 
	public void deleteBySender() {
		this.deletedBySender = true;
	}

	// 수신자가 메세지를 삭제하면 필드의 값을 true로 변경 
	public void deleteByReceiver() {
		this.deletedByReceiver = true;
	}

	// 수신자, 발신자 둘다 메세지를 삭제하면 DB에서 메세지 삭제 
	public boolean isDeletedMessage() {
        return isDeletedByReceiver() && isDeletedBySender();
    }
}