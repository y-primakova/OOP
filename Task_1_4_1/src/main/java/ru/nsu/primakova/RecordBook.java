package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class RecordBook.
 */
public class RecordBook {
    private String name;
    private String surname;
    private int group;
    private int semester;
    private HashMap<Integer, HashMap<String, Integer>> grades;
    private int gradeCoursework;

    public RecordBook(String name, String surname, int group) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.semester = 1;
        this.grades = new HashMap<>();
        this.grades.put(this.semester, new HashMap<>());
        this.gradeCoursework = 0;
    }

    public int get_gradeCoursework() {
        return this.gradeCoursework;
    }

    public HashMap<Integer, HashMap<String, Integer>> get_grades() {
        return this.grades;
    }

    public int get_semester() {
        return this.semester;
    }

    public void addGradeCoursework(int grade) throws WrongValueException {
        if (grade > 5 || grade < 2) {
            throw new WrongValueException("Wrong value of grade of qualification work");
        }
        this.gradeCoursework = grade;
    }

    public void addGrade(String nameSubj, int grade) throws WrongValueException {
        if (grade > 5 || grade < 2) {
            throw new WrongValueException("Wrong value of grade");
        }
        this.grades.get(this.semester).put(nameSubj, grade);
    }

    public void nextSemester() throws WrongValueException {
        if (this.semester == 8) {
            throw new WrongValueException("This semester is last");
        }
        this.semester += 1;
        this.grades.put(this.semester, new HashMap<>());
    }

    public ArrayList<Integer> lastGrades() {
        var sumGrades = new HashMap<String, Integer>();
        var amount = new HashMap<String, Integer>();
        for (int sem = 1; sem < 9; sem++) {
            if (this.grades.containsKey(sem)) {
                for (var subj : this.grades.get(sem).keySet()) {
                    if (sumGrades.containsKey(subj)) {
                        sumGrades.put(subj, sumGrades.get(subj) + this.grades.get(sem).get(subj));
                        amount.put(subj, amount.get(subj) + 1);
                    } else {
                        sumGrades.put(subj, this.grades.get(sem).get(subj));
                        amount.put(subj, 1);
                    }
                }
            }
        }
        var res = new ArrayList<Integer>();
        for (var subj : sumGrades.keySet()) {
            res.add((int) Math.round((double) sumGrades.get(subj) / amount.get(subj)));
        }
        return res;
    }

    public boolean increasedStipend() {
        for (var grade : this.grades.get(this.semester).values()) {
            if (grade < 5) {
                return false;
            }
        }
        return true;
    }

    public double averageGrade() {
        int sum = 0;
        int amountGrades = 0;
        var lastGrades = this.lastGrades();
        for (var grade : lastGrades) {
            sum += grade;
            amountGrades += 1;
        }
        return Math.round((double) sum / amountGrades * Math.pow(10, 2)) / Math.pow(10, 2);
    }

    public boolean redDiploma() {
        if (this.gradeCoursework != 5) {
            return false;
        }

        for (int sem = 1; sem < 9; sem++) {
            if (this.grades.containsKey(sem)) {
                for (var grade : this.grades.get(sem).values()) {
                    if (grade <= 3) {
                        return false;
                    }
                }
            }
        }

        int numberOfFive = 0;
        var lastGrades = this.lastGrades();
        for (var grade : lastGrades) {
            if (grade == 5) {
                numberOfFive++;
            }
        }
        if (numberOfFive < 0.75 * lastGrades.size()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Student : ").append(this.name).append(" ").append(this.surname);
        str.append("\nGroup   : ").append(this.group);

        for (int sem = 1; sem < 9; sem++) {
            str.append("\nSemester ").append(sem);
            if (this.grades.containsKey(sem)) {
                if (this.grades.get(sem).isEmpty()) {
                    str.append("\n\t-");
                }
                for (var subj : this.grades.get(sem).keySet()) {
                    str.append("\n\t").append(this.grades.get(sem).get(subj));
                    str.append("\t").append(subj);
                }
            } else {
                str.append("\n\t-");
            }

        }
        return str.toString();
    }
}
