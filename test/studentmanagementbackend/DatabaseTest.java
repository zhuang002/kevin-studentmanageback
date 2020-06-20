/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zhuan
 */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        Database.loadAll();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getExam method, of class Database.
     */
    @Test
    public void testGetExam() {
        System.out.println("getExam");
        String id = "testExam";
        Exam expResult = new Exam();
        expResult.setId(id);
        expResult.setDescription("testExamDesc");
        expResult.setPercentage(10);
        Course course=new Course();
        course.setId("testCourse");
        course.setCredit(1);
        course.setDescription("testCourseDesc");
        course.setName("testExamName");
        expResult.setCourse(course);
        
        Exam result = Database.getExam(id);
        assertNull(result);
        
        expResult.save();
        result = Database.getExam(id);
        assertEquals(expResult, result);
        assertEquals(expResult.getPercentage(),result.getPercentage());
        assertEquals(expResult.getDescription(),result.getDescription());
        assertEquals(expResult.getCourse(),result.getCourse());
        // TODO review the generated test code and remove the de//fail("The test case is a prototype.");
    }

    /**
     * Test of getStudent method, of class Database.
     */
    @Test
    public void testGetStudent() {
        System.out.println("getStudent");
        String studentId = "testStudent";
        Student expResult = new Student();
        expResult.setId(studentId);
        Address address=new Address();
        address.setRoom("1");
        address.setStreetNumber("2");
        address.setSteet("street");
        address.setCity("city");
        address.setProvince("province");
        address.setPostcode("postcode");
        expResult.setAddress(address);
        expResult.setName("name");
        expResult.setAge(3);
        expResult.setGender(Gender.Female);
        expResult.setGrade(4);
        Contact contact=new Contact();
        contact.setCellphone("cellphone");
        contact.setEmail("email");
        contact.setHomephone("homephone");
        expResult.setContact(contact);
        expResult.save();
        Student result = Database.getStudent(studentId);
        assertEquals(expResult, result);
        assertEquals(expResult.getName(),result.getName());
        assertEquals(expResult.getAge(),result.getAge());
        assertEquals(expResult.getGender(),result.getGender());
        assertEquals(expResult.getAddress().getRoom(),result.getAddress().getRoom());
        assertEquals(expResult.getAddress().getPostcode(),result.getAddress().getPostcode());
        assertEquals(expResult.getContact().getCellphone(),result.getContact().getCellphone());
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCourse method, of class Database.
     */
    @Test
    public void testGetCourse() {
        System.out.println("getCourse");
        String id = "testCourse";
        Course expResult = new Course();
        expResult.setId(id);
        expResult.setCredit(1);
        expResult.setDescription("testCourseDesc");
        expResult.setName("testExamName");
        expResult.save();
        Course result = Database.getCourse(id);
        assertEquals(expResult, result);
        assertEquals(expResult.getName(),result.getName());
        assertEquals(expResult.getCredit(),result.getCredit());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getExamReport method, of class Database.
     */
    @Test
    public void testGetExamReport() {
        System.out.println("getExamReport");
        String id = "testExamReport";
        ExamReport expResult = new ExamReport();
        expResult.setId(id);
        expResult.setDate(new Date(123456));
        expResult.setExam(Database.getExam("testExam"));
        expResult.setStudent(Database.getStudent("testStudent"));
        expResult.setScore(90);
        expResult.save();
        ExamReport result = Database.getExamReport(id);
        assertEquals(expResult, result);
        assertEquals(expResult.getExam(),result.getExam());
        assertEquals(expResult.getScore(),result.getScore());
        assertEquals(expResult.getStudent(),result.getStudent());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    /**
     * Test of deleteStudent method, of class Database.
     */
    @Test
    public void testDeleteStudent() {
        System.out.println("deleteStudent");
        Student student = new Student();
        student.setId("testStudent");
        student.save();
        Database.deleteStudent(student);
        student=Database.getStudent("testStudent");
        assertNull(student);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAllExams method, of class Database.
     */
    @Test
    public void testGetAllExams() {
        System.out.println("getAllExams");
        ArrayList<Exam> expResult = null;
        ArrayList<Exam> result = Database.getAllExams();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

    /**
     * Test of deleteCourse method, of class Database.
     */
    @Test
    public void testDeleteCourse() {
        System.out.println("deleteCourse");
        Course course = new Course();
        course.setId("testCourse");
        course.save();
        Database.deleteCourse(course);
        assertNull(Database.getCourse("testCourse"));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    /**
     * Test of deleteExam method, of class Database.
     */
    @Test
    public void testDeleteExam() {
        System.out.println("deleteExam");
        Exam exam = new Exam();
        exam.setId("testExam");
        exam.save();
        Database.deleteExam(exam);
        assertNull(Database.getExam("testExam"));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   

    /**
     * Test of deleteExamReport method, of class Database.
     */
    @Test
    public void testDeleteExamReport() {
        System.out.println("deleteExamReport");
        ExamReport examReport = new ExamReport();
        examReport.setId("testExamReport");
        examReport.save();
        Database.deleteExamReport(examReport);
        assertNull(Database.getExamReport("testExamReport"));
        // TODO review the generated test code and remove the default call to fail.
        
    }

    

    
    
}
