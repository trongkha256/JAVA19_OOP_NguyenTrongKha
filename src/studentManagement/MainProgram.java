package studentManagement;

public class MainProgram {
    public static void main(String[] args) {

        Student student = new Student();

        student.read(System.in, System.out);
        student.print(System.out);
    }
}
