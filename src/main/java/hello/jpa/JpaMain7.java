package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain7 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("jap king poonghyeok");
            member.setHomeAddress(new Address("homecity", "home-street", "04154"));

            member.getFavoriteFoods().add("pork-cutlet");
            member.getFavoriteFoods().add("rice-cake");
            member.getFavoriteFoods().add("water-melon");

            member.getAddressHistory().add(new AddressEntity("cheonan", "handeul", "31185"));
            member.getAddressHistory().add(new AddressEntity("asan", "soonsin-ro", "23254"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=========find member start");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("=========find member start");

            List<AddressEntity> addressHistory = findMember.getAddressHistory();
            for (AddressEntity address : addressHistory) {
                System.out.println("address history : " + address.getAddress().getCity());
            }

            Set<String> favoriteFodds = member.getFavoriteFoods();
            for (String food : favoriteFodds) {
                System.out.println("member1 fovorite food : " + food);
            }

            /*homeAddress 수정하기 그냥 값타입임 -> getter해서 setter 사용하면 side-effect 우려..*/
            Address memberAddr = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("new city", "new street", "new zipcode"));/*값타입 수정의 정석*/

            /*값 타입 컬렉션 수정하는 방법*/
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            favoriteFoods.remove("rice-cake");
            favoriteFoods.add("redbean-bread");

//            findMember.getAddressHistory().remove(new Address("cheonan", "handeul", "31185"));/*대부분의 컬렉션 remove 메서드는 equals()를 사용한다. 그래서 hashcode를 */
//            findMember.getAddressHistory().add(new Address("seoul", "achasan", "01485"));



            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

    }
}
