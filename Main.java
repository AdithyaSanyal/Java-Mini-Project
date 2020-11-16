import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Main extends JFrame implements ActionListener{
    int fwidth = 900, fheight = 600;    //Initial width and height of frame
    int field_height = 25;              //Height used by all fields
    Container c;
    JLabel name,DOB,mobile,gender,password;
    JTextField tname,tDOB,tmobile,tpassword;
    String sname,sDOB,smobile,sgender,spassword;
    JButton submit;
    JRadioButton male,female,others;
    ButtonGroup gengp;
    Main(){
        Color dark_color = new Color(38,38,38);
        Color text_color = new Color(230, 245, 255);
        Color red_color = new Color(202, 62, 71);
        c=getContentPane();
        c.setBackground(dark_color);
        c.setLayout(null);
        
        /*
            Size and location of labels and fields are set inside the resize listener
            this is because the resize listener's componentResized method is fired
            initially when the JFrame is initialized, so no need to define the loc and size
            here as well as in the listener
        */

        name=new JLabel("Name: ");
        name.setForeground(text_color);
        c.add(name);

        tname=new JTextField(10);
        c.add(tname);

        DOB=new JLabel("Date of Birth: ");
        DOB.setForeground(text_color);
        c.add(DOB);

        tDOB=new JTextField(10);
        c.add(tDOB);

        mobile=new JLabel("Mobile Number: ");
        mobile.setForeground(text_color);
        c.add(mobile);

        tmobile=new JTextField(10);
        c.add(tmobile);

        gender=new JLabel("Gender: ");
        gender.setForeground(text_color);
        c.add(gender);

        male=new JRadioButton("Male");
        male.setBackground(dark_color);
        male.setForeground(text_color);
        male.setSelected(false);
        c.add(male);

        female=new JRadioButton("Female");
        female.setBackground(dark_color);
        female.setForeground(text_color);
        female.setSelected(false);
        c.add(female);

        others=new JRadioButton("Other");
        others.setBackground(dark_color);
        others.setForeground(text_color);
        others.setSelected(false);
        c.add(others);

        gengp=new ButtonGroup();
        gengp.add(male);
        gengp.add(female);
        gengp.add(others);

        submit=new JButton("Submit");
        submit.setBackground(red_color);
        submit.addActionListener(this);
        c.add(submit);
        
        //Resize listener:
        /*
            Settings:
            field_height = height of all fields                  (constant class var defined above the constructor)
            start_height = height of 1st component               (25vh)
            label_width = width of all Jlabels                   (10vw)
            label_start_x = starting x coordinate for all labels (10vw)
            field_width = width of all fields                    (25vw)
            field_start_x = starting x coordinate for all fields (65vw)
        */
        c.addComponentListener(new ComponentAdapter(){
        public int getOffset(int n){return n*(field_height+15); /*15 is padding*/}
            public void componentResized(ComponentEvent e) {
                fwidth = c.getSize().width;
                fheight = c.getSize().height;
                // Recalculate all params dependent on viewport height/width
                int start_height = (int)fheight/4;;
                if(fheight < 500){start_height = (int)fheight/10;}  //Mimic media query use
                int label_width = (int)fwidth/10;
                int label_start_x = (int)fwidth/10;
                int field_width = (int) fwidth*65/100;
                int field_start_x = (int)fwidth*25/100;
                // Set size for all components according to new params
                name.setSize(label_width, field_height);
                name.setLocation(label_start_x, start_height);
                tname.setSize(field_width, field_height);
                tname.setLocation(field_start_x, start_height);

                DOB.setSize(label_width, field_height);
                DOB.setLocation(label_start_x, start_height+getOffset(1));

                tDOB.setSize(field_width, field_height);
                tDOB.setLocation(field_start_x, start_height+getOffset(1));

                mobile.setSize(label_width, field_height);
                mobile.setLocation(label_width, start_height+getOffset(2));

                tmobile.setSize(field_width, field_height);
                tmobile.setLocation(field_start_x, start_height+getOffset(2));
                
                gender.setSize(label_width, 20);
                gender.setLocation(label_start_x, start_height+getOffset(3));
                male.setSize(field_width, 20);
                male.setLocation(field_start_x, start_height+getOffset(3));
                female.setSize(field_width,20);
                female.setLocation(field_start_x, start_height+getOffset(3)+15);
                others.setSize(field_width, 20);
                others.setLocation(field_start_x, start_height+getOffset(3)+30);
                
                // Submit button will be different, roughly centered
                submit.setSize((int)fwidth*7/10, (int)3*field_height/2);
                submit.setLocation((int)fwidth*15/100, start_height+getOffset(5));
            }    
        }
        );

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
            m.setBounds(300, 90, fwidth, fheight);
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