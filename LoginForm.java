import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.AWTEvent;
import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
//implements ActionListener

 /**
 * @authors Sepanj Group
 * @version (01/03/2019)FSepanj
 * */

public class LoginForm extends JFrame {
 JLabel l1, l2, l3;
 JTextField tf1;
 JButton btn1, btn2;
 JPasswordField p1;
 private JPanel pricePanel;
 private JFrame frame;
 
 LoginForm() {
  frame = new JFrame("Login Form");
  try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Jupiter.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
  // makeFrame(frame);
  
  l1 = new JLabel("Login Form");
  l1.setForeground(Color.BLACK);
  l1.setFont(new Font("Serif", Font.BOLD, 25));
  
  l2 = new JLabel("Username");
  l3 = new JLabel("Password");
  tf1 = new JTextField();
  p1 = new JPasswordField();
  btn1 = new JButton("Login");
  btn2 = new JButton("Register");
 
  l1.setBounds(100, 30, 400, 30);
  l2.setBounds(80, 70, 200, 30);
  l3.setBounds(80, 110, 200, 30);
  tf1.setBounds(300, 70, 200, 30);
  p1.setBounds(300, 110, 200, 30);
  btn1.setBounds(100, 160, 100, 30);
  btn2.setBounds(200, 160, 100, 30);
  btn1.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
            try {checkUserPass(tf1,p1);  }
            catch(FileNotFoundException ex){} 
    }  
    });

  btn2.addActionListener(e -> actionRegister());
  
  frame.add(l1);
  frame.add(l2);
  frame.add(tf1);
  frame.add(l3);
  frame.add(p1);
  frame.add(btn1);
  frame.add(btn2);
  //frame.add(image);
  
  Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(d);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); 
 
  frame.setSize(800, 550);
  frame.setLayout(null);
  frame.setVisible(true);
  
 }
     
 public void actionRegister(){
         btn2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    frame.setVisible(false);
                    new RegisterPanel();
                }
            });
 }

 public void checkUserPass(JTextField tf1,JPasswordField p1 ) throws FileNotFoundException
 {
   String uname = tf1.getText();
   String pass = p1.getText();
   if(check(uname,pass))
   {
      frame.setVisible(false);
      AirbnbDataLoader airbnbDataLoader = new AirbnbDataLoader();
      SepanjInn jupiterInn = new SepanjInn(airbnbDataLoader);
      // wel.setVisible(true);
      // JLabel label = new JLabel("Welcome:"+uname);
      // wel.getContentPane().add(label);
    }
    else
    {
      JOptionPane.showMessageDialog(this,"Incorrect login or password",
      "Error",JOptionPane.ERROR_MESSAGE); 
    }
  }
  
  public boolean check(String searchStr1, String searchStr2) throws FileNotFoundException{
    try {Scanner scan = new Scanner(new File("users.txt"));
        boolean flag = false;
        while(scan.hasNext()){
            String line1 = scan.nextLine().toString();
            String line2 = scan.nextLine().toString();
            // System.out.println("line1: "+line1);
            // System.out.println("line2: "+line2);
            // System.out.println("searchStr1: "+searchStr1);
            // System.out.println("searchStr2: "+ searchStr2);
            if(line1.contains(searchStr1) && line2.contains(searchStr2)){
                flag = true;
                return flag;
            }
        }
    }
    catch(Exception ex){
        System.out.println("There is no file!");
        return false;}
    
    return false;
}

public static void main(String[] args) {
 new LoginForm();
}
 
}