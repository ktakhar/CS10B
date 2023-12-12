import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class EatTheFrogApp extends JFrame {
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

    public EatTheFrogApp() {
        setTitle("Eat The Frog!");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize TaskManager to manage tasks
        taskManager = new TaskManager();

        // Create a list model for tasks and a JList to display them
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
                completed();
            }
        });

        loadTasksFromFile();
    }
}

class TaskLogic {
      private void addTask() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            boolean duplicatePriorityExists = taskManager.doesPriorityExist(priority);

            if (!duplicatePriorityExists) {
                Task newTask = new Task(title, description, priority);

                taskManager.addTask(newTask);
                taskListModel.addElement(newTask);

                clearFields();
                saveTasksToFile();
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
            if (editingTask == null) {
                editingTask = taskList.getSelectedValue();
                editingTaskIndex = selectedIndex;

                titleField.setText(editingTask.getTitle());
                descriptionField.setText(editingTask.getDescription());
                int priorityIndex = editingTask.getPriority() - 1;
                priorityComboBox.setSelectedIndex(priorityIndex);
                completedCheckBox.setSelected(editingTask.isCompleted());

                editButton.setText("Save Task");
            } else {
                try {
                    String newTitle = titleField.getText();
                    String newDescription = descriptionField.getText();
                    int newPriority = priorityComboBox.getSelectedIndex() + 1;

                    editingTask.setTitle(newTitle);
                    editingTask.setDescription(newDescription);
                    editingTask.setPriority(newPriority);

                    taskManager.updateTask(editingTask);
                    taskListModel.setElementAt(editingTask, editingTaskIndex);

                    clearFields();
                    editButton.setText("Edit");
                    editingTask = null;

                    saveTasksToFile();
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
                taskManager.deleteTask(selectedTaskForDeletion);
                taskListModel.removeElement(selectedTaskForDeletion);

                clearFields();
                saveTasksToFile();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    private void completed() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = taskList.getSelectedValue();

            int option = JOptionPane.showConfirmDialog(this, "Have you completed this task?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                selectedTask.setCompleted(true);

                taskManager.updateTask(selectedTask);

                if (selectedTask.isCompleted()) {
                    taskListModel.removeElement(selectedTask);
                    saveTasksToFile();
                    addCompletedTaskToFrogFile(selectedTask);
                }

                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark as completed.");
        }
    }

    private void loadTasksFromFile() {
        List<Task> loadedTasks = taskManager.loadTasksFromFile();
        for (Task loadedTask : loadedTasks) {
            taskListModel.addElement(loadedTask);
        }
    }

    private void saveTasksToFile() {
        taskManager.saveTasksToFile();
    }

    private void addCompletedTaskToFrogFile(Task completedTask) {
        taskManager.addCompletedTaskToFrogFile(completedTask);
    }

    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EatTheFrogApp().setVisible(true);
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
    public int compareTo(Task otherTask) {
        return Integer.compare(this.priority, otherTask.priority);
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
    private Map<Integer, Task> taskMap;
    private TreeSet<Task> taskSet;
    private final String fileName = "eatthefrog.txt";
    private final String frogseatenFileName = "frogseaten.txt";

    public TaskManager() {
        taskMap = new HashMap<>();
        taskSet = new TreeSet<>();
    }

    public boolean doesPriorityExist(int priority) {
        return taskSet.stream().anyMatch(task -> task.getPriority() == priority);
    }

    public void addTask(Task task) {
        taskMap.put(task.hashCode(), task);
        taskSet.add(task);
    }

    public void updateTask(Task task) {
        taskMap.put(task.hashCode(), task);
        taskSet.remove(task);
        taskSet.add(task);
    }

    public void deleteTask(Task task) {
        taskMap.remove(task.hashCode());
        taskSet.remove(task);
    }

    public List<Task> loadTasksFromFile() {
        List<Task> loadedTasks = new ArrayList<>();
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

                        loadedTasks.add(loadedTask);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedTasks;
    }

    public void saveTasksToFile() {
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

    public void addCompletedTaskToFrogFile(Task completedTask) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(frogseatenFileName, true))) {
            String line = "Task: " + completedTask.getTitle() + ", Priority: " + completedTask.getPriority() + ", Completed: " + completedTask.isCompleted();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
