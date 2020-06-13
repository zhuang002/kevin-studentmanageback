/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.util.ArrayList;
import java.util.Hashtable;
import org.json.simple.*;

/**
 *
 * @author zhuan
 */
public class Database {

    static private Hashtable<String,Student> students=null;
    static private Hashtable<String,Exam> exams=null;
    static private Hashtable<String,Course> courses=null;
    static private Hashtable<String,ExamReport> examReports=null;
    
    static Exam getExam(String id) {
        if (exams==null) loadAll();
        return exams.get(id);
    }

    static Student getStudent(String studentId) {
        if (students==null) loadAll();
        return students.get(studentId);
    }

    static Entity getCourse(String id) {
        if (courses==null) loadAll();
        return courses.get(id);
    }
    
    static Entity getExamReport(String id) {
        if (examReports==null) loadAll();
        return examReports.get(id);
    }

    public static void loadAll() {
        if (students==null) {
            // To be implemented.
        }
    }
    
    public static void saveAll() {
        
    }

    public static ArrayList<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }
    
    
}
