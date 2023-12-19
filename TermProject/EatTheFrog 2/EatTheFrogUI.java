import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EatTheFrogUI extends JFrame {
    private JTextField titleField, descriptionField;
    private JComboBox<String> priorityComboBox;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton, editButton, deleteButton;
    private JCheckBox completedCheckBox;

    private Map<Integer, Task> taskMap;
    private TreeSet<Task> taskSet;

    private Task editingTask;
    private int editingTaskIndex;
    private Task selectedTaskForDeletion;

    private final String fileName = "eatthefrog.txt";
    private final String frogseatenFileName = "frogseaten.txt";

    public EatTheFrogUI() {
        setTitle("Eat The Frog!");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Font h1 = new Font("Arial", Font.BOLD, 36);
        Font h2 = new Font("Arial", Font.BOLD, 24);
        Font h3 = new Font("Arial", Font.PLAIN, 24);

        taskMap = new HashMap<>();
        taskSet = new TreeSet<>();
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        taskList.setFont(h2);
        taskList.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        completedCheckBox = new JCheckBox("Completed");

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

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completedCheckBox);

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

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        loadTasksFromFile();
    }

    private void addTask() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String selectedPriorityStr = (String) priorityComboBox.getSelectedItem();
            int priority = Integer.parseInt(selectedPriorityStr.split(" ")[0]);

            boolean duplicatePriorityExists = taskSet.stream()
                    .anyMatch(task -> task.getPriority() == priority);

            if (!duplicatePriorityExists) {
                Task newTask = new Task(title, description, priority);

                taskMap.put(newTask.hashCode(), newTask);
                taskSet.add(newTask);
                taskListModel.addElement(newTask);

                sortTaskListModel();

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

                    taskMap.put(editingTask.hashCode(), editingTask);
                    taskSet.remove(editingTask);
                    taskSet.add(editingTask);

                    taskListModel.setElementAt(editingTask, editingTaskIndex);

                    sortTaskListModel();

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
                taskMap.remove(selectedTaskForDeletion.hashCode());
                taskSet.remove(selectedTaskForDeletion);
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

                taskMap.put(selectedTask.hashCode(), selectedTask);
                taskSet.remove(selectedTask);

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

    private void sortTaskListModel() {
        ArrayList<Task> taskList = new ArrayList<>(taskSet);
        taskListModel.clear();

        for (Task task : taskList) {
            taskListModel.addElement(task);
        }
    }

    private void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
        priorityComboBox.setSelectedIndex(0);
        completedCheckBox.setSelected(false);
    }

    public void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            new EatTheFrogUI().setVisible(true);
        });
    }
}
