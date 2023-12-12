import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.io.*;
import java.util.*;

class EatTheFrogFrame extends JFrame {
    private final TaskManager taskManager;
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton, editButton, deleteButton;
    private JCheckBox completedCheckBox;

    public EatTheFrogFrame() {
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
        completedCheckBox = new JCheckBox("Completed");

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
        buttonPanel.add(completedCheckBox);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Define action listeners for buttons
        addButton.addActionListener(e -> addTask());
        editButton.addActionListener(e -> editTask());
        deleteButton.addActionListener(e -> deleteTask());
        completedCheckBox.addActionListener(e -> markTaskAsCompleted());

        // Load tasks from the manager and update the UI
        loadTasksAndUpdateUI();
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
                taskManager.addTask(newTask);

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
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    private void loadTasksAndUpdateUI() {
        for (Task task : taskManager.getAllTasks()) {
            taskListModel.addElement(task);
        }
    }

    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EatTheFrogFrame frame = new EatTheFrogFrame();
            frame.setSize(500, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class TaskManager {
    private final Map<Integer, Task> taskMap;
    private final TreeSet<Task> taskSet;
    private final String fileName = "eatthefrog.txt";
    private final String frogseatenFileName = "frogseaten.txt";

    public TaskManager() {
        taskMap = new HashMap<>();
        taskSet = new TreeSet<>();
        loadTasksFromFile();
    }

    public void addTask(Task task) {
        taskMap.put(task.hashCode(), task);
        taskSet.add(task);
        saveTasksToFile();
    }

    public void editTask(Task task) {
        taskSet.remove(task);
        taskSet.add(task);
        saveTasksToFile();
    }

    public void deleteTask(Task task) {
        taskMap.remove(task.hashCode());
        taskSet.remove(task);
        saveTasksToFile();
    }

    public void markTaskAsCompleted(Task task) {
        task.setCompleted(true);
        editTask(task);
        addCompletedTaskToFrogFile(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskSet);
    }

    public boolean isTaskEditable(Task task) {
        int priority = task.getPriority();
        return taskSet.stream().noneMatch(t -> t != task && t.getPriority() == priority);
    }

    private void loadTasksFromFile() {
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    if (parts.length == 4) {
                        String title = parts[0];
                        String description = parts[1];
                        int priority = Integer.parseInt(parts[2]);
                        boolean completed = Boolean.parseBoolean(parts[3]);

                        Task loadedTask = new Task(title, description, priority);
                        loadedTask.setCompleted(completed);

                        taskMap.put(loadedTask.hashCode(), loadedTask);
                        taskSet.add(loadedTask);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : taskSet) {
                String line = task.getTitle() + "\t" + task.getDescription() + "\t" + task.getPriority() + "\t" + task.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCompletedTaskToFrogFile(Task completedTask) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(frogseatenFileName, true))) {
            String line = "Task: " + completedTask.getTitle() + ", Priority: " + completedTask.getPriority() + ", Completed: " + completedTask.isCompleted();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Comparable<Task> {
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
    public int hashCode() {
        return Objects.hash(title, description, priority);
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
    public int compareTo(Task otherTask) {
        return Integer.compare(this.priority, otherTask.priority);
    }
}
