package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

public class ServiceMockTest {

    private static Service service;
    @Mock
    private StudentXMLRepository fileRepository1;
    @Mock
    private HomeworkXMLRepository fileRepository2;
    @Mock
    private GradeXMLRepository fileRepository3;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        fileRepository1 = mock(StudentXMLRepository.class);
        fileRepository2 = mock(HomeworkXMLRepository.class);
        fileRepository3 = mock(GradeXMLRepository.class);
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testFindAllStudents() {
        Collection<Student> students= Arrays.asList(new Student("1", "Alpar", 534), new Student("2", "Bela", 533));
        Mockito.doReturn(students).when(fileRepository1).findAll();
        students = (Collection<Student>) service.findAllStudents();
        assertTrue(students.size() == 2);
    }

    @Test
    public void testFindAllHomework() {
        Collection<Homework> homework = Arrays.asList(new Homework("1", "Short desc", 2, 4), new Homework("2", "Short desc", 4, 6), new Homework("3", "Short desc", 8, 12));
        Mockito.doReturn(homework).when(fileRepository2).findAll();
        Collection<Homework> finalHomework = (Collection<Homework>) service.findAllHomework();
        assertAll("homework", () -> assertEquals(3, finalHomework.size()));
    }

}
