/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.io.Serializable;

/**
 *
 * @author zhuan
 */
public class Student extends Entity implements Serializable{

    String name;
    int age;
    Gender gender;
    int grade;
    Contact contact;
    Address address;

    

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public int getGrade() {
        return grade;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public void save() {
        Database.saveStudent(this);
    }

    @Override
    public void update() {
        Database.saveStudent(this);
    }

    @Override
    public void delete() {
        Database.deleteStudent(this);
    }

    @Override
    public String toString() {
        return this.getId()+" "+this.name;
    }
    
}
