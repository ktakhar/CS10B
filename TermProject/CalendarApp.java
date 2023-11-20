import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CalendarApp {
    private JFrame mainFrame;
    private JPanel calendarPanel;
    private Map<Integer, List<CalendarEvent>> eventsByDay = new HashMap<>();

    public CalendarApp() {
        mainFrame = new JFrame("Calendar App");

        // Set the main frame to full screen
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);

        mainFrame.setLayout(new BorderLayout());

        calendarPanel = new JPanel(new GridLayout(6, 7)); // 6 rows and 7 columns with no spacing
        createCalendar();

        mainFrame.add(calendarPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEventDialog();
            }
        });

        mainFrame.add(addButton, BorderLayout.SOUTH);
    }

    private void createCalendar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d");
        for (int i = 1; i <= 31; i++) {
            JPanel dayPanel = new JPanel(new BorderLayout());
            JButton dateButton = new JButton(Integer.toString(i));
            int day = i;
            dateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showAddEventDialogForDay(day);
                }
            });
            dayPanel.add(dateButton, BorderLayout.CENTER);
            calendarPanel.add(dayPanel);
        }
    }

    private void showEventsForDate(int day) {
        List<CalendarEvent> dayEvents = eventsByDay.get(day);
        if (dayEvents != null) {
            // Sort events by priority (1-5)
            dayEvents.sort(Comparator.comparingInt(CalendarEvent::getPriority));
            StringBuilder eventText = new StringBuilder("Events for " + day + ":\n");

            for (CalendarEvent event : dayEvents) {
                eventText.append(event.getEventName()).append(" (Priority: ").append(event.getPriority()).append(")\n");
            }
            JOptionPane.showMessageDialog(mainFrame, eventText.toString());
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No events for " + day);
        }
    }

    private void showAddEventDialogForDay(int day) {
        JFrame dialogFrame = new JFrame("Add Event");
        dialogFrame.setLayout(new GridLayout(4, 2));

        JTextField eventNameField = new JTextField();
        JTextField priorityField = new JTextField();
        JButton saveButton = new JButton("Save Event");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = eventNameField.getText();
                int priority = Integer.parseInt(priorityField.getText());
                CalendarEvent event = new CalendarEvent(day, eventName, priority);

                // Add the event to the corresponding day's list
                List<CalendarEvent> dayEvents = eventsByDay.getOrDefault(day, new ArrayList<>());
                dayEvents.add(event);

                // Limit to a maximum of 5 events per day
                if (dayEvents.size() > 5) {
                    JOptionPane.showMessageDialog(mainFrame, "Maximum of 5 events per day reached.");
                } else {
                    eventsByDay.put(day, dayEvents);

                    // Refresh the calendar to display the event
                    updateCalendar();
                    dialogFrame.dispose();
                }
            }
        });

        dialogFrame.add(new JLabel("Event Name:"));
        dialogFrame.add(eventNameField);
        dialogFrame.add(new JLabel("Priority (1-5):"));
        dialogFrame.add(priorityField);
        dialogFrame.add(saveButton);

        dialogFrame.pack();
        dialogFrame.setVisible(true);
    }

    private void showAddEventDialog() {
        JFrame dialogFrame = new JFrame("Add Event");
        dialogFrame.setLayout(new GridLayout(4, 2));

        JTextField eventNameField = new JTextField();
        JTextField priorityField = new JTextField();
        JButton saveButton = new JButton("Save Event");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = 0; // Default to day 0 for events not associated with a specific day
                String eventName = eventNameField.getText();
                int priority = Integer.parseInt(priorityField.getText());
                CalendarEvent event = new CalendarEvent(day, eventName, priority);

                // Add the event to the corresponding day's list
                List<CalendarEvent> dayEvents = eventsByDay.getOrDefault(day, new ArrayList<>());
                dayEvents.add(event);

                // Limit to a maximum of 5 events per day
                if (dayEvents.size() > 5) {
                    JOptionPane.showMessageDialog(mainFrame, "Maximum of 5 events per day reached.");
                } else {
                    eventsByDay.put(day, dayEvents);

                    // Refresh the calendar to display the event
                    updateCalendar();
                    dialogFrame.dispose();
                }
            }
        });

        dialogFrame.add(new JLabel("Event Name:"));
        dialogFrame.add(eventNameField);
        dialogFrame.add(new JLabel("Priority (1-5):"));
        dialogFrame.add(priorityField);
        dialogFrame.add(saveButton);

        dialogFrame.pack();
        dialogFrame.setVisible(true);
    }

private void updateCalendar() {
    for (Component component : calendarPanel.getComponents()) {
        if (component instanceof JPanel) {
            JPanel dayPanel = (JPanel) component;
            JButton dateButton = (JButton) dayPanel.getComponent(0); // Get the date button
            int day = Integer.parseInt(dateButton.getText().replaceAll("<html>|</html>", "").split("<br>")[0]);
            List<CalendarEvent> dayEvents = eventsByDay.get(day);

            if (dayEvents != null && !dayEvents.isEmpty()) {
                // Sort events by priority (1-5)
                dayEvents.sort(Comparator.comparingInt(CalendarEvent::getPriority));

                StringBuilder eventsText = new StringBuilder("<html>" + day + "<br>");
                for (CalendarEvent event : dayEvents) {
                    eventsText.append(event.getEventName()).append(" (").append(event.getPriority()).append(")<br>");
                }
                eventsText.append("</html>");
                dateButton.setText(eventsText.toString());
            } else {
                dateButton.setText("<html>" + day + "<br></html>");
            }
        }
    }
}



    public void showGUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarApp calendarApp = new CalendarApp();
            calendarApp.showGUI();
        });
    }
}

class CalendarEvent {
    private int day;
    private String eventName;
    private int priority;

    public CalendarEvent(int day, String eventName, int priority) {
        this.day = day;
        this.eventName = eventName;
        this.priority = priority;
    }

    public int getDay() {
        return day;
    }

    public String getEventName() {
        return eventName;
    }

    public int getPriority() {
        return priority;
    }
}
