import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import javax.swing.border.LineBorder;

public class Calculator {
    JFrame frame = new JFrame("Calculator");
    
    int frameWidth = 360;
    int frameHeight = 540;
    Color customRed = new Color(255, 0, 0);
    Color customGreen = new Color(0, 255, 0);
    Color customBlue = new Color(0, 0, 255);
    String[] buttonSymbols = {
        "C", "±", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] topSymbols={
        "C", "±", "%"
    };
    String[] rightSymbols={
        "÷", "×", "-", "+", "="
    };
    JPanel displaypanel = new JPanel();
    JPanel buttonpanel = new JPanel();
    JLabel displaylabel = new JLabel();

    String A="0";
    String operator=null;
    String B=null;
    
    Calculator(){
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        displaylabel.setBackground(customRed);
        displaylabel.setForeground(customGreen);
        displaylabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displaylabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displaylabel.setText("0");
        displaylabel.setOpaque(true);

        displaypanel.setLayout(new BorderLayout());
        displaypanel.add(displaylabel);
        frame.add(displaypanel, BorderLayout.NORTH);

        buttonpanel.setLayout(new GridLayout(5, 4));
        buttonpanel.setBackground(customRed);
        frame.add(buttonpanel,BorderLayout.CENTER);

        for(int i=0;i<buttonSymbols.length;i++){
            JButton button = new JButton();
            String symbol=buttonSymbols[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setBackground(customBlue);
            button.setForeground(customGreen);
            button.setText(symbol);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customGreen, 1));
            buttonpanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button=(JButton)e.getSource();
                    String value=button.getText();
                    String curValue=displaylabel.getText();
                    if(Arrays.asList(topSymbols).contains(value)){
                        if(value=="C"){
                            clearAll();
                            displaylabel.setText("0");
                        }
                        else if(value=="±"){
                            double tempValue= Double.parseDouble(curValue);
                            tempValue=-tempValue;
                            displaylabel.setText(removeZeroDecimal(tempValue));
                        }
                        else{
                            double tempValue= Double.parseDouble(curValue);
                            tempValue/=100;
                            displaylabel.setText(removeZeroDecimal(tempValue));
                        }
                    } 
                    else if(Arrays.asList(rightSymbols).contains(value)){
                        if("+-×÷".contains(value)){
                            if(operator==null){
                                A=displaylabel.getText();
                                displaylabel.setText("0");
                                B="0";
                            }
                            operator=value;
                        }
                        else if(value=="="){
                            if(A!=null){
                                B=displaylabel.getText();
                                double a = Double.parseDouble(A);
                                double b = Double.parseDouble(B);

                                double result = 0;

                                switch(operator){
                                    case "+": result = a + b; break;
                                    case "-": result = a - b; break;
                                    case "×": result = a * b; break;
                                    case "÷": result = a / b; break;
                                }

                                displaylabel.setText(removeZeroDecimal(result));

                                A = Double.toString(result);
                                operator = null;
                                B = null;
                            }
                        }
                    } 
                    else {
                        if(value=="."){
                            if(!(displaylabel.getText().contains(value))){
                                displaylabel.setText(displaylabel.getText()+value);
                            }
                        }
                        else if("0123456789".contains(value)){
                            if(displaylabel.getText().equals("0")){
                                displaylabel.setText(value);
                            }
                            else {
                                displaylabel.setText(displaylabel.getText()+value);
                            }
                        }   
                    } 
                }
            });
        }
        frame.setVisible(true);
    }
    void clearAll(){
        A="0";
        operator=null;
        B=null;
    }

    String removeZeroDecimal(double curValue){
        if(curValue % 1==0){
            return String.valueOf((int) curValue);
        }
        return String.valueOf(curValue);
    }
}
