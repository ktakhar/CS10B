import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EatTheFrogApp extends JFrame {
    private TaskManager taskManager;
    private TaskUI taskUI;

    public EatTheFrogApp() {
        setTitle("Eat The Frog!");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        taskManager = new TaskManager();
        taskUI = new TaskUI(taskManager);

        // Add the TaskUI panel to the frame
        add(taskUI);

        loadTasksFromFile();

        // Make the frame visible
        setVisible(true);
    }

    private void loadTasksFromFile() {
        taskManager.loadTasksFromFile("eatthefrog.txt");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EatTheFrogApp();
        });
    }
}

class TaskUI extends JPanel {
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton, editButton, deleteButton;
    private JCheckBox completedCheckBox;
    private TaskManager taskManager;
    private Task editingTask;
    private int editingTaskIndex;
    private Task selectedTaskForDeletion;

    public TaskUI(TaskManager taskManager) {
        this.taskManager = taskManager;

        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Create an input panel for task details
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        JLabel taskLabel = new JLabel("Task: ");
        inputPanel.add(taskLabel);
        titleField = new JTextField();
        inputPanel.add(titleField);
        titleField.setPreferredSize(new Dimension(200, 40));

        JLabel descriptionLabel = new JLabel("Description: ");
        inputPanel.add(descriptionLabel);
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        JLabel priorityLabel = new JLabel("Priority: ");
        inputPanel.add(priorityLabel);

        priorityComboBox = new JComboBox<>(new String[]{"1 (Highest)", "2", "3", "4", "5 (Lowest)"});
        inputPanel.add(priorityComboBox);

        // Create a button panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        completedCheckBox = new JCheckBox("Completed");

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completedCheckBox);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        completedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markCompleted();
            }
        });

        loadTasksFromFile();
    }

    private void addTask() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            // Create a new task and add it to data structures
            Task newTask = new Task(title, description, priority);

            taskManager.addTask(newTask);

            // Clear input fields
            clearFields();

            // Save tasks to file after adding a task
            taskManager.saveTasksToFile("eatthefrog.txt");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            if (editingTask == null) { // Enter edit mode
                editingTask = taskList.getSelectedValue();
                editingTaskIndex = selectedIndex;

                // Populate input fields with the selected task's details for editing
                titleField.setText(editingTask.getTitle());
                descriptionField.setText(editingTask.getDescription());
                // Find the index of the priority level in the combo box
                int priorityIndex = editingTask.getPriority() - 1; // Adjust index for 1-based priorities
                priorityComboBox.setSelectedIndex(priorityIndex);
                completedCheckBox.setSelected(editingTask.isCompleted());

                // Change the button label to "Save Task"
                editButton.setText("Save Task");
            } else { // Save changes
                try {
                    String newTitle = titleField.getText();
                    String newDescription = descriptionField.getText();
                    int newPriority = priorityComboBox.getSelectedIndex() + 1; // Adjust priority value

                    // Update the selected task
                    editingTask.setTitle(newTitle);
                    editingTask.setDescription(newDescription);
                    editingTask.setPriority(newPriority);

                    // Update the task in the manager
                    taskManager.updateTask(editingTask);

                    // Update the list model
                    taskListModel.setElementAt(editingTask, editingTaskIndex);

                    // Clear input fields
                    clearFields();
                    editButton.setText("Edit"); // Change the button label back to "Edit Task"

                    editingTask = null; // Reset the editing task

                    // Save tasks to file after editing a task
                    taskManager.saveTasksToFile("eatthefrog.txt");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to edit.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedTaskForDeletion = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Remove the task from data structures and list model
                taskManager.deleteTask(selectedTaskForDeletion);
                taskListModel.removeElement(selectedTaskForDeletion);

                // Clear input fields
                clearFields();

                // Save tasks to file after deleting a task
                taskManager.saveTasksToFile("eatthefrog.txt");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    private void markCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Have you completed this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                selectedTask.setCompleted(true);

                // Update the task in the manager
                taskManager.updateTask(selectedTask);

                if (selectedTask.isCompleted()) {
                    // Optionally move the completed task to a separate list
                    // For now, we'll simply remove it from the list
                    taskListModel.removeElement(selectedTask);

                    // Save tasks to file after completing a task
                    taskManager.saveTasksToFile("eatthefrog.txt");

                    // Add completed task to "frogseaten.txt"
                    taskManager.addCompletedTaskToFrogFile(selectedTask);
                }

                // Clear input fields
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    public void updateTaskList() {
        taskListModel.clear();
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task);
        }
    }
}

class Task {
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

    // Getter and setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter methods for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter methods for priority
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Getter and setter methods for completed
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
}

class TaskManager {
    private TreeSet<Task> tasks;
    private final String fileName = "eatthefrog.txt";

    public TaskManager() {
        tasks = new TreeSet<>();
    }

    public void loadTasksFromFile(String fileName) {
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

                        tasks.add(loadedTask);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTasksToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                String line = task.getTitle() + "\t" + task.getDescription() + "\t" + task.getPriority() + "\t" + task.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCompletedTaskToFrogFile(Task completedTask) {
        // Implement this method to add completed tasks to "frogseaten.txt"
        // You can use a similar approach as saving tasks to file
        // Write completed task details to "frogseaten.txt" in a format of your choice
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void updateTask(Task task) {
        // Tasks in a TreeSet are automatically updated since they are sorted based on priority
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public TreeSet<Task> getTasks() {
        return tasks;
    }
}
