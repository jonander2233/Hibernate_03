package es.ada.u3.hibernate.dao;

import es.ada.u3.hibernate.dao.utils.HibernateSessionFactory;
import es.ada.u3.hibernate.entities.Employee;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class EmployeeDAO {
    private static EmployeeDAO instance = new EmployeeDAO();

    private EmployeeDAO() {

    }

    public static EmployeeDAO getInstance() {
        return instance;
    }

    public Employee addEmployee(Employee employee) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(employee); /* .save is deprecated */
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> loadEmployees() {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            TypedQuery<Employee> query = session.createNativeQuery("select * FROM Employee", Employee.class);
            List<Employee> employees = query.getResultList();

            return employees;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void updateEmployeeSalary(Integer employeeID, Integer salary) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeID);
            employee.setSalary(salary);
            session.merge(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }
    public void deleteEmployee(Integer EmployeeID) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class,
                    EmployeeID);
            session.remove(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }
}
