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
public class Course extends Entity implements Serializable {
    String name;
    String description;
    double credit;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCredit() {
        return credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public void save() {
        Database.saveCourse(this);
    }

    @Override
    public void update() {
        Database.saveCourse(this);
    }

    @Override
    public void delete() {
        Database.deleteCourse(this);
    }

    @Override
    public String toString() {
        return this.getId()+" "+this.name;
    }
    
    
    
}
