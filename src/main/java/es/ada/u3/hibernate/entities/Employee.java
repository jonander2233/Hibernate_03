
package es.ada.u3.hibernate.entities;


import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name = "employee")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "salary")
    private Integer salary;

    private Set<Certificate> certificates = null;
    public Employee() {}

    public Employee(String fname, String lname, Integer salary) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
        this.certificates   = new LinkedHashSet<Certificate>();
    }
    public Employee(String fname, String lname, Integer salary,Set<Certificate> certificates) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
        this.certificates   = new LinkedHashSet<Certificate>(certificates);
    }

    public Integer getId() {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( String first_name ) {
        this.firstName = first_name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName( String last_name ) {
        this.lastName = last_name;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary( Integer salary ) {
        this.salary = salary;
    }
    public void addCertificate(Certificate certificate){
        certificates.add(certificate);
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }
}
