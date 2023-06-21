package hello.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item{
    //엔터티가 엔터티를 상속받는다..

    /**
     * Item 테이블을 조인 전략을 통해 상속받을 때,
     *
     * */

    private String artist;


}
