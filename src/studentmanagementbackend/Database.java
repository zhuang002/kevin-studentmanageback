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
import java.util.Date;
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
    static private Hashtable<String, ArrayList<ExamReport>> studentExamReports = null;

    static public Exam getExam(String id) {
        if (exams == null) {
            loadAll();
        }
        return exams.get(id);
    }

    static public Student getStudent(String studentId) {
        if (students == null) {
            loadAll();
        }
        return students.get(studentId);
    }

    static public Course getCourse(String id) {
        if (courses == null) {
            loadAll();
        }
        return courses.get(id);
    }

    static public ExamReport getExamReport(String id) {
        if (examReports == null) {
            loadAll();
        }
        return examReports.get(id);
    }

    public static void loadAll() {
        if (students == null) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("studentmanagement.db"));

                students = (Hashtable<String, Student>) in.readObject();
                courses = (Hashtable<String, Course>) in.readObject();
                exams = (Hashtable<String, Exam>) in.readObject();
                examReports = (Hashtable<String, ExamReport>) in.readObject();
                examReports.values().forEach((examReport) -> {  // for (ExamReport examReport:examReports.values){
                    addExamReportToStudentExamReports(examReport);

                });
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                students = new Hashtable<>();
                courses = new Hashtable<>();
                exams = new Hashtable<>();
                examReports = new Hashtable<>();
                studentExamReports=new Hashtable<>();
            }
        }
    }

    public static void saveAll() {
        if (students != null) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("studentmanagement.db"));
                out.writeObject(students);
                out.writeObject(courses);
                out.writeObject(exams);
                out.writeObject(examReports);
                out.close();
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

        ArrayList<Student> ret = new ArrayList<>(students.values());
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
        ArrayList<Exam> ret = new ArrayList<>(exams.values());
        Collections.sort(ret);
        return ret;
    }

    /*public static ExamReport findExamReport(String examReportId) {
        if (examReports.containsKey(examReportId)) {
            return examReports.get(examReportId);
        }
        return null;
    }*/

    public static ArrayList<Course> getAllCourses() {
        if (courses == null) {
            loadAll();
        }
        ArrayList<Course> ret = new ArrayList<>(courses.values());
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
        exams.put(exam.getId(), exam);
    }

    static void deleteExam(Exam exam) {
        exams.remove(exam.getId());
    }

    static void saveExamReport(ExamReport examReport) {
        examReports.put(examReport.getId(), examReport);
        addExamReportToStudentExamReports(examReport);
    }

    static void deleteExamReport(ExamReport examReport) {
        examReports.remove(examReport.getId());
    }

    static int getCurrentSemester() {
        Date date = new Date();
        return getSemester(date);

    }

    public static ArrayList<ExamReport> getStudentExams(String studentId) {
        ArrayList<ExamReport> ret = new ArrayList();
        ArrayList<ExamReport> reports = studentExamReports.get(studentId);
        reports.forEach((examReport -> {
            Date today = new Date();
            if (examReport.getDate().getYear() == today.getYear()
                    && getSemester(examReport.getDate()) == getSemester(today)) {
                ret.add(examReport);
            }
        }));
        return ret;
    }

    private static int getSemester(Date date) {
        if (date.getMonth() < 9) {
            return 2;
        } else {
            return 1;
        }
    }

    private static void addExamReportToStudentExamReports(ExamReport examReport) {
        if (studentExamReports==null) studentExamReports=new Hashtable<>();
        if (!studentExamReports.containsKey(examReport.getStudentId())) {
            studentExamReports.put(examReport.getStudentId(), new ArrayList<>());
        }
        ArrayList<ExamReport> reports = studentExamReports.get(examReport.getStudentId());
        reports.add(examReport);
        studentExamReports.put(examReport.getStudentId(), reports);
    }

}
