package hello.jpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {

    //다대다에서 매핑테이블 역할을 할 엔터티를 만든다.
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    /**
     * 위와 같이 멤버와 프로덕트 양쪽으로 매핑을 걸어놓으면 나머지 필드들에는 추가 정보가 될만한 필드들 추가 가능
     */
    private int count;
    private int price;

    private LocalDateTime orderDatetime;

}
