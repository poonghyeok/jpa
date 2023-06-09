package prac.ecommerce;

import prac.ecommerce.domain.Book;
import prac.ecommerce.domain.Member;
import prac.ecommerce.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commerce");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setName("Dopamination");
            book.setAuthor("poonghyoek");

            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }



    }

}
