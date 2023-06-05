package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /*
            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("spring king ph");

            System.out.println("====BEFORE======");
            em.persist(member); //여기서 부터 영속상태가 되는거임
            em.find(Member.class, 100L);
            System.out.println("is member whose pk is 100l in first cache ? check logged query ....member name " + member.getName()); //커밋이 날라가지도 않았는데 조회가 된다..!! 이게 영속성 컨텍스트의 1차캐시다..!!
            //근데 영속상태가 되었다고 해서 바로 Insert 쿼리가 날라가지 않는다.

            System.out.println("====AFTER======");

            ;//쿼리는 여기서 날라간다 => commit 전까지 쿼리들 쭉쭉 쌓고 있다가 커밋 날릴 때 한번에 커밋시키면서 하나의 트랜잭션을 보장(?) 해준다
            */

            /*
            Member findMember1 = em.find(Member.class, 100L); //캐시가 없기 때문에, 쿼리가 나가서 db에서 가져와야하고 가져온 뒤에 1차  캐시에 적재시키므로
            Member findMember2 = em.find(Member.class, 100L); //여기서는 쿼리가 나가면 안된다

            System.out.println("영속성 컨텍스트의 엔턴티 동일성 보장 : " + (findMember1 == findMember2)); //각각의 객체생성이라서 false가 나타날 수도 있는데, true가 나타나는 동일성 보장 기능
            */

            Member memberA = new Member(150L, "A");
            Member memberB = new Member(160L, "B");

            em.persist(memberA);
            em.persist(memberB);

            System.out.println("==================");

            tx.commit(); //여기까지 쓰기지연
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }



    }
}
