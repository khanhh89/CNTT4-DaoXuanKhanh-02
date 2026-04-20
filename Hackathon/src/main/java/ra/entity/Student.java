package ra.entity;
import java.util.Scanner;
public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private double gpa;
    public Student() {}

    public Student(String studentId, String studentName, int age, double gpa) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.gpa = gpa;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên sinh viên: ");
            this.studentName = scanner.nextLine().trim();
            if (!this.studentName.isEmpty()) break;
            System.out.println("Tên không được để trống!");
        }

        while (true) {
            try {
                System.out.print("Nhập tuổi: ");
                this.age = Integer.parseInt(scanner.nextLine());
                if (this.age > 0) break;
                System.out.println("Tuổi phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("Tuổi phải là số nguyên!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập GPA: ");
                this.gpa = Double.parseDouble(scanner.nextLine());
                if (this.gpa >= 0 && this.gpa <= 10) break;
                System.out.println("GPA phải từ 0 đến 10!");
            } catch (NumberFormatException e) {
                System.out.println("GPA phải là số thực!");
            }
        }
    }

    public void displayData() {
        String rank = gpa >= 8.0 ? "Giỏi" : (gpa >= 6.5 ? "Khá" : "Trung Bình");
        System.out.printf("ID: %-6s | Tên: %-15s | Tuổi: %-3d | GPA: %-5.2f | Xếp loại: %-10s\n",
                studentId, studentName, age, gpa, rank);
    }
}