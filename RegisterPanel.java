import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
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
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
 //implements ActionListener
 /**
 * @authors Sepanj Group
 * @version (01/03/2019)FSepanj
 * */
public class RegisterPanel extends JFrame {
     JLabel l1, l2, l3, first_name, last_name, phone,email;
     JTextField username,firstName,lastName,phoneNum,emailAdd;
     JButton btn1, btn2;
     JPasswordField password;
     private JPanel pricePanel;
     private JFrame frame;
     boolean log_in_done = false;
 
RegisterPanel(){
  frame = new JFrame("Register Form");
  try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Jupiter.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
  // makeFrame(frame);
  
  l1 = new JLabel("Register Form");
  l1.setForeground(Color.BLACK);
  l1.setFont(new Font("Serif", Font.BOLD, 25));
 
  first_name= new JLabel("Fisrt Name");
  last_name = new JLabel("Last Name");
  phone = new JLabel("Phone Number");
  email = new JLabel("Email Address");
  
  l2 = new JLabel("Set Username");
  l3 = new JLabel("Set Password");
  
  firstName = new JTextField();
  lastName = new JTextField();
  phoneNum = new JTextField();
  emailAdd = new JTextField();
  username = new JTextField();
  password = new JPasswordField();
  btn2 = new JButton("Register");
  // btn2.addActionListener(e -> actionRegister(username,password));
  btn2.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
            try {actionRegister(username,password);  }
            catch(FileNotFoundException ex){} 
    }  
    });
        
  l1.setBounds(100, 30, 400, 30);
  first_name.setBounds(80, 70, 200, 30);
  last_name.setBounds(80, 110, 200, 30);
  phone.setBounds(80, 150, 200, 30);
  email.setBounds(80, 190, 200, 30);
  l2.setBounds(80, 230, 200, 30);
  l3.setBounds(80, 270, 200, 30);
  firstName.setBounds(300, 70, 200, 30);
  lastName.setBounds(300, 110, 200, 30);
  phoneNum.setBounds(300, 150, 200, 30);
  emailAdd.setBounds(300, 190, 200, 30);
  username.setBounds(300, 230, 200, 30);
  password.setBounds(300, 270, 200, 30);
  btn2.setBounds(200, 300, 100, 30);
  
  //btn2.addActionListener(e -> actionRegister(username,password));
  frame.add(first_name);
  frame.add(last_name);
  frame.add(phone);
  frame.add(email);
  frame.add(l1);
  frame.add(l2);
  frame.add(firstName);
  frame.add(lastName);
  frame.add(phoneNum);
  frame.add(emailAdd);
  frame.add(username);
  frame.add(l3);
  frame.add(password);
  // frame.add(btn1);
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
     
 public void actionRegister(JTextField useranem,JPasswordField password) throws FileNotFoundException{
    try{
        String user = username.getText(); //username.toString();
        String pass = password.getText();
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String phonenumber = phoneNum.getText();
        String emailadd = emailAdd.getText();
          if(parseFile(user,pass)) {
            //log_in_done = true;
            //System.out.print("Log in Done !");
            JOptionPane.showMessageDialog(this,"NOoo!!! Please Enter Another Username");            
        }else{
                // while(CheckforDup(user)){
                    // System.out.print("Please Enter Another Username : ");
                    // new RegisterPanel();
                    // // username = user.toString().next(); //was cs.next
                // }
                AddNewUser(user,pass,firstname,lastname,phonenumber,emailadd);
                frame.setVisible(false);
                AirbnbDataLoader airbnbDataLoader = new AirbnbDataLoader();
                SepanjInn jupiterInn = new SepanjInn(airbnbDataLoader);
                //new LoginForm();
                //System.out.print("You're Registered & Logged in !");
                JOptionPane.showMessageDialog(this,"You're Registered Successfuly! Please Login now");
            }
    }
    catch (FileNotFoundException ex){} 
    
}

public static void AddNewUser(String username, String password,String firstname, String lastname, String emailadd, String phonenumber)
    {
        try {
            // System.out.println("username: "+username);
            // System.out.println("password: "+ password);
            FileWriter fw = new FileWriter("users.txt", true);
            FileWriter fw2 = new FileWriter("users info.txt", true);
            fw.write(username+" "+"\n"+password+"\n");
            fw.close();
            fw2.write(username+" "+"\n"+password+"\n"+firstname+"\n"+lastname+"\n"+emailadd+"\n"+phonenumber+"\n");
            fw2.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

public static boolean CheckforDup(String searchStr1) throws FileNotFoundException{
        try{Scanner scan = new Scanner(new File("users.txt"));
        boolean flag = false;
        while(scan.hasNext()){
            String line1 = scan.nextLine().toString();
            if(line1.equals(searchStr1)){
                flag = true;
                return flag;
            }
        }
        return flag;}
        catch(Exception ex){return false;}
    }
    
public static boolean parseFile(String searchStr1, String searchStr2) throws FileNotFoundException{
    try{Scanner scan = new Scanner(new File("users.txt"));
    boolean flag = false;
    while(scan.hasNextLine()){
        String line1 = scan.nextLine().toString();
        String line2 = scan.nextLine().toString();
        // System.out.println("line1: "+line1);
        // System.out.println("line2: "+line2);
        // System.out.println("searchStr1: "+ searchStr1);
        // System.out.println("searchStr2: "+ searchStr2);
        if(line1.equals(searchStr1) && line2.equals(searchStr2)){
            flag = true;
            return flag;
        }
    }
    return flag;}
    catch(Exception ex){return false;}
}


public static void main(String[] args) throws FileNotFoundException{
    try{new RegisterPanel();}
    catch(Exception ex){}
}
 
}