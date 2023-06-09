package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            member.changeTeam(team); //단순히 setter를 쓰지 않고 changeTeam이라고 사용한다. 개개개개개꿀팁
            em.persist(member);


//            team.getMemberList().add(member); //update query가 날라가는 것은 아니지만 List 객체에는 member값을 셋팅 해줘야 한다. 시점 차이로 아래와 같은 오류가 발생할 수 있기 때문이다. => setter 속으로 집어넣자

            //영속성 컨텍스트의 1차캐시말고 직접 DB에서 받아오는 쿼리를 보고싶을 떄
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시에 있음. flush() clear로 db에서 다시 긁어오지 않는 이상 그냥 team이 들어가있고 team에는 member가 빈 상태임. ㄷㄷ 그럼 addmember해도 member의 team_id가 변경되도록 하면 안되나
            List<Member> members = findTeam.getMemberList();

            for (Member m : members) {
                System.out.println("member name = " + m.getUsername());
            }


            System.out.println("\n@@@@@@commit start@@@@@@");
            tx.commit();
            System.out.println("\n@@@@@@commit end@@@@@@");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }



    }
}
