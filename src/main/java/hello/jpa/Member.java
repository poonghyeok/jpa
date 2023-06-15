package hello.jpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity /*실습으로 일시주석*/
/*@TableGenerator(
        name = "member_seq_generator",
        table = "my_sequences",
        pkColumnName = "MEMBER_SEQ", allocationSize = 1
)*/
/*
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 50
)
*/
public class Member {

    @Id
    @GeneratedValue()
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String username;

    /**
     * 일대일 연관관계 매핑 학습용
     *  - 멤버 <-> 라커 1:1 관계 가정
     * */
    @OneToOne
    @JoinColumn(name ="LOCKER_ID")
    private Locker locker;



    /**
     * 다대다 매핑 학습용 Product
     * 멤버 <-> 프로덕트
     */
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    /**
     * 다대다를 다대일로 낮추고 매핑테이블은 엔터티로 승격시켜서 사용하기
     * */
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();


//    @Column(name = "TEAM_ID")
//    private Long teamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }*/

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
