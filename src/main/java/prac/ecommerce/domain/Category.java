package prac.ecommerce.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private Category parent; //상위 카테고리.. jpa는 셀프 매핑도 지원한다.

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>(); //셀프 양방향 매핑..! 부모 카테고리에서 자식 카테고리를 볼 수 있고 자식 카테고리에서 부모 카테고리 볼 수 있음

    /**
     * 실전 예제3에서 Category와 Item은 다대다 관계
     */
    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
                joinColumns = @JoinColumn(name = "CATEGORY_ID"),
                inverseJoinColumns = @JoinColumn(name="ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();
}
