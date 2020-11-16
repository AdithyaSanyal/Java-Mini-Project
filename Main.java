import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;

class Main extends JFrame implements ActionListener{
    int fwidth = 900, fheight = 600;    //Initial width and height of frame
    int field_height = 25;              //Height used by all fields
    Container c;
    JLabel name,DOB,mobile,gender,password, errors;
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

        errors = new JLabel("", SwingConstants.CENTER);
        errors.setBackground(dark_color);
        errors.setForeground(text_color);
        c.add(errors);
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

                errors.setSize((int)fwidth*7/10, (int)3*field_height/2);
                errors.setLocation((int)fwidth*15/100, start_height+getOffset(6));
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
            errors.setText(""); //Clear errors initially on submit
            sname=tname.getText();
            smobile=tmobile.getText();
            sDOB=tDOB.getText();
            if(male.isSelected()){sgender="Male";}
            else if(female.isSelected()){sgender="Female";}
            else if(others.isSelected()){sgender="Others";}
            if(sname.isBlank() || smobile.isBlank() || sDOB.isBlank()){
                errors.setText(errors.getText() + "Please fill out all fields! ");
            } 
            if((sDOB.split("-").length != 3 && sDOB.split("/").length != 3) && !sDOB.isBlank()){ //if isBlank err has occured, this should not
                errors.setText(errors.getText() + "Invalid DOB format! ");
            }
            if(!smobile.matches("[0-9]*")){
                errors.setText(errors.getText() + "Mobile numbers should be numbers only! ");
            } 
            if((smobile.length() != 10) && !(smobile.isEmpty())){   //if isBlank error has occured, this should not
                errors.setText(errors.getText() + "Mobile numbers should be 10 digits long! ");
            }
            if(errors.getText().isEmpty()){
                Main5 m=new Main5(sname,smobile,sDOB,sgender);
                m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                m.setBounds(300, 90, fwidth, fheight);
                m.setVisible(true);
                m.setTitle("MiniProject");
            }
        }
    }

}


class Main5 extends JFrame{
    int fwidth = 900, fheight = 600, field_height=25;
    Container c;
    JLabel a,a1,a2,a3;
    JButton g;
    Color dark_color = new Color(38,38,38);
    Color text_color = new Color(230, 245, 255);
    Color red_color = new Color(202, 62, 71);
    Border rBorderUnderline = BorderFactory.createMatteBorder(0, 0, 1, 0, red_color);
    Main5(String name,String mobile,String DOB,String gender){
        c = getContentPane();
        c.setBackground(dark_color);
        c.setLayout(null);

        a=new JLabel("Name: "+name);
        a.setBackground(dark_color);
        a.setForeground(text_color);
        a.setBorder(rBorderUnderline);

        a1=new JLabel("Mobile: "+mobile);
        a1.setBackground(dark_color);
        a1.setForeground(text_color);
        a1.setBorder(rBorderUnderline);

        a2=new JLabel("Date of Birth: "+DOB);
        a2.setBackground(dark_color);
        a2.setForeground(text_color);
        a2.setBorder(rBorderUnderline);

        a3=new JLabel("Gender: "+gender);
        a3.setBackground(dark_color);
        a3.setForeground(text_color);
        a3.setBorder(rBorderUnderline);

        c.add(a);
        c.add(a1);
        c.add(a2);
        c.add(a3);
        c.addComponentListener(new ComponentAdapter(){
        public int getOffset(int n){return n*(field_height+15);}//15 is padding/}
        //Resize listener:
        public void componentResized(ComponentEvent e) {
            fwidth = c.getSize().width;
            fheight = c.getSize().height;
            // Recalculate all params dependent on viewport height/width
            int start_height = (int)fheight/4;;
            if(fheight < 500){start_height = (int)fheight/10;}  //Mimic media query use
            int label_width = (int)fwidth*8/10;
            int label_start_x = (int)fwidth/10; //start at 0.1 goto 0.9, end with 0.1 left

            // Set size for all components according to new params
            a.setSize(label_width, field_height);
            a.setLocation(label_start_x, start_height);
            a1.setSize(label_width, field_height);
            a1.setLocation(label_start_x, start_height + getOffset(1));
            a2.setSize(label_width, field_height);
            a2.setLocation(label_start_x, start_height + getOffset(2));
            a3.setSize(label_width, field_height);
            a3.setLocation(label_start_x, start_height + getOffset(3));
        }    
    }
    );
        
   }
    
}