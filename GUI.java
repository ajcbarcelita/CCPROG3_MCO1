import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI implements ActionListener {

    private int count = 0;
    private JPanel panel;
    private JFrame frame;
    private JLabel label;

    public GUI() {
        frame = new JFrame();

        JButton button = new JButton("Click Me");
        button.addActionListener(this);

        label = new JLabel("Number of Clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Hotel Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500,500);
        frame.setVisible(true);


    }

    public static void main (String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++; 
        label.setText("Number of clicks " + count );
    }
}
