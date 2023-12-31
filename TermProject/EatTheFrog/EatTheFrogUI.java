import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// Define a class named EatTheFrogUI that extends JFrame, creating a GUI application.
public class EatTheFrogUI extends JFrame {
    // Declare instance variables for various GUI components and data structures.
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton, editButton, deleteButton;
    private JCheckBox completedCheckBox;

    private Map<Integer, Task> taskMap; // Stores tasks using a unique integer key
    private TreeSet<Task> taskSet; // Stores tasks in a sorted order

    private Task editingTask; // Represents a task being edited
    private int editingTaskIndex; // Index of the task being edited in the task list
    private Task selectedTaskForDeletion; // Represents a task selected for deletion

    private final String fileName = "eatthefrog.txt"; // File name for saving tasks
    private final String frogseatenFileName = "frogseaten.txt"; // File name for completed tasks

    // Constructor initializing the UI
    public EatTheFrogUI() {
        // Set the title and dimensions of the JFrame
        setTitle("Eat The Frog!");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Define fonts for various text components
        Font h1 = new Font("Arial", Font.BOLD, 36);
        Font h2 = new Font("Arial", Font.BOLD, 24);
        Font h3 = new Font("Arial", Font.PLAIN, 24);
        
        // Initialize data structures and GUI components
        taskMap = new HashMap<>();
        taskSet = new TreeSet<>();
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        taskList.setFont(h2);
        taskList.setForeground(Color.BLACK);

        // Create a scrollable panel for the task list
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Create input panel for task details
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.setBackground(new Color(8, 164, 167));

        // Create labels and input fields for task title description and priority 
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

        // Create button panel for Add Edit Delete buttons and completed checkbox
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        completedCheckBox = new JCheckBox("Completed");

        // Set button colors and fonts
        addButton.setForeground(new Color(8, 164, 167));
        editButton.setForeground(new Color(8, 164, 167));
        deleteButton.setForeground(new Color(8, 164, 167));
        completedCheckBox.setForeground(new Color(8, 164, 167));
        buttonPanel.setBackground(new Color(8, 164, 167));
        completedCheckBox.setForeground(Color.WHITE);

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        completedCheckBox.setFont(buttonFont);

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completedCheckBox);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() { // addTask() 
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        editButton.addActionListener(new ActionListener() { // editTask()
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() { // deleteTask()
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        completedCheckBox.addActionListener(new ActionListener() { // completed()
            @Override
            public void actionPerformed(ActionEvent e) {
                completed();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        loadTasksFromFile();
    }
    
    // Method to add new task
    private void addTask() {
        try {
            // Get task details from input fields
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            Task newTask = new Task(title, description, priority);

            // Check if the new priority already exists
            boolean priorityExists = taskSet.stream()
                .anyMatch(task -> task.getPriority() == priority);

            if (priorityExists) {
                int option = JOptionPane.showConfirmDialog(this,
                    "A task with the same priority already exists. Override priority?",
                    "Priority Exists", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.NO_OPTION) {
                // User chose not to override the task
                    return;
                } else {
                    // User chose to override the task
                    // Shift down tasks with a higher or equal priority
                    for (Task task : taskSet) {
                        if (task.getPriority() >= priority) {
                        task.setPriority(task.getPriority() + 1);
                        }
                    }
                }
            }

            taskMap.put(newTask.hashCode(), newTask);
            taskSet.add(newTask);
            taskListModel.addElement(newTask);

            // Sort the task list and clear input fields
            sortTaskListModel();
            clearFields();

            // Save tasks to the file
            saveTasksToFile();
        } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
        }
}
    // Method to edit task
    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            if (editingTask == null) {
                // Select a task for editing
                editingTask = taskList.getSelectedValue();
                editingTaskIndex = selectedIndex;

                // Populate input fields with task details
                titleField.setText(editingTask.getTitle());
                descriptionField.setText(editingTask.getDescription());

                // Set the selected item in priorityComboBox to match the task's priority
                for (int i = 0; i < priorityComboBox.getItemCount(); i++) {
                    String item = priorityComboBox.getItemAt(i);
                    if (item.startsWith(editingTask.getPriority() + " ")) {
                        priorityComboBox.setSelectedIndex(i);
                        break;
                    }
                }

                completedCheckBox.setSelected(editingTask.isCompleted());

                editButton.setText("Save Task");
            } else {
                try {
                    // Update task details based on input fields
                    String newTitle = titleField.getText();
                    String newDescription = descriptionField.getText();
                    int newPriority = Integer.parseInt(priorityComboBox.getSelectedItem().toString().split(" ")[0]);

                    // Check if the priority is changing
                    if (newPriority != editingTask.getPriority()) {
                        // Check if the new priority already exists
                        boolean priorityExists = taskSet.stream()
                            .anyMatch(task -> task.getPriority() == newPriority);

                        if (priorityExists) {
                            int option = JOptionPane.showConfirmDialog(this,
                                "A task with the same priority already exists. Add anyway?",
                                "Priority Exists", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.NO_OPTION) {
                                // User chose not to add the task
                                return;
                            }
                        }

                        // Remove the task from the old priority
                        taskSet.remove(editingTask);
                        // Update the priority
                        editingTask.setPriority(newPriority);
                        // Add it back with the new priority
                        taskSet.add(editingTask);

                        // Adjust the priorities of other tasks
                        for (Task task : taskSet) {
                            if (task.getPriority() >= newPriority && task != editingTask) {
                                task.setPriority(task.getPriority() + 1);
                            }
                        }
                    }

                    editingTask.setTitle(newTitle);
                    editingTask.setDescription(newDescription);
                    editingTask.setCompleted(completedCheckBox.isSelected());

                    // Update task in the task list model
                    taskListModel.setElementAt(editingTask, editingTaskIndex);

                    // Sort the task list, clear input fields, and switch back to Edit mode
                    sortTaskListModel();
                    clearFields();
                    editButton.setText("Edit");

                    editingTask = null;

                    // Save tasks to file
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
            
            // Display a confirmation dialog for task deletion.
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Remove the task from data structures and task list model
                taskMap.remove(selectedTaskForDeletion.hashCode());
                taskSet.remove(selectedTaskForDeletion);
                taskListModel.removeElement(selectedTaskForDeletion);

                // Clear input fields and save tasks to file
                clearFields();
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

            // Display a confirmation dialog for completing the task
            int option = JOptionPane.showConfirmDialog(this, "Have you completed this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                selectedTask.setCompleted(true);

                // Update the task in data structures
                taskMap.put(selectedTask.hashCode(), selectedTask);
                taskSet.remove(selectedTask);

                if (selectedTask.isCompleted()) {
                    // If the task is completed remove it from the task list model
                    taskListModel.removeElement(selectedTask);
                    // Save tasks to file and add the copmleted tasks to the completed text file
                    saveTasksToFile();

                    addCompletedTaskToFrogFile(selectedTask);
                }
                // Clear input fields
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    // Method to load tasks from a file when the UI is created
    private void loadTasksFromFile() {
        File file = new File(fileName);
        if (file.exists()) {
            // Use BufferedReader to read file
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    if (parts.length == 4) {
                        String title = parts[0];
                        String description = parts[1];
                        int priority = Integer.parseInt(parts[2]);
                        boolean completed = Boolean.parseBoolean(parts[3]);

                        // Load tasks
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

    // Method to save tasks to a file
    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : taskSet) {
                // Write task details to the file
                String line = task.getTitle() + "\t" + task.getDescription() + "\t" + task.getPriority() + "\t" + task.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a completed task to the frogseaten file
    private void addCompletedTaskToFrogFile(Task completedTask) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(frogseatenFileName, true))) {
            // Write completed task details to file
            String line = "Task: " + completedTask.getTitle() + ", Priority: " + completedTask.getPriority() + ", Completed: " + completedTask.isCompleted();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to sort and update the task list model
    private void sortTaskListModel() {
        ArrayList<Task> taskList = new ArrayList<>(taskSet);
        taskListModel.clear();

        for (Task task : taskList) {
            taskListModel.addElement(new Task(task.getTitle(), task.getDescription(), task.getPriority()));
        }
    }

    // Method to clear input fields
    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    // Method to create and show the GUI
    public void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            new EatTheFrogUI().setVisible(true);
        });
    }
}
