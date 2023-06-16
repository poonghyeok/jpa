package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("king director poonghyeok");
            movie.setActor("king actor poonghyeok");
            movie.setName("dev king poonghyok moive");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear(); //1차 캐시가 아니라 db 쿼리 로그를 보기 위해서...

            Movie findMoive = em.find(Movie.class, movie.getId());
            System.out.println("findMoive price : " + findMoive.getPrice());


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
