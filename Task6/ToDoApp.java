import javax.swing.*;
import java.awt.*;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public ToDoApp() {
        // Window title
        setTitle("To-Do List App");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Task list model and JList
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Input field
        taskInput = new JTextField();
        taskInput.addActionListener(e -> addTask()); // Press Enter to add task

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");

        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Add components to main panel
        panel.add(taskInput, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to frame
        add(panel);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a task.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to remove.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoApp app = new ToDoApp();
            app.setVisible(true);
        });
    }
}
