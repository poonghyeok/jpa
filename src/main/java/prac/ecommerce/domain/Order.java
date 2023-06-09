package prac.ecommerce.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

//@Entity
//@Table(name = "ORDERS") //ORDER는 DB의 예약어이기 때문에,,,!
public class Order extends BaseEntity{

    @Id@GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID", foreignKey = @ForeignKey(name = "FK_ORDER_MEMBER"))
    private Member member;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name="DELIVERY_ID", foreignKey = @ForeignKey(name = "FK_ORDER_DELIVERY"))
    private Delivery delivery;

    /**
     * orderItems를 만들면서 양방향 매핑으로 가는 것이 꼭 필요한가?
     *  - 비즈니스 적으로 가치가 높다.
     *  - 한 주문에 어떤 아이템들이 주문되었는지 보기 위해서
     * */
    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
