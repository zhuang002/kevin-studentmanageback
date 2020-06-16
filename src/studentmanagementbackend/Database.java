/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 *
 * @author zhuan
 */
public class Database {

    static private Hashtable<String, Student> students = null;
    static private Hashtable<String, Exam> exams = null;
    static private Hashtable<String, Course> courses = null;
    static private Hashtable<String, ExamReport> examReports = null;

    static Exam getExam(String id) throws IOException, FileNotFoundException, ClassNotFoundException {
        if (exams == null) {
            loadAll();
        }
        return exams.get(id);
    }

    static Student getStudent(String studentId)  {
        if (students == null) {
            loadAll();
        }
        return students.get(studentId);
    }

    static Entity getCourse(String id)  {
        if (courses == null) {
            loadAll();
        }
        return courses.get(id);
    }

    static Entity getExamReport(String id)  {
        if (examReports == null) {
            loadAll();
        }
        return examReports.get(id);
    }

    public static void loadAll()  {
        if (students == null) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("studentmanagement.db"));

                students = (Hashtable<String, Student>) in.readObject();
                courses = (Hashtable<String, Course>) in.readObject();
                exams = (Hashtable<String, Exam>) in.readObject();
                examReports = (Hashtable<String, ExamReport>) in.readObject();
                in.close();
            } catch (Exception e) {
                students=new Hashtable<>();
                courses = new Hashtable<>();
                exams=new Hashtable<>();
                examReports=new Hashtable<>();
            }
        }
    }

    public static void saveAll()  {
        if (students != null) {
            try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("studentmanagement.db"));
            out.writeObject(students);
            out.writeObject(courses);
            out.writeObject(exams);
            out.writeObject(examReports);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Student> getAllStudents() {
        if (students == null) {
            loadAll();
        }
        
        ArrayList<Student> ret=new ArrayList<>(students.values());
        Collections.sort(ret);
        return ret;
    }

    static void saveStudent(Student student) {
            students.put(student.getId(), student);
    }

    static void deleteStudent(Student student) {
        students.remove(student.getId());
    }

    public static ArrayList<Exam> getAllExams() {
        if (exams == null) {
            loadAll();
        }
        ArrayList<Exam> ret=new ArrayList<>(exams.values());
        Collections.sort(ret);
        return ret;
    }

    public static ExamReport findExamReport(String examReportId) {
        if (examReports.contains(examReportId)) 
            return examReports.get(examReportId);
        return null;
    }

    public static ArrayList<Course> getAllCourses() {
        if (courses == null) {
            loadAll();
        }
        ArrayList<Course> ret=new ArrayList<>(courses.values());
        Collections.sort(ret);
        return ret;
    }

    static void saveCourse(Course course) {
        courses.put(course.getId(), course);
    }

    static void deleteCourse(Course course) {
        courses.remove(course.getId());
    }

    static void saveExam(Exam exam) {
        exams.put(exam.getId(),exam);
    }

    static void deleteExam(Exam exam) {
        exams.remove(exam.getId());
    }

    

}
