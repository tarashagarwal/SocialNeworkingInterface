package login;

import FORM.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class LoginError extends Frame implements ActionListener{
    Label l;
    Button b1;
    public LoginError() {
	Color c= new Color(150,238,154);
        setSize(500, 200);
        setVisible(true);
        setLayout(null);
        setForeground(c);
        
        
        Color a= new Color(255,168,168);
        Color b= new Color(34,34,51);
        
        setBackground(b);
        l= new Label("Invalid email or password");
        l.setBounds(150,50,400,100);
        add(l);
        
        b1=new Button("Go Back");
        b1.setBounds(150,150,100,25);
        add(b1);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        
    addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent we)
  {
  System.exit(0);
  }
 });}
    public void actionPerformed(ActionEvent e){
        PrimaryScreen pr=new PrimaryScreen();
        setVisible(false);
    }
    
    public static void main(String args[]){
        LoginError le=new LoginError();
    }
}