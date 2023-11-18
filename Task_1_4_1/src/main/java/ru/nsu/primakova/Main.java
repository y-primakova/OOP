package ru.nsu.primakova;

/**
 * Class Main.
 */
public class Main {
    public static void main(String[] args) throws WrongValueException {
        var book = new RecordBook("Julia", "Primakova", 22216);
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
        book.addGradeCoursework(5);

        System.out.println(book);
        System.out.println(book.lastGrades());
        System.out.println(book.increasedStipend());
        System.out.println(book.averageGrade());
        System.out.println(book.redDiploma());
    }
}