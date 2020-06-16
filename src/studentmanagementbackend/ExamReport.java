/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zhuan
 */
public class ExamReport extends Entity implements Serializable {
    private String examId;
    private String studentId;
    int score;
    Date date;

    public String getExamId() {
        return examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Exam getExam() throws ClassNotFoundException, IOException {
        return Database.getExam(this.examId);
    }
    
    public void setExam(Exam exam) {
        this.examId=exam.getId();
    }
    
    public Student getStudent() throws IOException, FileNotFoundException, ClassNotFoundException {
        return Database.getStudent(this.studentId);
    }
    
    public void setStudent(Student student) {
        this.studentId=student.getId();
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

    
}
