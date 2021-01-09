/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

/**
 *
 * @author zhuan
 */
public class Report {
    Student student=null;
    ArrayList<ExamReport> examReports=null;
    String teacherComment=null;
    int semester=1;
    int grade=1;

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public ArrayList<ExamReport> getExamReports() {
        return examReports;
    }

    public int getGrade() {
        return this.student.getGrade();
    }

    public int getSemester() {
        return this.semester;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setStudent(Student student) throws SQLException {
        this.student = student;
        this.examReports=new Database().getStudentExams(student.getId());
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }
    
    @Override
    public String toString() {
        double score=0;
        int credit=0;
        ArrayList<String> courses=new ArrayList();
        String ret="";
        Date today=new Date();
        try {
            for (ExamReport examReport:examReports) {
            
                int gradeToTakeExam=this.student.getGrade()-(today.getYear()-examReport.getDate().getYear());
                int semesterToTakeExam=getSemester(examReport.getDate());
                if (this.getSemester()!=semesterToTakeExam || this.grade!=gradeToTakeExam) continue;
                ret+=examReport.getExam().toString()+":Course: "+examReport.getExam().getCourse()+", Score:"+examReport.getScore();
                ret+=",Percentage:"+examReport.getExam().getPercentage()+"%\n";
                score+=examReport.getScore()*examReport.getExam().getPercentage()/100;
                if (!courses.contains(examReport.getExam().getCourse().getId())) {
                    credit+=examReport.getExam().getCourse().getCredit();
                    courses.add(examReport.getExam().getCourse().getId());
                }
            }
            ret+="Total Score:"+score+"\n";
            ret+="Credit:"+credit;
            return ret; 
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private int getSemester(Date date) {
        return date.getMonth()<9?2:1;
    }
}
