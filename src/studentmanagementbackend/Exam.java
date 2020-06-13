/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

/**
 *
 * @author zhuan
 */
public class Exam  extends Entity{

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
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exam get(String id) {
        return Database.getExam(id);
    }
    
}
