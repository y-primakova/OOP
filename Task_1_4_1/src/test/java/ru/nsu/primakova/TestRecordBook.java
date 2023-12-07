package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

/**
 * Class TestRecordBook.
 */
public class TestRecordBook {
    @Test
    public void testAddGrade() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.addGrade("A", 5);
        var actual = new HashMap<Integer, HashMap<String, Integer>>();
        actual.put(1, new HashMap<>());
        actual.get(1).put("A", 5);

        assertEquals(actual, book.get_grades());
    }

    @Test
    public void testAddGradeCoursework() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.addGradeCoursework(5);

        assertEquals(5, book.get_gradeCoursework());
    }

    @Test
    public void testNextSemester() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.nextSemester();
        book.nextSemester();
        book.nextSemester();

        assertEquals(4, book.get_semester());
    }

    @Test
    public void testIncreasedStipend() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.addGrade("A", 5);
        book.addGrade("B", 4);

        assertFalse(book.increasedStipend());

        book.nextSemester();
        book.addGrade("C", 5);
        book.addGrade("D", 5);

        assertTrue(book.increasedStipend());
    }

    @Test
    public void testAverageGrade() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.addGrade("A", 5);
        book.addGrade("B", 3);
        book.addGrade("C", 4);
        book.addGrade("D", 4);
        book.nextSemester();
        book.addGrade("A", 4);
        book.addGrade("B", 4);
        book.addGrade("E", 3);
        book.nextSemester();
        book.addGrade("A", 5);
        book.addGrade("D", 5);
        book.addGrade("F", 5);

        assertEquals(4.33, book.averageGrade());
    }

    @Test
    public void testRedDiplomaTrue() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.addGrade("A", 5);
        book.addGrade("B", 5);
        book.addGrade("C", 4);
        book.addGrade("D", 5);
        book.nextSemester();
        book.addGrade("A", 4);
        book.addGrade("B", 4);
        book.addGrade("E", 4);
        book.nextSemester();
        book.addGrade("A", 5);
        book.addGrade("D", 4);
        book.addGrade("F", 5);
        book.addGrade("G", 4);
        book.addGrade("H", 5);
        book.nextSemester();
        book.addGrade("A", 4);
        book.addGrade("E", 5);
        book.addGrade("I", 5);
        book.addGradeCoursework(5);

        assertTrue(book.redDiploma());
    }

    @Test
    public void testRedDiplomaFalse() throws WrongValueException {
        var book = new RecordBook("name", "surname", 1);
        book.addGrade("A", 4);
        book.addGrade("B", 5);
        book.addGrade("C", 4);
        book.addGrade("D", 5);
        book.nextSemester();
        book.addGrade("A", 4);
        book.addGrade("B", 4);
        book.addGrade("E", 4);
        book.nextSemester();
        book.addGrade("A", 5);
        book.addGrade("D", 4);
        book.addGrade("F", 5);
        book.addGrade("G", 4);
        book.addGrade("H", 5);
        book.nextSemester();
        book.addGrade("A", 4);
        book.addGrade("E", 5);
        book.addGrade("I", 5);
        book.addGradeCoursework(5);

        assertFalse(book.redDiploma());
    }
}
