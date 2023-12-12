import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// The main class for the EatTheFrog application
public class EatTheFrog extends JFrame {
    // User interface components
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton, editButton, deleteButton;
    private JCheckBox completedCheckBox;

    // Data structures to store tasks
    private Map<Integer, Task> taskMap;
    private TreeSet<Task> taskSet; // Use TreeSet for automatic sorting

    // Fields to keep track of the currently edited task and selected task for deletion
    private Task editingTask;
    private int editingTaskIndex;
    private Task selectedTaskForDeletion;

    // File names for task storage
    private final String fileName = "eatthefrog.txt";
    private final String frogseatenFileName = "frogseaten.txt";

    // Constructor for the EatTheFrog application
    public EatTheFrog() {
        // Set up the main window
        setTitle("Eat The Frog!");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Font h1 = new Font("Arial", Font.BOLD, 36);
        Font h2 = new Font("Arial", Font.BOLD, 24);
        Font h3 = new Font("Arial", Font.PLAIN, 24);

        // Initialize data structures
        taskMap = new HashMap<>();
        taskSet = new TreeSet<>();

        // Create a list model for tasks and a JList to display them
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        taskList.setFont(h2);
        taskList.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Create an input panel for task details
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.setBackground(new Color(8, 164, 167));

        JLabel taskLabel = new JLabel("Task: ");
        inputPanel.add(taskLabel);
        taskLabel.setFont(h3);
        titleField = new JTextField();
        inputPanel.add(titleField);
        titleField.setPreferredSize(new Dimension(200, 40));

        JLabel descriptionLabel = new JLabel("Description: ");
        inputPanel.add(descriptionLabel);
        descriptionLabel.setFont(h3);
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        JLabel priorityLabel = new JLabel("Priority: ");
        inputPanel.add(priorityLabel);
        priorityLabel.setFont(h3);

        priorityComboBox = new JComboBox<>(new String[]{"1 (Highest)", "2", "3", "4", "5 (Lowest)"});
        inputPanel.add(priorityComboBox);

        // Create a button panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        completedCheckBox = new JCheckBox("Completed");

        // Set the text color for each button and the button panel
        addButton.setForeground(new Color(8, 164, 167));
        editButton.setForeground(new Color(8, 164, 167));
        deleteButton.setForeground(new Color(8, 164, 167));
        completedCheckBox.setForeground(new Color(8, 164, 167));
        buttonPanel.setBackground(new Color(8, 164, 167));
        completedCheckBox.setForeground(Color.WHITE);

        // Set the font for each button
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        completedCheckBox.setFont(buttonFont);

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completedCheckBox);

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
                completed();
            }
        });

        // Add input and button panels to the main window
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load tasks from file on application startup
        loadTasksFromFile();
    }

    // Method to add a new task
    private void addTask() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            // Check if a task with the same priority already exists
            boolean duplicatePriorityExists = taskSet.stream()
                    .anyMatch(task -> task.getPriority() == priority);

            if (!duplicatePriorityExists) {
                // Create a new task and add it to data structures
                Task newTask = new Task(title, description, priority);

                taskMap.put(newTask.hashCode(), newTask);
                taskSet.add(newTask);
                taskListModel.addElement(newTask);

                // Sort the taskListModel based on task priority
                sortTaskListModel();

                // Clear input fields
                clearFields();

                // Save tasks to file after adding a task
                saveTasksToFile();
            } else {
                JOptionPane.showMessageDialog(this, "Priority level already exists.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
        }
    }

    // Method to edit an existing task
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

                    // Update the task in the map and set
                    taskMap.put(editingTask.hashCode(), editingTask);
                    taskSet.remove(editingTask);
                    taskSet.add(editingTask);

                    // Update the list model
                    taskListModel.setElementAt(editingTask, editingTaskIndex);

                    // Sort the taskListModel based on task priority
                    sortTaskListModel();

                    // Clear input fields
                    clearFields();
                    editButton.setText("Edit"); // Change the button label back to "Edit Task"

                    editingTask = null; // Reset the editing task

                    // Save tasks to file after editing a task
                    saveTasksToFile();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to edit.");
        }
    }

    // Method to delete a task
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedTaskForDeletion = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Remove the task from data structures and list model
                taskMap.remove(selectedTaskForDeletion.hashCode());
                taskSet.remove(selectedTaskForDeletion);
                taskListModel.removeElement(selectedTaskForDeletion);

                // Clear input fields
                clearFields();

                // Save tasks to file after deleting a task
                saveTasksToFile();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    // Method to mark a task as completed
    private void completed() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Have you completed this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                selectedTask.setCompleted(true);

                // Update the task in the map and set
                taskMap.put(selectedTask.hashCode(), selectedTask);
                taskSet.remove(selectedTask);

                if (selectedTask.isCompleted()) {
                    // Optionally move the completed task to a separate list
                    // For now, we'll simply remove it from the list
                    taskListModel.removeElement(selectedTask);

                    // Save tasks to file after completing a task
                    saveTasksToFile();

                    // Add completed task to "frogseaten.txt"
                    addCompletedTaskToFrogFile(selectedTask);
                }

                // Clear input fields
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    // Method to load tasks from the file and populate the display fields
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
                        taskListModel.addElement(loadedTask);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to save tasks to the file
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

    // Method to add a completed task to "frogseaten.txt"
    private void addCompletedTaskToFrogFile(Task completedTask) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(frogseatenFileName, true))) {
            String line = "Task: " + completedTask.getTitle() + ", Priority: " + completedTask.getPriority() + ", Completed: " + completedTask.isCompleted();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to sort the task list model based on task priority
    private void sortTaskListModel() {
        ArrayList<Task> taskList = new ArrayList<>(taskSet);
        taskListModel.clear();

        for (Task task : taskList) {
            taskListModel.addElement(task);
        }
    }

    // Method to clear input fields
    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EatTheFrog().setVisible(true);
            }
        });
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
        this.completed = false; // By default, a task is not completed
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

     @Override
    public int compareTo(Task otherTask) {
        // Compare tasks based on their priority
        return Integer.compare(this.priority, otherTask.priority);
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

