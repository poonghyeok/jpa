package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

           /**
           * 일대다 test
            *   - mebmer와 team 중에서 team 쪽에서 FK를 관리하는 실습코드를 작성해본다.
           * */
            Member member = new Member();
            member.setUsername("jpa king poonghyeok");
            em.persist(member);

            /*
            Team team = new Team();
            team.setName("jpa team");
            //아래코드가 team 테이블에 insert되는 것은 아니다 member 테이블의 데이터의 fk가 변경되어야 한다.
            //실행해서 쿼리를 확인해본다면,
            team.getMemberList().add(member);

            em.persist(team);
            */

            /**
             * 일대일 TEST
             *  - 멤버와 라커 1:1관계에서
             * */
            Locker locker = new Locker();
            locker.setName("locker5563");
            em.persist(locker);

            member.setLocker(locker);

            System.out.println(member.getUsername() + " 가 사용하는 라커 이름 : " + member.getLocker().getName());
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
