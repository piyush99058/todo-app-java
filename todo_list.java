import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class todo_list {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoListFrame frame = new TodoListFrame();
            frame.setVisible(true);
        });
    }
}

class TodoListFrame extends JFrame {
    private final DefaultListModel<String> todoModel;
    private final JList<String> todoList;
    private final JTextField inputField;

    TodoListFrame() {
        super("Todo List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        todoModel = new DefaultListModel<>();
        todoList = new JList<>(todoModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(todoList);

        inputField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(new AddTaskAction());
        removeButton.addActionListener(e -> removeSelectedTask());

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new BorderLayout(5, 5));
        buttonPanel.add(removeButton, BorderLayout.WEST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void addTask() {
        String task = inputField.getText().trim();
        if (!task.isEmpty()) {
            todoModel.addElement(task);
            inputField.setText("");
        }
    }

    private void removeSelectedTask() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex >= 0) {
            todoModel.remove(selectedIndex);
        }
    }

    private class AddTaskAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addTask();
        }
    }
}
