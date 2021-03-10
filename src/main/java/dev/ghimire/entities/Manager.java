package dev.ghimire.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    int managerId;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;

    @OneToMany(mappedBy = "managerId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Expense> expenses = new HashSet<>();

    public Manager() {

    }

    public Manager(int managerId,String username,String password)
    {
        this.username = username;
        this.password = password;
    }

    public Manager(int managerId, String firstName, String lastName,String username,String password) {
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
