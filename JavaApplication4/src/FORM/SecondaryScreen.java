package FORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;


import login.Login;
import user.ConnectionBehaviour;


public class SecondaryScreen extends Frame implements ConnectionBehaviour, ActionListener {


	Button bmsg;
	Button bpoke;
        Button blout;
	Choice ch1;
        Choice ch2;
	Label ltitle;
	Label lwel;
	Label lmsg;
	Label lpoke;
	Label lsend;
	Label lpkto;
	TextArea tasm;
	TextArea tarm;
	//private javax.swing.JTextField jTextField2;
	public Login login;
	public Connection myConn=null;
	public Statement qrStmt=null;
	public PreparedStatement updtStmt=null;
	public ResultSet myRs=null;

	public SecondaryScreen(Login login) {
		this.login=login;
                String[] temp=getUsersList();
                int i=temp.length;
                int j;
                
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

		lwel = new Label("WELCOME BACK!");
                lwel.setFont(new Font("Arial",Font.BOLD,20));
                lwel.setBounds(70,100,200,50);
                add(lwel);
                
		lmsg = new Label("Send Message");
                lmsg.setBounds(70,150,200,25);
                add(lmsg);
                
		tasm=new TextArea();
                tasm.setBounds(70,190,250,100);
                add(tasm);
                tasm.setForeground(Color.black);
                
                lpoke = new Label("Read Message");
                lpoke.setBounds(70,300,200,25);
                add(lpoke);
                
                tarm=new TextArea();
                tarm.setBounds(70,330,250,100);
                add(tarm);
                tarm.setForeground(Color.black);
                		
                lsend = new Label("Send To");
                lsend.setBounds(550,150,200,25);
                add(lsend);
                
                ch1=new Choice();
                for(j=0;j<i;j++){
                    ch1.add(temp[j]);}
                ch1.setBounds(550, 210, 200, 25);
                add(ch1);
                ch1.setForeground(Color.black);
                
		bmsg = new Button("Send Message");
                bmsg.setBounds(550, 260, 110, 30);
                bmsg.setForeground(Color.black);
                add(bmsg);
                
                lpkto=new Label("Poke");
                lpkto.setBounds(550,300,200,25);
                add(lpkto);
                
                ch2=new Choice();
                for(j=0;j<i;j++){
                    ch2.add(temp[j]);}
                ch2.setBounds(550, 350, 200, 25);
                add(ch2);
                ch2.setForeground(Color.black);
                
                bpoke = new Button("Poke");
                bpoke.setBounds(550, 400, 110, 30);
                bpoke.setForeground(Color.black);
                add(bpoke);
		
                blout = new Button("Log Out");
                blout.setBounds(400, 400, 110, 30);
                blout.setForeground(Color.black);
                add(blout);
		
                addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent we)
  {
  System.exit(0);
  }
 });
		
                bmsg.addActionListener(this);
                bpoke.addActionListener(this);
                blout.addActionListener(this);
		
		try {
			myConn=DriverManager.getConnection(dbUrl,userId,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrStmt=null;
		updtStmt=null;
		myRs=null;


		String email=login.getEmail();
		System.out.println(email);

		try {
			String createdStatement="SELECT * FROM user_msg WHERE receiver_email='"+email.trim()+"'";


			qrStmt=myConn.createStatement();
			myRs=qrStmt.executeQuery(createdStatement);

			while(myRs.next()){

				if(myRs.getString("read_msg").equals("N"))
				{
					String msg=myRs.getString("msg");
					String senderEmail=myRs.getString("sender_email");
					String receiverEmail=myRs.getString("receiver_email");

					createdStatement="SELECT * FROM user_info WHERE email_id='"+senderEmail+"'";
					qrStmt=myConn.createStatement();
					ResultSet tempRs=qrStmt.executeQuery(createdStatement);
					tempRs.next();
					String name=tempRs.getString("first_name")+tempRs.getString("last_name");
					tarm.setText(tarm.getText()+"\n"+""+name+":"+msg.trim());

					String preparedStatement="UPDATE user_msg SET read_msg='Y' WHERE receiver_email='"
							+receiverEmail+"' AND read_msg='N'";

					updtStmt=myConn.prepareStatement(preparedStatement);
					updtStmt.executeUpdate();
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*************************************poke******************************/

		try {
			String createdStatement="SELECT * FROM user_poke WHERE receiver_email='"+email+"'";


			qrStmt=myConn.createStatement();
			myRs=qrStmt.executeQuery(createdStatement);


			while( myRs.next()){
				//System.out.println("hello");
				if(myRs.getString("read_poke").equals("N"))
				{
					//String poke=myRs.getString("poke");
					String senderEmail=myRs.getString("sender_email");
					String receiverEmail=myRs.getString("receiver_email");

					createdStatement="SELECT * FROM user_info WHERE email_id='"+senderEmail+"'";
					qrStmt=myConn.createStatement();
					ResultSet tempRs=qrStmt.executeQuery(createdStatement);
					tempRs.next();
					String name=tempRs.getString("first_name")+tempRs.getString("last_name");
					System.out.println(name+"Poked");
					tarm.setText(tarm.getText()+"\n"+name.toUpperCase()+":Poked You" +"\n");

					String preparedStatement="UPDATE user_poke SET read_poke='Y' WHERE receiver_email='"
							+receiverEmail+"' AND read_poke='N'";

					updtStmt=myConn.prepareStatement(preparedStatement);
					updtStmt.executeUpdate();
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent ae) {
            String s1="Send Message";
            String s2="Poke";
            String s3="Log Out";
                if(ae.getActionCommand().equals(s1)){
		String email=login.getEmail();
		String sendText=tasm.getText();

		int index=((String)ch1.getSelectedItem()).indexOf(" ");

		String firstName=((String)ch1.getSelectedItem()).substring(0,index);
		//System.out.println(firstName);

		String createdStatement="SELECT email_id FROM user_info WHERE first_name='"+firstName.trim()+"'";
		String receiverEmail = null;
		try{
			qrStmt=myConn.createStatement();
			myRs=qrStmt.executeQuery(createdStatement);
			myRs.next();
			receiverEmail=myRs.getString("email_id");
			//System.out.println(receiverEmail);

			String preparedString="INSERT INTO user_msg(receiver_email,sender_email,msg,read_msg) VALUE('"+
					receiverEmail+"','"+email+"','"+sendText+"','N')";
			updtStmt=myConn.prepareStatement(preparedString);

			updtStmt.executeUpdate();

			System.out.println("Msg Sent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

                else if (ae.getActionCommand().equals(s2)) {

		String email=login.getEmail();
		int index=((String)ch2.getSelectedItem()).indexOf(" ");

		String firstName=((String)ch2.getSelectedItem()).substring(0,index);
		//System.out.println(firstName);

		String createdStatement="SELECT email_id FROM user_info WHERE first_name='"+firstName.trim()+"'";
		String receiverEmail = null;
		try{
			qrStmt=myConn.createStatement();
			myRs=qrStmt.executeQuery(createdStatement);
			myRs.next();
			receiverEmail=myRs.getString("email_id");


			String preparedString="INSERT INTO user_poke(receiver_email,sender_email,poke,read_poke) VALUE('"+
					receiverEmail+"','"+email+"','Y','N')";

			updtStmt=myConn.prepareStatement(preparedString);

			updtStmt.executeUpdate();

			System.out.println("Poked");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
                else if(ae.getActionCommand().equals(s3)){
                    PrimaryScreen pt=new PrimaryScreen();
                    setVisible(false);
                }

    }
    
public String[] getUsersList()
	{
		String email=login.getEmail();
		try {
			myConn=DriverManager.getConnection(dbUrl,userId,pass);
                        qrStmt=myConn.createStatement();
			String createdStatement="SELECT * FROM user_info WHERE email_id != '"+ email +"'";

			myRs=qrStmt.executeQuery("SELECT COUNT(*) AS rowcount FROM user_info");
			myRs.next();

			int row=myRs.getInt("rowcount");


			myRs=qrStmt.executeQuery(createdStatement);

			int i=1;
			String[] temp=new String[row];
			for(int j=0;j<row;j++)
				temp[j]="";
			while(myRs.next()){
				temp[i-1]+=(myRs.getString("first_name")+" "+myRs.getString("last_name"));
				i++;
			}
			return temp;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}


}
