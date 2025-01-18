package es.ada.u3.hibernate.dao;

import es.ada.u3.hibernate.dao.utils.HibernateSessionFactory;
import es.ada.u3.hibernate.entities.Certificate;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CertificateDAO {
    private static CertificateDAO instance = new CertificateDAO();
    private CertificateDAO(){

    }
    public static CertificateDAO getInstance(){
        return instance;
    }
//    public Certificate add
    public Certificate addCertificate(Certificate certificate) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(certificate); /* .save is deprecated */
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return certificate;
    }

    public List<Certificate> loadCertificates() {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            TypedQuery<Certificate> query = session.createNativeQuery("select * FROM Certificate", Certificate.class);
            List<Certificate> employees = query.getResultList();

            return employees;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

}

