package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /*Member member = new Member();
            Member member2 = new Member();
            Member member3 = new Member();

            member.setUsername("JPA KNIG POONGHYEOK");
            member2.setUsername("SPRING KING POONGHYEOK");
            member3.setUsername("LINUX KING POONGHYEOK");

            System.out.println("@@@@@@persist start@@@@@@");
            em.persist(member);
            em.persist(member2);
            em.persist(member3);
            System.out.println("memeber.id 지금 받을 수 있음 ? " + member.getId());
            System.out.println("@@@@@@persist end@@@@@@");*/

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            //영속성 컨텍스트의 1차캐시말고 직접 DB에서 받아오는 쿼리를 보고싶을 떄
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, member.getTeam().getId());
            System.out.println("find Team name : " + findTeam.getName());


            System.out.println("@@@@@@commit start@@@@@@");
            tx.commit();
            System.out.println("@@@@@@commit end@@@@@@");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }



    }
}
