import java.util.Objects;

// Define a class named "Task" that implements the "Comparable" interface
class Task implements Comparable<Task> {
    // Instance variables to store task information
    private String title;
    private String description;
    private int priority;
    private boolean completed;

    // Constructor to initialize a Task object with title, description, and priority
    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false; // Initialize completed status to false
    }

    // Getter method to retrieve the task's title
    public String getTitle() {
        return title;
    }

    // Setter method to update the task's title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method to retrieve the task's description
    public String getDescription() {
        return description;
    }

    // Setter method to update the task's description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter method to retrieve the task's priority
    public int getPriority() {
        return priority;
    }

    // Override the "compareTo" method to compare tasks based on their priorities
    @Override
    public int compareTo(Task otherTask) {
        return Integer.compare(this.priority, otherTask.priority);
    }

    // Setter method to update the task's priority
    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Getter method to check if the task is completed
    public boolean isCompleted() {
        return completed;
    }

    // Setter method to update the completed status of the task
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Override the "toString" method to provide a string representation of the task (used in the GUI)
    @Override
    public String toString() {
        return title + " " + " " + description;
    }

    // Override the "hashCode" method to generate a unique hash code for the task
    @Override
    public int hashCode() {
        return Objects.hash(title, description, priority);
    }

    // Override the "equals" method to compare two tasks for equality based on their attributes
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references point to the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Check class compatibility
        Task task = (Task) obj; // Cast the object to a Task
        // Check if title, description, and priority are equal
        return priority == task.priority &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description);
    }
}
