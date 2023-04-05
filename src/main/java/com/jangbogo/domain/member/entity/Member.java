package com.jangbogo.domain.member.entity;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jangbogo.domain.common.BaseTimeEntity;
import lombok.*;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
>>>>>>> 66ef775d76714e9a80c91047f09a43d4296be784
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jangbogo.domain.Product;
import com.jangbogo.domain.common.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicUpdate //실제 값이 변경된 컬럼으로만 update 쿼리 생성
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    private String password;

    private String age;
    private String region; //지역정보

    private String imageUrl;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private Role role;

<<<<<<< HEAD
    
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

   
=======
    @Builder
>>>>>>> 66ef775d76714e9a80c91047f09a43d4296be784
    public Member(String email, String password, String name, Role role, Provider provider, String region, String age, String providerId, String imageUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.provider = provider;
        this.region = region;
        this.age = age;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    //닉네임 변경
    public void updateName(String name){
        this.name = name;
    }

    //프로필 이미지
    public void updateImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    //프로필 이미지 삭제
    public void deleteImageUrl(){
        this.imageUrl = null;
    }

    //회원정보 수정
    public void updateMember(String name, String password, String region, String age) {
        this.name = name;
        this.password = password;
        this.region = region;
        this.age = age;
    }

<<<<<<< HEAD


}
=======
    //임시 비밀번호 발급
    public void updatePassWord(String password) {
        this.password = password;
    }

}
>>>>>>> 66ef775d76714e9a80c91047f09a43d4296be784
