package hello.jpa;

import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.*;
import java.time.LocalDateTime;

public class JpaMain5 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("running king poonghyeok");

            em.persist(member);

            em.flush();
            em.clear();

            /**
             * 지금까지 사용해왔떤 em.find... flush()와 clear()를 하고 find를 하게 되면 영속성 컨텍스트가 초기화 된 상태기 때문에 쿼리를 날리는 걸 볼 수 있다.
             * */

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember id : " + findMember.getId());
//            System.out.println("findMember name : " + findMember.getUsername());

            Member refMember = em.getReference(Member.class, member.getId()); //프록시 멤버다
            System.out.println("ref Member : " + refMember.getClass());
            refMember.getUsername();
            System.out.println("is Loaded ? " + emf.getPersistenceUnitUtil().isLoaded(refMember));

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
