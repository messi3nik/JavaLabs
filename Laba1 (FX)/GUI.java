import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class GUI extends JFrame {
    private static JFrame frame;
    private static JLabel label1, label2;
    private JLabel labelWarning, textArea;
    private JTextField textField;
    private JButton button;
    private JRadioButton from2, from10;


    private void initialization(){
        frame = new JFrame("KPP_APP");
        label2 = new JLabel("Your result");
        label1 =new JLabel("Enter the number");
        textArea=new JLabel();
        textField=new JTextField();
        labelWarning = new JLabel("Sorry, too big number");
        button = new JButton("Convert!");
        from2 = new JRadioButton("From Binary to Decimal");
        from10 = new JRadioButton("From Decimal to Binary");

        frame.add(label1);
        frame.add(label2);
        frame.add(textField);
        frame.add(textArea);
        frame.add(button);
        frame.add(labelWarning);
        frame.add(from10);
        frame.add(from2);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void settings (){
        label1.setBounds(50, 50, 100, 50);
        label2.setBounds(290, 50, 100, 50);
        labelWarning.setBounds(50, 150, 300, 50);
        this.setLayout(new BorderLayout());
        labelWarning.setForeground(Color.red);
        textField.setBounds(50, 90, 200, 20);
        textArea.setBounds(290, 90, 270, 20);
        button.setBounds(50, 200, 100, 40);

        from2.setBounds(290,180,200,50);
        from10.setBounds(290,220,200,50);
        from10.setSelected(true);
        from2.setActionCommand("From Binary to Decimal");
        from10.setActionCommand("From Decimal to Binary");
        ButtonGroup group = new ButtonGroup();
        group.add(from2);
        group.add(from10);
        labelWarning.setVisible(false);
        textField.getDocument().addDocumentListener(new MyListener());
    }

    public GUI()
    {
        initialization();
        settings();
        PlainDocument doc = (PlainDocument) textField.getDocument();
        if(from2.isSelected())
            doc.setDocumentFilter(new BinaryFilter());
        if(from10.isSelected())
            doc.setDocumentFilter(new DigitFilter());
    }

    private void from10To2()
    {
        int integer=Integer.parseInt(textField.getText());
        textArea.setText(Integer.toString(integer, 2));
    }
    private void from2To10()
    {
        int integer = Integer.parseInt(textField.getText(), 2);
        textArea.setText(Integer.toString(integer));
    }

    public void actLis()
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField.getText().equals(""))
                        textField.setText("0");
                    labelWarning.setVisible(false);
                    if(from2.isSelected()) {
                        from2To10();
                    }
                    else from10To2();
                }
                catch (NumberFormatException ex) {
                    labelWarning.setVisible(true);
                }
            }
        });
    }

    class DigitFilter extends DocumentFilter {
        private static final String DIGITS = "\\d+";

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches(DIGITS)) {
                super.insertString(fb, offset, string, attr);
            }
        }
        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
            if (string.matches(DIGITS)) {
                super.replace(fb, offset, length, string, attrs);
            }
        }
    }

    class BinaryFilter extends DocumentFilter {
        private static final String BINARY = "^[0-1]+";

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

            if (string.matches(BINARY)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
            if (string.matches(BINARY)) {
                super.replace(fb, offset, length, string, attrs);
            }
        }
    }

    private class MyListener implements DocumentListener {


        private void warm() {
            PlainDocument doc = (PlainDocument) textField.getDocument();
            if(from2.isSelected()) {
                doc.setDocumentFilter(new BinaryFilter());
            }
            if(from10.isSelected()) {
                doc.setDocumentFilter(new DigitFilter());
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            warm();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            warm();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            warm();
        }
    }
}

