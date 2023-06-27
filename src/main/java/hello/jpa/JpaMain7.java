package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain7 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("seoul", "achasan", "31184");

            Member member = new Member();
            member.setUsername("jpa king poonghyeok");
            member.setAddress(address);
            em.persist(member);

            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            Member member2 = new Member();
            member2.setUsername("spring king poonghyeok");
            member2.setAddress(address2);
            em.persist(member2);

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
