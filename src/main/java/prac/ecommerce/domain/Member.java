package prac.ecommerce.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Member extends BaseEntity {

    @Id@GeneratedValue//일단 (default) AUTO로 가봅시다
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    /**
     * 단방향 매핑으로 끝내야하지만, 예제이고 양방향 매핑을 배웠기에 써먹어 보기 위해서 orders를 만들어봄
     * new ArrayList()로 초기화하는 것이 관례
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    /**
     * 편의 메서드를 사용한느 것이 이득. 단, 양방향 매핑의 편의 메서드는 한쪽에서만 만들어야한다.
     * */
    public void setOrders(List<Order> orders) {

        this.orders = orders;
    }
}
