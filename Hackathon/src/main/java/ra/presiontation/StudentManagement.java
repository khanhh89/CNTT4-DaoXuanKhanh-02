package ra.presiontation;
import ra.business.StudentBusiness;
import ra.entity.Student;
import java.util.Scanner;

public class StudentManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentBusiness service = StudentBusiness.getInstance();
    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = inputChoice();
            switch (choice) {
                case 1:
                    service.findAll();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    updateStudent();
                break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    service.filter();
                    break;
                case 7:
                    service.sortByGpa();
                    break;
                case 8:
                    System.out.println("Tạm biệt!");
                    return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Hiển thị danh sách");
        System.out.println("2. Thêm mới sinh viên");
        System.out.println("3. Cập nhật thông tin");
        System.out.println("4. Xóa sinh viên");
        System.out.println("5. Tìm kiếm sinh viên");
        System.out.println("6. Lọc sinh viên giỏi");
        System.out.println("7. Sắp xếp theo GPA (Giảm dần)");
        System.out.println("8. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }

    private static int inputChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addStudent() {
        System.out.print("Nhập số lượng sinh viên muốn thêm: ");
        int n;
        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Số lượng không hợp lệ!");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nSinh viên thứ " + (i + 1) + ":");
            Student s = new Student();
            String id;
            while (true) {
                System.out.print("Nhập mã SV: ");
                id = scanner.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("Mã SV không được để trống!");
                } else if (service.exists(id)) {
                    System.out.println("Mã SV đã tồn tại! Vui lòng nhập mã khác.");
                } else {
                    s.setStudentId(id);
                    break;
                }
            }
            s.inputData(scanner);
            service.create(s);
            System.out.println("Thêm thành công!");
        }
    }

    private static void updateStudent() {
        System.out.print("Nhập mã SV cần sửa: ");
        String id = scanner.nextLine();
        if (service.exists(id)) {
            Student newData = new Student();
            newData.inputData(scanner);
            service.update(id, newData);
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Không tìm thấy mã SV: " + id);
        }
    }

    private static void deleteStudent() {
        System.out.print("Nhập mã SV cần xóa: ");
        String id = scanner.nextLine();
        if (service.delete(id)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Không tìm thấy mã SV!");
        }
    }

    private static void searchStudent() {
        System.out.print("Nhập tên cần tìm: ");
        service.searchByName(scanner.nextLine());
    }
}