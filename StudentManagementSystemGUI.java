
package studentmanagementsystemgui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }
}

class SMS {
    private List<Student> students;

    public SMS() {
        students = new java.util.ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagementSystemGUI extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;
    private JTextArea resultArea;
    private SMS sms;

    public StudentManagementSystemGUI() {
        sms = new SMS();

        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        String grade = gradeField.getText();
        Student student = new Student(name, rollNumber, grade);
        sms.addStudent(student);
        displayAllStudents();
        clearInputFields();
    }

    public void displayAllStudents() {
        List<Student> students = sms.getAllStudents();
        if (students.isEmpty()) {
            resultArea.setText("No students found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student student : students) {
                sb.append("Name: ").append(student.getName()).append("\n");
                sb.append("Roll Number: ").append(student.getRollNumber()).append("\n");
                sb.append("Grade: ").append(student.getGrade()).append("\n");
                sb.append("----------").append("\n");
            }
            resultArea.setText(sb.toString());
        }
    }

    private void clearInputFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StudentManagementSystemGUI gui = new StudentManagementSystemGUI();
                gui.setVisible(true);
            }
        });
    }
}
