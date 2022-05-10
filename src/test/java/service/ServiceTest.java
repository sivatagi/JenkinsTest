package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ServiceTest{

    private static Service service;

    @BeforeClass
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testFindAllStudents() {
        Collection<Student> students = (Collection<Student>) service.findAllStudents();
        assertTrue(students.size() == 2);
    }

    @Test
    public void testFindAllHomework() {
        Collection<Homework> homework = (Collection<Homework>) service.findAllHomework();
        assertAll("homework", () -> assertEquals(3, homework.size()));
    }

    @Test
    public void testSaveStudent() {
        int success = service.saveStudent("12", "Teszt", 534);
        assertEquals(1, success);
        service.deleteStudent("12");
    }

    @Test
    public void testDeleteStudent() {
        int result = service.deleteStudent("2");
        assertTrue(result == 1);
        service.saveStudent("2", "Adrian", 531);
    }

    @Test
    public void testDeleteHomework() {
        int result = service.deleteHomework("valami");
        assertEquals(result, 0);
    }

}