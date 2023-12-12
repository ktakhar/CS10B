import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

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
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Font h1 = new Font("Arial", Font.BOLD, 36);
        Font h2 = new Font("Arial", Font.BOLD, 24);
        Font h3 = new Font("Arial", Font.Plain, 24);

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

        inputPanel.add(new JLabel("Task:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Priority:"));
        priorityComboBox = new JComboBox<>(new String[]{"1 (Highest)", "2", "3", "4", "5 (Lowest)"});
        inputPanel.add(priorityComboBox);

        // Create buttons for adding, editing, deleting, and marking tasks as completed
        addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        editButton = new JButton("Edit Task");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask(); // Change the button label dynamically
            }
        });

        deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        completedCheckBox = new JCheckBox("Completed");
        completedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completed();
            }
        });

        // Create a button panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)); // Adjusted button panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton); // Added delete button
        buttonPanel.add(completedCheckBox);

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
                    editButton.setText("Edit Task"); // Change the button label back to "Edit Task"

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

                    // Add the completed task to frogseaten.txt
                    addToFrogEatenFile(selectedTask);
                }

                // Clear input fields
                clearFields();

                // Save tasks to file after completing a task
                saveTasksToFile();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    // Method to add a completed task to frogseaten.txt
    private void addToFrogEatenFile(Task completedTask) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(frogseatenFileName, true))) {
            writer.write(completedTask.getTitle() + "\t" + completedTask.getDescription() + "\t" + completedTask.getPriority());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to clear input fields
    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    // Method to sort the taskListModel based on task priority
    private void sortTaskListModel() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskListModel.getSize(); i++) {
            tasks.add(taskListModel.getElementAt(i));
        }

        Collections.sort(tasks); // Sort the tasks based on their compareTo method

        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    // Method to load tasks from file and populate the display fields
    private void loadTasksFromFile() {
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

                    // Populate the input fields with the loaded task's details
                    if (taskList.getSelectedValue() == loadedTask) {
                        titleField.setText(title);
                        descriptionField.setText(description);
                        priorityComboBox.setSelectedItem(priority + " (Priority)");
                        completedCheckBox.setSelected(completed);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist (it will be created when tasks are saved)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save tasks to file
    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < taskListModel.getSize(); i++) {
                Task task = taskListModel.getElementAt(i);
                writer.write(task.getTitle() + "\t" + task.getDescription() + "\t" + task.getPriority() + "\t" + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EatTheFrog app = new EatTheFrog();
                app.setVisible(true);
            }
        });
    }
}

// Template class for tasks
class Task implements Comparable<Task> {
    // Fields for task properties
    private static int nextId = 1;
    private int id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;

    // Constructor to create a new task
    public Task(String title, String description, int priority) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    // Getters and setters for fields

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
    public int compareTo(Task other) {
        // Compare tasks based on priority (1 as highest)
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        // List task, description, and priority level
        return title + "\t" + description + "\t\t\t\t" + priority;
    }
}
