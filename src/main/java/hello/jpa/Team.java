package hello.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{

    @Id@GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") //mapped by는 Member 엔터티의 필드 네임 team임을 알려주는 것이다.
//    @JoinColumn(name = "TEAM_ID") /*여기 왜 OneToMany로 하고 있는거지*/
    private List<Member> memberList = new ArrayList<>(); //필드 생성할 때 초기화 시켜주는 것이 관례.

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
