package FORM;

import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;

import login.*;
import user.EmailAddressTakenException;
import user.FieldsMismatchException;
import user.FreeUser;


public class PrimaryScreen extends Frame implements ActionListener{

	Label ltitle, lle, llp, lfn, lln, lca, lha, lse, lsp, lsre, lbd, lg;
        Button blogin, bsignup;
        TextField tle, tlp, tfn, tln, tse, tsp, tsre, tbd;
        CheckboxGroup cbg;
        Checkbox cm, cf, ca;
        
	public PrimaryScreen() {
	Color c= new Color(150,238,154);
        setSize(900, 500);
        setVisible(true);
        setLayout(null);
        setForeground(c);
        Font f=new Font("Arial Narrow",Font.BOLD,24);
        Color a= new Color(255,168,168);
        Color b= new Color(34,34,51);
        setBackground(b);


        ltitle = new Label("FRIENDSBOOK");
        ltitle.setBounds(400, 10, 250, 110);
        this.add(ltitle);
        ltitle.setFont(f);
        ltitle.setForeground(a);

        lfn = new Label("First Name");
        lfn.setBounds(70, 170, 70, 25);
        add(lfn);
        
        tfn = new TextField(30);
        tfn.setBounds(150, 170, 250, 25);
        add(tfn);
        tfn.setForeground(Color.black);

        lln = new Label("Last Name");
        lln.setBounds(70, 200, 70, 25);
        add(lln);

        tln = new TextField();
        tln.setBounds(150, 200, 250, 25);
        add(tln);
        tln.setForeground(Color.black);
        
        lse = new Label("Email id");
        lse.setBounds(70, 230, 70, 25);
        add(lse);
        
        tse = new TextField(30);
        tse.setBounds(150, 230, 250, 25);
        add(tse);
        tse.setForeground(Color.black);
        
        lsre = new Label("Re Enter id");
        lsre.setBounds(70, 260, 70, 25);
        add(lsre);
        
        tsre = new TextField(30);
        tsre.setBounds(150, 260, 250, 25);
        add(tsre);
        tsre.setForeground(Color.black);

        lsp = new Label("Password");
        lsp.setBounds(70, 290, 70, 25);
        add(lsp);

        tsp = new TextField();
        tsp.setBounds(150, 290, 250, 25);
        add(tsp);
        tsp.setForeground(Color.black);
        tsp.setEchoChar('*');

        lbd = new Label("DOB [Y-M-D]");
        lbd.setBounds(70, 320, 70, 25);
        add(lbd);

        
        tbd = new TextField(30);
        tbd.setBounds(150, 320, 250, 25);
        add(tbd);
        tbd.setForeground(Color.black);
        
        lg = new Label("Gender");
        lg.setBounds(70, 350, 70, 25);
        add(lg);
        
        cbg=new CheckboxGroup();
        
        cm= new Checkbox("Male",cbg,false);
        cm.setBounds(150,350,50,25);
        add(cm);
        
        cf= new Checkbox("Female",cbg,false);
        cf.setBounds(220,350,70,25);
        add(cf);
        
        lle = new Label("Email id");
        lle.setBounds(450, 200, 70, 25);
        add(lle);
        
        tle = new TextField(30);
        tle.setBounds(530, 200, 250, 25);
        add(tle);
        tle.setForeground(Color.black);
        
        llp = new Label("Password");
        llp.setBounds(450, 230, 70, 25);
        add(llp);

        tlp = new TextField();
        tlp.setBounds(530, 230, 250, 25);
        add(tlp);
        tlp.setForeground(Color.black);
        tlp.setEchoChar('*');
        
        lca=new Label("CREATE ACCOUNT");
        lca.setBounds(200,140,150,25);
        add(lca);
        
        lha=new Label("SIGN IN");
        lha.setBounds(630,140,300,25);
        add(lha);
        
        ca=new Checkbox("Participate in Program Improvement",false);
        ca.setBounds(70,380,500,25);
        add(ca);
        

        bsignup = new Button("Sign Up");
        bsignup.setBounds(200, 430, 110, 30);
        bsignup.setForeground(Color.black);
        add(bsignup);
        
        blogin = new Button("Log In");
        blogin.setBounds(630, 430, 110, 30);
        blogin.setForeground(Color.black);
        add(blogin);
        
  addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent we)
  {
  System.exit(0);
  }
 });

        bsignup.addActionListener(this);
        blogin.addActionListener(this);

        

		
		
	}


	public void actionPerformed(ActionEvent ae) {
            String s1="Log In";
            String s2="Sign Up";
            if (ae.getActionCommand().equals(s1)){
		String email=tle.getText();
		String password=tlp.getText();
		

		if(email.equals("") || password.equals(""))
		{
			System.out.println("Email or Password not entered correctly");
			resetAll();
			repaint();
		}
		else
		{

			Login login=null;
			try {
				login = new Login(email,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(login.isAllowed())
			{
				this.dispose();
				new SecondaryScreen(login);
			}
			else
			{       
                                LoginError lt= new LoginError();
                                setVisible(false);
				System.out.println("Incorrect Username or Password");
				resetAll();
			}
		}

	}

            else if (ae.getActionCommand().equals(s2)) {
	

		String first_name=tfn.getText();
		String last_name=tln.getText();
		String email_id=tse.getText();
		String birthdate=tbd.getText();
		String reemail_id=tsre.getText();
		String password=tsp.getText();

		
		String improvement;
		String sex;

		if(cm.isEnabled())
			sex="M";
		else
			sex="F";
		if(ca.getState()==true)
			improvement="Y";
		else improvement="N";

		if(reemail_id.equals(email_id))
		{
			String[] info={first_name,last_name,email_id,password,birthdate,sex,improvement};
			try {
				new FreeUser(info);
				System.out.println("Account Created");
                                AccSucc as=new AccSucc();
                                setVisible(false);
				resetAll();
				repaint();


			} catch (FieldsMismatchException e) {
                                EmptyField ef=new EmptyField();
				System.out.println("Enter all fields");

				// TODO Auto-generated catch block
				resetAll();
				repaint();
			}
			catch(EmailAddressTakenException e)
			{
                                
				System.out.println("Email Address Taken Enter another Email Address");
				resetAll();
				repaint();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				resetAll();
				repaint();
			}
		}
		else
		{
			resetAll();
		}


	}
        }
	public void resetAll()
	{
		tfn.setText("");
		tln.setText("");
		tse.setText("");
		tsp.setText("");
		tbd.setText("");
		tle.setText("");
		tlp.setText("");
		tsre.setText("");
                cm.setState(false);
                cf.setState(false);
                ca.setState(false);

	}


	public static void main(String args[]) {
		PrimaryScreen ps=new PrimaryScreen();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	
	// End of variables declaration//GEN-END:variables
}
