/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author zhuan
 */
public class Database {

    private static Connection dbConnection = null;

    public Database() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "developer");
        connectionProps.put("password", "developer");
        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/studentdb",
                    connectionProps);
        }
    }

    public Exam getExam(String id) throws SQLException {
        String sql = "Select id,description,course,percentage From exam Where id=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        Exam exam = null;
        try {
            ResultSet result = statement.executeQuery();
            exam = new Exam();
            if (result.next()) {
                exam.setId(result.getString("id"));
                exam.setDescription(result.getString("description"));
                Course course = getCourse(result.getString("course"));
                exam.setCourse(course);
                exam.setPercentage(result.getInt("percentage"));

            }
        } finally {
            statement.close();
        }
        return exam;
    }

    public Student getStudent(String studentId) throws SQLException {
        String sql = "Select student.id,student.name, student.age,student.gender,student.grade,student.hompephone,"
                + "student.cellphone,student.email,address.room, address.streetNumber,address.street,address.city,"
                + "address.province,address.postcode From student,address Where student.addressId=address.id And student.id=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(studentId));
        Student student = null;
        try {
            ResultSet result = statement.executeQuery();
            student = new Student();
            if (result.next()) {
                student.setId(result.getString(1));
                student.setName(result.getString(2));
                student.setAge(result.getInt(3));
                int gender = result.getInt(4);
                if (gender == 0) {
                    student.setGender(Gender.Male);
                } else {
                    student.setGender(Gender.Female);
                }
                student.setGrade(result.getInt(5));
                Contact contact = new Contact();
                contact.setHomephone(result.getString(6));
                contact.setCellphone(result.getString(7));
                contact.setEmail(result.getString(8));
                student.setContact(contact);
                Address address = new Address();
                address.setRoom(result.getString(9));
                address.setStreetNumber(result.getString(10));
                address.setSteet(result.getString(11));
                address.setCity(result.getString(12));
                address.setPostcode(result.getString(13));
                address.setPostcode(result.getString(14));
                student.setAddress(address);

            }
        } finally {
            statement.close();
        }
        return student;
    }

    public Course getCourse(String id) throws SQLException {
        String sql = "Select name,description,credit From course Where id=? ";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        statement.setString(1, id);
        Course course = null;
        try {
            ResultSet result = statement.executeQuery();
            course = new Course();
            if (result.next()) {
                course.setId(id);
                course.setName(result.getString("name"));
                course.setDescription(result.getString("description"));
                course.setCredit(result.getDouble("credit"));
            }
        } finally {
            statement.close();
        }
        return course;
    }

    public ExamReport getExamReport(String id) throws SQLException {
        String sql = "Select examId,studentId,score,testingDate From exam_report Where id=? ";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        statement.setString(1, id);
        ExamReport examReport = new ExamReport();
        try {
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                examReport.setId(id);
                examReport.setExamId(result.getString("examId"));
                examReport.setStudentId(result.getString("studentId"));
                examReport.score = result.getInt("score");
                examReport.setDate(result.getDate("testingDate"));
            }
        } finally {
            statement.close();
        }
        return examReport;
    }

    static void loadAll() {
    }

    public void saveAll() {

    }

    public ArrayList<Student> getAllStudents() throws SQLException {
        String sql = "Select student.id,student.name, student.age,student.gender,student.grade,student.hompephone,"
                + "student.cellphone,student.email,address.room, address.streetNumber,address.street,address.city,"
                + "address.province,address.postcode From student,address Where student.addressId=address.id order by student.name";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        ArrayList<Student> students = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Student student = new Student();
                student.setId(result.getString(1));
                student.setName(result.getString(2));
                student.setAge(result.getInt(3));
                int gender = result.getInt(4);
                if (gender == 0) {
                    student.setGender(Gender.Male);
                } else {
                    student.setGender(Gender.Female);
                }
                student.setGrade(result.getInt(5));
                Contact contact = new Contact();
                contact.setHomephone(result.getString(6));
                contact.setCellphone(result.getString(7));
                contact.setEmail(result.getString(8));
                student.setContact(contact);
                Address address = new Address();
                address.setRoom(result.getString(9));
                address.setStreetNumber(result.getString(10));
                address.setSteet(result.getString(11));
                address.setCity(result.getString(12));
                address.setPostcode(result.getString(13));
                address.setPostcode(result.getString(14));
                student.setAddress(address);
                students.add(student);
            }
        } finally {
            statement.close();
        }
        return students;
    }

    void saveStudent(Student student) throws SQLException {
        String sql = "Select id From address Where room=? and streetNumber=? and street=? and city=? and province=? and postcode=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        int addressId = 0;
        try {
            statement.setString(1, student.address.getRoom());
            statement.setString(2, student.address.getStreetNumber());
            statement.setString(3, student.address.getStreet());
            statement.setString(4, student.address.getCity());
            statement.setString(5, student.address.getProvince());
            statement.setString(6, student.address.getPostcode());
            ResultSet result = statement.executeQuery();

            if (result.next()) { // found address
                addressId = result.getInt("id");
            } else { // not found. insert a new address.
                statement.close();
                sql = "Insert Into address (room,streetNumber,street,city,province,postcode) Values (?,?,?,?,?,?)";
                statement = Database.dbConnection.prepareStatement(sql);
                statement.setString(1, student.getAddress().getRoom());
                statement.setString(2, student.getAddress().getStreetNumber());
                statement.setString(3, student.getAddress().getStreet());
                statement.setString(4, student.getAddress().getCity());
                statement.setString(5, student.getAddress().getProvince());
                statement.setString(6, student.getAddress().getPostcode());

                statement.executeUpdate();

                statement.close();
                sql = "Select LAST_INSERT_ID()";
                statement = Database.dbConnection.prepareStatement(sql);
                result = statement.executeQuery();
                if (result.next()) {
                    addressId = result.getInt(1);
                }
            }
        } finally {
            statement.close();
        }

        int gender;
        if (student.getGender() == Gender.Male) {
            gender = 0;
        } else {
            gender = 1;
        }

        sql = "Replace student (name,age,gender,grade,homephone,cellphone,email,addressId) Values (?,?,?,?,?,?,?,?,?) Where id=?";
        statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, gender);
            statement.setInt(4, student.getGrade());
            statement.setString(5, student.getContact().getHomephone());
            statement.setString(6, student.getContact().getCellphone());
            statement.setString(7, student.getContact().getEmail());
            statement.setInt(8, addressId);
            statement.setString(9, student.getId());

            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    void deleteStudent(Student student) throws SQLException {

        String sql = "Delete From student Where id=? ";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, student.getId());
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    public ArrayList<Exam> getAllExams() throws SQLException {
        ArrayList<Exam> ar = new ArrayList<>();
        String sql = "Select id,description,course,percentage From exam order by id";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Exam exam = new Exam();
                exam.setId(result.getString("id"));
                exam.setDescription(result.getString("description"));
                Course course = getCourse(result.getString("course"));
                exam.setCourse(course);
                exam.setPercentage(result.getInt("percentage"));
                ar.add(exam);
            }
        } finally {
            statement.close();
        }
        return ar;
    }

    /*public static ExamReport findExamReport(String examReportId) {
        if (examReports.containsKey(examReportId)) {
            return examReports.get(examReportId);
        }
        return null;
    }*/
    public ArrayList<Course> getAllCourses() throws SQLException {
        ArrayList<Course> ar = new ArrayList<>();
        String sql = "Select id, name,description,credit From course order by id";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Course course = new Course();
                course.setId(result.getString("id"));
                course.setName(result.getString("name"));
                course.setDescription(result.getString("description"));
                course.setCredit(result.getDouble("credit"));
                ar.add(course);
            }
        } finally {
            statement.close();
        }
        return ar;
    }

    void saveCourse(Course course) throws SQLException {

        String sql = "Replace (name,description,credit) Values (?,?,?) From course Where id=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setDouble(3, course.getCredit());
            statement.setString(4, course.getId());
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    void deleteCourse(Course course) throws SQLException {

        String sql = "Delete From course Where id=? ";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, course.getId());
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    void saveExam(Exam exam) throws SQLException {
        String sql = "Replace (description,course, percentage) Values (?,?,?) From exam Where id=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, exam.getDescription());
            statement.setString(2, exam.getCourse().getId());
            statement.setInt(3, exam.getPercentage());
            statement.setString(4, exam.getId());
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    void deleteExam(Exam exam) throws SQLException {
        String sql = "Delete From exam Where id=? ";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, exam.getId());
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    void saveExamReport(ExamReport examReport) throws SQLException {
        String sql = "Replace (score,testDate) Values (?,?) From course Where examId=? and studentId=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setInt(1, examReport.getScore());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            statement.setDate(2, java.sql.Date.valueOf(dateFormat.format(examReport.getDate())));
            statement.setString(3, examReport.getExamId());
            statement.setString(4, examReport.getStudentId());
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    void deleteExamReport(ExamReport examReport) throws SQLException {

        String sql = "Delete From exam_report Where studentId=? and examId=?";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, examReport.getStudentId());
            statement.setString(2, examReport.getExamId());

            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    int getCurrentSemester() {
        Date date = new Date();
        return getSemester(date);

    }

    public ArrayList<ExamReport> getStudentExams(String studentId) throws SQLException {
        String sql = "Select examId,studentId,score,testingDate From exam_report Where studentId=? order by testingDate order by DESC";
        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        ArrayList<ExamReport> al = new ArrayList<>();

        try {
            statement.setString(1, studentId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                ExamReport examReport = new ExamReport();
                examReport.setExamId(result.getString(1));
                examReport.setStudentId(result.getString(2));
                examReport.setScore(result.getInt(3));
                examReport.setDate(result.getDate(4));
                al.add(examReport);
            }
        } finally {
            statement.close();
        }

        return al;

    }

    private int getSemester(Date date) {
        if (date.getMonth() < 9) {
            return 2;
        } else {
            return 1;
        }
    }

    private void addExamReportToStudentExamReports(ExamReport examReport) throws SQLException {
        String sql = "Replace exam_report (examId,studentId,score,testingDate) Values (?,?,?,?)";

        PreparedStatement statement = Database.dbConnection.prepareStatement(sql);
        try {
            statement.setString(1, examReport.getExamId());
            statement.setString(2, examReport.getStudentId());
            statement.setInt(3, examReport.getScore());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            statement.setDate(4, java.sql.Date.valueOf(dateFormat.format(examReport.getDate())));

            statement.executeUpdate();
        } finally {
            statement.close();
        }

    }

}
