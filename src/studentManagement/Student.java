package studentManagement;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Student {

    enum Subject {
        English,
        Mathematic,
        Literature,
    }

    enum Classification {
        A,
        B,
        C,
        D,
    }

    private String id;
    private String name;
    private HashMap<Subject, Float> scores;
    private Float avgScore;
    private Classification classification;

    private void setAvgScore() {
        int n = Subject.values().length;
        this.avgScore = (float) 0;
        for (Subject subject : Subject.values()) {
            this.avgScore += getScore(subject) / n;
        }
    }

    private void setClassification() {
        if (avgScore > 8) {
            this.classification = Classification.A;
        } else if (avgScore > 6) {
            this.classification = Classification.B;
        } else if (avgScore > 4) {
            this.classification = Classification.C;
        } else {
            this.classification = Classification.D;
        }
    }

    public Student() {
        this.scores = new HashMap<>();

        for (Subject subject : Subject.values()) {
            this.scores.put(subject, (float) 0);
        }

        this.setAvgScore();
        this.setClassification();
    }

    public Student(String _id, String _name) {
        this();
        this.id = _id;
        this.name = _name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Float getScore(Subject subject) {
        return scores.get(subject);
    }

    public Float getAvgScore() {
        return this.avgScore;
    }

    public Classification getClassification() {
        return this.classification;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setScore(Subject subject, Float _score) {
        scores.put(subject, _score);
        this.setAvgScore();
        this.setClassification();
    }

    public void read(InputStream source, PrintStream out) {
        Scanner input = new Scanner(source);

        out.print("ID: ");
        this.setId(input.nextLine());
        out.print("Full name: ");
        this.setName(input.nextLine());

        for (Subject subject : Subject.values()) {
            out.printf("%s score: ", subject);
            this.setScore(subject, input.nextFloat());
        }

        input.close();
    }

    public void print(PrintStream out) {
        out.printf("ID: %s\tName:%s\nClassification: %s\nAverage Scores: %10.2f\n",
                this.getId(),
                this.getName(),
                this.getClassification(),
                this.getAvgScore());
        for (Subject subject : Subject.values()) {
            out.printf("\t%s: %10.2f\n", subject, getScore(subject));
        }
    }
}
