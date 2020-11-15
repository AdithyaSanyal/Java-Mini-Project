import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Main extends JFrame implements ActionListener{

    Container c;
    JLabel name,DOB,mobile,gender,password;
    JTextField tname,tDOB,tmobile,tpassword;
    String sname,sDOB,smobile,sgender,spassword;
    JButton submit;
    JRadioButton male,female,others;
    ButtonGroup gengp;
    Main(){
        c=getContentPane();
        c.setLayout(null);

        name=new JLabel("Name: ");
        name.setSize(100,40);
        name.setLocation(100,100);
        c.add(name);

        tname=new JTextField(10);
        tname.setSize(190,40);
        tname.setLocation(150,100);
        c.add(tname);

        DOB=new JLabel("Date of Birth: ");
        DOB.setSize(120,40);
        DOB.setLocation(60,160);
        c.add(DOB);

        tDOB=new JTextField(10);
        tDOB.setSize(190,40);
        tDOB.setLocation(150,160);
        c.add(tDOB);

        mobile=new JLabel("Mobile Number: ");
        mobile.setSize(120,40);
        mobile.setLocation(40,220);
        c.add(mobile);

        tmobile=new JTextField(10);
        tmobile.setSize(190,40);
        tmobile.setLocation(150,220);
        c.add(tmobile);

        gender=new JLabel("Gender: ");
        gender.setSize(120,40);
        gender.setLocation(60,280);
        c.add(gender);

        male=new JRadioButton("Male");
        male.setSelected(false);
        male.setSize(75,20);
        male.setLocation(150,300);
        c.add(male);

        female=new JRadioButton("Female");
        female.setSelected(false);
        female.setSize(75,20);
        female.setLocation(150,320);
        c.add(female);

        others=new JRadioButton("Other");
        others.setSelected(false);
        others.setSize(75,20);
        others.setLocation(150,340);
        c.add(others);

        gengp=new ButtonGroup();
        gengp.add(male);
        gengp.add(female);
        gengp.add(others);

        submit=new JButton("Submit");
        submit.setSize(100,20);
        submit.setLocation(150,400);
        submit.addActionListener(this);
        c.add(submit);


    }

    public static void main(String[] args) {
        Main m=new Main();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setBounds(300, 90, 900, 600);
        m.setVisible(true);
        m.setTitle("MiniProject");
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){

            sname=tname.getText();
            smobile=tmobile.getText();
            sDOB=tDOB.getText();
            if(male.isSelected()){
                sgender="Male";
            }
            else if(female.isSelected()){
                sgender="Female";
            }
            else if(others.isSelected()){
                sgender="Others";
            }

            Main5 m=new Main5(sname,smobile,sDOB,sgender);
            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m.setBounds(300, 90, 900, 600);
            m.setVisible(true);
            m.setTitle("MiniProject");
        }
    }

}


class Main5 extends JFrame{

    Container c;
    JTextField a,a1,a2,a3;
    JButton g;
    Main5(String name,String mobile,String DOB,String gender){
        c=getContentPane();
        c.setLayout(null);

        a=new JTextField("Name: "+name);
        a.setSize(200,75);
        a.setLocation(200,100);
        c.add(a);

        a1=new JTextField("Mobile: "+mobile);
        a1.setSize(200,75);
        a1.setLocation(200,150);
        c.add(a1);

        a2=new JTextField("Date of Birth: "+DOB);
        a2.setSize(200,75);
        a2.setLocation(200,200);
        c.add(a2);

        a3=new JTextField("Gender: "+gender);
        a3.setSize(200,75);
        a3.setLocation(200,250);
        c.add(a3);
        
   }

   

   

    
    
}