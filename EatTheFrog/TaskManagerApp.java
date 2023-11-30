import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class TaskManagerApp<T extends Task> extends JFrame {
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<T> taskListModel;
    private JList<T> taskList;

    private Map<Integer, T> taskMap;
    private TreeSet<T> taskSet; // Use TreeSet for automatic sorting

    private JButton addButton, editButton, deleteButton;
    private JCheckBox completedCheckBox;

    // Fields to keep track of the currently edited task and selected task for deletion
    private T editingTask;
    private int editingTaskIndex;
    private T selectedTaskForDeletion;

    public TaskManagerApp() {
        setTitle("Task Manager");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.BLACK);
        Font h1 = new Font("Arial", Font.PLAIN, 24);
        Font h2 = new Font("Arial", Font.PLAIN, 18);

        taskMap = new HashMap<>();
        taskSet = new TreeSet<>();

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        taskList.setFont(h2);
        taskList.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

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
                editOrSaveTask(); // Change the button label dynamically
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
                markAsCompleted();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)); // Adjusted button panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton); // Added delete button
        buttonPanel.add(completedCheckBox);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addTask() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            T newTask = createTask(title, description, priority);

            if (!taskSet.contains(newTask)) {
                taskMap.put(newTask.hashCode(), newTask);
                taskSet.add(newTask);
                taskListModel.addElement(newTask);

                // Sort the taskListModel based on task priority
                sortTaskListModel();

                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Task already exists.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
        }
    }

    private void editOrSaveTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            if (editingTask == null) { // Enter edit mode
                editingTask = taskList.getSelectedValue();
                editingTaskIndex = selectedIndex;

                // Populate input fields with the selected task's details for editing
                Task typedTask = (Task) editingTask;
                titleField.setText(typedTask.getTitle());
                descriptionField.setText(typedTask.getDescription());
                priorityComboBox.setSelectedItem(typedTask.getPriority() + " (Priority)");
                completedCheckBox.setSelected(typedTask.isCompleted());

                // Change the button label to "Save Task"
                editButton.setText("Save Task");
            } else { // Save changes
                try {
                    String newTitle = titleField.getText();
                    String newDescription = descriptionField.getText();
                    String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
                    int newPriority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

                    // Update the selected task
                    updateTaskDetails(editingTask, newTitle, newDescription, newPriority);

                    // Update the task in the map and set
                    taskMap.put(editingTask.hashCode(), editingTask);
                    taskSet.remove(editingTask);
                    taskSet.add(editingTask);

                    // Update the list model
                    taskListModel.setElementAt(editingTask, editingTaskIndex);

                    // Sort the taskListModel based on task priority
                    sortTaskListModel();

                    clearFields();
                    editButton.setText("Edit Task"); // Change the button label back to "Edit Task"

                    editingTask = null; // Reset the editing task
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
                taskMap.remove(selectedTaskForDeletion.hashCode());
                taskSet.remove(selectedTaskForDeletion);
                taskListModel.removeElement(selectedTaskForDeletion);
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    private void markAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            T selectedTask = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Have you completed this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                setTaskCompleted(selectedTask, true);

                // Update the task in the map and set
                taskMap.put(selectedTask.hashCode(), selectedTask);
                taskSet.remove(selectedTask);

                if (isTaskCompleted(selectedTask)) {
                    // Optionally move the completed task to a separate list
                    // For now, we'll simply remove it from the list
                    taskListModel.removeElement(selectedTask);
                }

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

    protected T createTask(String title, String description, int priority) {
        return (T) new Task(title, description, priority);
    }

    protected void updateTaskDetails(T task, String title, String description, int priority) {
        Task typedTask = (Task) task;
        typedTask.setTitle(title);
        typedTask.setDescription(description);
        typedTask.setPriority(priority);
    }

    protected void setTaskCompleted(T task, boolean completed) {
        Task typedTask = (Task) task;
        typedTask.setCompleted(completed);
    }

    protected boolean isTaskCompleted(T task) {
        Task typedTask = (Task) task;
        return typedTask.isCompleted();
    }

    // Sort the taskListModel based on task priority
    private void sortTaskListModel() {
        ArrayList<T> tasks = new ArrayList<>();
        for (int i = 0; i < taskListModel.getSize(); i++) {
            tasks.add(taskListModel.getElementAt(i));
        }

        Collections.sort(tasks); // Sort the tasks based on their compareTo method

        taskListModel.clear();
        for (T task : tasks) {
            taskListModel.addElement(task);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TaskManagerApp<Task> app = new TaskManagerApp<>();
                app.setVisible(true);
            }
        });
    }
}

class Task implements Comparable<Task> {
    private static int nextId = 1;
    private int id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;

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
        // Include description in task list information after priority
        return title + " [Priority: " + priority + "] - " + description;
    }
}
