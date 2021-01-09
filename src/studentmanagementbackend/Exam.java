/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.io.Serializable;
import java.sql.SQLException;
/**
 *
 * @author zhuan
 */
public class Exam  extends Entity implements Serializable{

    String description;
    Course course;
    int percentage;

    public String getDescription() {
        return description;
    }

    public Course getCourse() {
        return course;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
    

    @Override
    public void save() throws SQLException  {
        new Database().saveExam(this);
    }

    @Override
    public void update() throws SQLException {
        new Database().saveExam(this);
    }

    @Override
    public void delete() throws SQLException {
        new Database().deleteExam(this);
    }
    
    @Override
    public String toString() {
        return this.getId()+"-"+this.getDescription();
    }

    
    
}
