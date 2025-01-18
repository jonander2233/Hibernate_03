package es.ada.u3.hibernate;

import es.ada.u3.hibernate.dao.CertificateDAO;
import es.ada.u3.hibernate.dao.EmployeeDAO;
import es.ada.u3.hibernate.entities.Certificate;
import es.ada.u3.hibernate.entities.Employee;
import org.jonander2233.Lang;
import org.jonander2233.lib_personal.Eys;
import org.jonander2233.lib_personal.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    private static final CertificateDAO CE_DAO = CertificateDAO.getInstance();
    private static final EmployeeDAO EM_DAO = EmployeeDAO.getInstance();
    private static final Menu menu1 = new Menu(Lang.EN);
    private static final Eys eys = new Eys(Lang.EN);

    public static void main(String[] args) {
        boolean exit = false;
        do {
            int option = menu1.show("Employee Management",new String[]{"Add employee","See All Employees","Add certificate","Add certificate to employee"},"Exit");
            switch (option){
                case 0:
                    exit = true;
                    break;
                case 1:
                    addNewEmployee();
                    break;
                case 2:
                    printEmployees(EM_DAO.loadEmployees());
                    break;
                case 3:
                    addNewCertificate();
                    break;
                case 4:
                    linkCertificate();
                    break;
            }
        }while (!exit);
    }

    private static void addNewCertificate(){
        String certificateName = eys.imprimirYLeer("Put certificate Name:",1,10);
        CE_DAO.addCertificate(new Certificate(certificateName));
    }
    private static void linkCertificate(){
        ArrayList<String> certificates = getCertificatesString(CE_DAO.loadCertificates());
        menu1.show("Select one of them",certificates.toArray(new String[0]),"back");
        //por aquí me he quedado

    }

    private static void addNewEmployee(){
        String firstName = eys.imprimirYLeer("Name of the employee:",1,10);
        String secondName = eys.imprimirYLeer("Surname of the employee",1,10);
        Integer salary = eys.imprimirYLeerInt("Salary of the employee", 1,10);
        addEmployee(firstName,secondName,salary);
    }
    private static void addEmployee(String fname, String iname, int salary){
        Employee emp = EM_DAO.addEmployee(new Employee(fname, iname, salary));
    }
    private static void addEmployee(String fname, String iname, int salary, Certificate certificate){
        Employee emp = new Employee(fname,iname,salary);
        emp.addCertificate(certificate);
        EM_DAO.addEmployee(emp);

    }

    private static ArrayList<String> getCertificatesString(List<Certificate> certificates){
        ArrayList<String> names = new ArrayList<>();
        if (certificates.isEmpty()){
            return null;
        }else{
            for (Iterator<Certificate> iterator = certificates.iterator(); iterator.hasNext();) {
                Certificate certificate = (Certificate) iterator.next();
                names.add("Certificate: " + certificate.getName());
            }
        }
        return names;
    }
    private static void printCertificates(List<Certificate> certificates) {
        if (certificates.isEmpty()){
            System.out.println("******** No items found");
        }else{
            for (Iterator<Certificate> iterator = certificates.iterator(); iterator.hasNext();) {
                Certificate certificate = (Certificate) iterator.next();
                System.out.println("Certificate name :" + certificate.getName());
            }
        }
    }

    private static void printEmployees(List<Employee> employees) {
        if (employees.isEmpty()){
            System.out.println("******** No items found");
        }else{
            for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName() + " | ");
                System.out.print("Last Name: " + employee.getLastName() + " | ");
                System.out.println("Salary: " + employee.getSalary() + " | ");
                System.out.println("Certificate Name: " + employee.getCertificates());
            }
        }
    }
}



