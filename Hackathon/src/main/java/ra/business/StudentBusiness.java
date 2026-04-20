package ra.business;
import ra.entity.Student;
import java.util.*;
import java.util.stream.Collectors;

public class StudentBusiness {
    private static StudentBusiness instance;
    private final List<Student> listStudent = new ArrayList<>();

    private StudentBusiness() {}

    public static synchronized StudentBusiness getInstance() {
        if (instance == null) instance = new StudentBusiness();
        return instance;
    }

    public boolean exists(String id) {
        return listStudent.stream().anyMatch(s -> s.getStudentId().equalsIgnoreCase(id));
    }

    public void findAll() {
        if (listStudent.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        listStudent.forEach(Student::displayData);
    }

    public boolean create(Student student) {
        return listStudent.add(student);
    }

    public boolean update(String id, Student newData) {
        for (Student s : listStudent) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                s.setStudentName(newData.getStudentName());
                s.setAge(newData.getAge());
                s.setGpa(newData.getGpa());
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        return listStudent.removeIf(s -> s.getStudentId().equalsIgnoreCase(id));
    }

    public void searchByName(String name) {
        List<Student> result = listStudent.stream()
                .filter(s -> s.getStudentName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) System.out.println("Không tìm thấy!");
        else result.forEach(Student::displayData);
    }

    public void filter() {
        System.out.println("Sinh viên Giỏi (GPA >= 8.0)");
        listStudent.stream().filter(s -> s.getGpa() >= 8.0).forEach(Student::displayData);
    }

    public void sortByGpa() {
        listStudent.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .forEach(Student::displayData);
    }
}