package hello.jpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;
    @Column(name = "name", nullable = false)
    private String username;
    private int age;
    @Enumerated(EnumType.STRING) //EnumType은 기본값인 Ordinal을 사용하며 안된다, 기본이 Integer라서 Enum을 사용하는 목적에서 벗어남 1, 2 이런식으로 저장되면 무슨의미임, 직관성 떨어지고 추가될 떄 노답임
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP) //DB는 날짜 , 시간, 날짜시간 이렇게 구분해서 사용하기 때문에 지정을 해줘야한다. => java8부터 localdate가 등장했고 최신하이버네이트는 이에 따른 구분 매핑이 가능하기 때문에 밑에 코드를 사용하면된다.
    private Date lastModifiedDate;

    //최신하이버네이트를 지원할 때에는 아래와 같다
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
    @Lob //VARCHAR를 넘어서는 큰 컨텐츠를 넣고 싶을 떄,
    private String description;
    @Transient //DB와 매핑하지 않는 필드
    private int temp;
    public Member() {
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
