import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

class EatTheFrogApp extends JFrame {
    private final TaskManager taskManager;
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton, editButton, deleteButton, completedButton;

    public EatTheFrogApp() {
        taskManager = new TaskManager();

        setTitle("Eat The Frog!");
        setLayout(new BorderLayout());

        // Initialize and configure UI components
        titleField = new JTextField();
        descriptionField = new JTextField();
        priorityComboBox = new JComboBox<>(new String[]{"1 (Highest)", "2", "3", "4", "5 (Lowest)"});
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        JScrollPane scrollPane = new JScrollPane(taskList);

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        completedButton = new JButton("Mark Completed");

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Task: "));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description: "));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Priority: "));
        inputPanel.add(priorityComboBox);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completedButton);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Define action listeners for buttons
        addButton.addActionListener(e -> addTask());
        editButton.addActionListener(e -> editTask());
        deleteButton.addActionListener(e -> deleteTask());
        completedButton.addActionListener(e -> markTaskAsCompleted());

        // Load tasks from the manager and update the UI
        loadTasksAndUpdateUI();

        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addTask() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            boolean duplicatePriorityExists = taskManager.getAllTasks().stream()
                    .anyMatch(task -> task.getPriority() == priority);

            if (!duplicatePriorityExists) {
                Task newTask = new Task(title, description, priority);
                taskListModel.addElement(newTask);
                taskManager.addTask(newTask);

                // Save tasks to file after adding a task
                taskManager.saveTasksToFile();

                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Priority level already exists.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = taskList.getSelectedValue();

            if (taskManager.isTaskEditable(selectedTask)) {
                String newTitle = titleField.getText();
                String newDescription = descriptionField.getText();
                int newPriority = priorityComboBox.getSelectedIndex() + 1;

                selectedTask.setTitle(newTitle);
                selectedTask.setDescription(newDescription);
                selectedTask.setPriority(newPriority);

                taskManager.editTask(selectedTask);

                // Save tasks to file after editing a task
                taskManager.saveTasksToFile();

                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Task with the same priority exists.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to edit.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                taskManager.deleteTask(selectedTask);

                // Save tasks to file after deleting a task
                taskManager.saveTasksToFile();

                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    private void markTaskAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Have you completed this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                taskManager.markTaskAsCompleted(selectedTask);

                // Save tasks to file after completing a task
                taskManager.saveTasksToFile();

                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    private void loadTasksAndUpdateUI() {
        List<Task> tasks = taskManager.loadTasksFromFile();
        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EatTheFrogApp();
        });
    }
}

class TaskManager {
    private final String fileName = "eatthefrog.txt";
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void editTask(Task task) {
        // Nothing specific to edit in this version
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void markTaskAsCompleted(Task task) {
        task.setCompleted(true);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public boolean isTaskEditable(Task task) {
        int priority = task.getPriority();
        return tasks.stream().noneMatch(t -> t != task && t.getPriority() == priority);
    }

    public void saveTasksToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadTasksFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Task>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

class Task implements Serializable {
    private String title;
    private String description;
    private int priority;
    private boolean completed;

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return priority == task.priority &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, priority);
    }
}
