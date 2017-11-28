import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.filechooser.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class AdmissionDetails extends JApplet implements FocusListener{

private GridBagConstraints gc;    
    private Image profileImage;
    private JTextField nameJTextField;
    private ButtonGroup genderButtonGroup;
    private ButtonGroup communityButtonGroup;
    private JTextArea addressJTextArea;
    private JComboBox coursesJComboBox;
    private JTextField cutOffJTextField;
    private JTextArea officeJTextArea;
    private JButton browseJButton; 
    private JPanel imagePanel;
    private JLabel profileJLabel;
    private String name="",gender="",community="",address="",course="",cutoff="";
    public void init(){
         
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.resize(dim.width, dim.height);
        
        
        setLayout(new GridBagLayout());
        gc=new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        
        setGBConstraints(2,0);
        add(new JLabel("SteveNode Engineering College Admission Form"),gc);

        setGBConstraints(3,1);
        profileJLabel=new JLabel(new ImageIcon(new javax.swing.ImageIcon("profile.png").getImage().getScaledInstance(120, 170, Image.SCALE_SMOOTH)));
        profileJLabel.setPreferredSize(new Dimension(120,170));

        imagePanel=new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        imagePanel.add(profileJLabel,gc);
        add(imagePanel,gc);

        setGBConstraints(1,2);
        add(new JLabel("Name"),gc);
        nameJTextField=new JTextField(25);
        setGBConstraints(2,2);
        add(nameJTextField,gc);

        browseJButton=new JButton("Browse");
        setGBConstraints(3,2);
        add(browseJButton,gc);

        setGBConstraints(1,3);
        add(new JLabel("Gender"),gc);
        setGBConstraints(2,3);
        JRadioButton maleJRadioButton=new JRadioButton("Male");
        JRadioButton femaleJRadioButton=new JRadioButton("Female");
        genderButtonGroup=new ButtonGroup();
        genderButtonGroup.add(maleJRadioButton);
        genderButtonGroup.add(femaleJRadioButton);
        JPanel genderJPanel=new JPanel(new FlowLayout(FlowLayout.LEADING));
        genderJPanel.add(maleJRadioButton);
        genderJPanel.add(femaleJRadioButton);
        add(genderJPanel,gc);

        setGBConstraints(1,4);
        add(new JLabel("Community"),gc);     
        JRadioButton ocJRadioButton=new JRadioButton("OC");
        JRadioButton bcJRadioButton=new JRadioButton("BC");
        JRadioButton mbcJRadioButton=new JRadioButton("MBC");
        JRadioButton scJRadioButton=new JRadioButton("SC/ST");
        communityButtonGroup=new ButtonGroup();          
        communityButtonGroup.add(ocJRadioButton);
        communityButtonGroup.add(bcJRadioButton);
        communityButtonGroup.add(mbcJRadioButton);
        communityButtonGroup.add(scJRadioButton);
   
        JPanel communityPanel=new JPanel(new FlowLayout(FlowLayout.LEADING));
        communityPanel.add(ocJRadioButton);  
        communityPanel.add(bcJRadioButton);  
        communityPanel.add(mbcJRadioButton);  
        communityPanel.add(scJRadioButton); 
        setGBConstraints(2,4);
        add(communityPanel,gc);

        setGBConstraints(1,5);
        add(new JLabel("Address"),gc);
        addressJTextArea=new JTextArea(3,25);
        setGBConstraints(2,5);
        add(addressJTextArea,gc);

        setGBConstraints(1,6);
        add(new JLabel("Select your Course"),gc);
        coursesJComboBox=new JComboBox(new String[]{"Mech","CSE","IT","EEE","ECE","Civil","Auto Mobile"});
        setGBConstraints(2,6); 
        add(coursesJComboBox,gc);

        setGBConstraints(1,7);
        add(new JLabel("12th Cutoff"),gc);
        cutOffJTextField=new JTextField(3);
        setGBConstraints(2,7);
        JPanel cutOffJPanel=new JPanel();
        cutOffJPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        cutOffJPanel.add(cutOffJTextField,gc);
        cutOffJPanel.add(new JLabel("/200"),gc); 
        add(cutOffJPanel,gc);

        setGBConstraints(1,8); 
        add(new JLabel("Office Use"),gc);
        officeJTextArea=new JTextArea(3,25);
        setGBConstraints(2,8);
        add(officeJTextArea,gc);
        
        /*
         *Listeners of all components
         */
       
        //Focus Listener
        nameJTextField.addFocusListener(this);
        
        //File Chooser Listener
        browseJButton.addActionListener(new ActionListener(){
         
        public void actionPerformed(ActionEvent ae){
           JFileChooser fc = new JFileChooser();
               FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG Images", "jpg", "jpeg");
           fc.setFileFilter(filter);
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String sname = file.getName();
                profileJLabel= new JLabel( new ImageIcon(sname));
                imagePanel.add(profileJLabel);
                imagePanel.revalidate();
                imagePanel.repaint();
            }
       }
      }); 

     
        
        //item Listener for selecting gender
         ItemListener genderListener=new ItemListener(){
          public void itemStateChanged(ItemEvent ae){
              if(ae.getStateChange()==ItemEvent.SELECTED){
                   gender=((JRadioButton)ae.getItem()).getText();
                   displayMessage(); 
              }
            }  
        };
        maleJRadioButton.addItemListener(genderListener);
        femaleJRadioButton.addItemListener(genderListener);
       
        
        //item Listener for selecting community
        ItemListener communityListener=new ItemListener(){
          public void itemStateChanged(ItemEvent ae){
              if(ae.getStateChange()==ItemEvent.SELECTED){
                   community=((JRadioButton)ae.getItem()).getText();
                   displayMessage();
              }
            }  
       };
        ocJRadioButton.addItemListener(communityListener);
        bcJRadioButton.addItemListener(communityListener);
        mbcJRadioButton.addItemListener(communityListener);
        scJRadioButton.addItemListener(communityListener); 

       //Focus Listener for Address TextArea 
       addressJTextArea.addFocusListener(new FocusListener(){
         public void focusGained(FocusEvent e) {
           }

         public void focusLost(FocusEvent e) {
            address=((JTextArea)e.getComponent()).getText();
            displayMessage();
        
          }
         
        });
        
        //Item Listener for Course ComboBox
       coursesJComboBox.addItemListener(new ItemListener(){
           public void itemStateChanged(ItemEvent ae){
              course=coursesJComboBox.getSelectedItem().toString();
                displayMessage(); 
            }  
       });
       //Focus Listener for Cutoff TextField
       cutOffJTextField.addFocusListener(this);
       
          
    }

   public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
        JTextField genericTextField=((JTextField)e.getComponent());
        if(genericTextField.equals(nameJTextField)){
          name=genericTextField.getText();
          displayMessage(); 
        }
        else{
            try{
                Integer mark=Integer.parseInt(genericTextField.getText());
                if(mark<=200){  
                 cutoff=mark+"";
                 displayMessage();
                 
                }else{
                    JOptionPane.showMessageDialog(this, "Cutt-off mark should be less than or equal to 200");
                }
            }catch(NumberFormatException ne){
                 JOptionPane.showMessageDialog(this, "Invalid Mark");
             }  
            
         }
        
    }

    void displayMessage() {
        
         officeJTextArea.setText("Name:"
                       + name+"\n"
                       + "Gender:"+gender+"\n" 
                       + "Community: "+community+"\n" 
                       + "Address:"+address+"\n"
                       + "Course:"+course+"\n"
                       + "12th mark Cutoff:"+cutoff  
                     ); 
    }
   void setGBConstraints(int gridx,int gridy){

        gc.gridx=gridx;
        gc.gridy=gridy;                         
        gc.insets=new Insets(3,3,3,3);;
}

}
class ImagePanel extends JPanel{

    private BufferedImage image;
    
     public ImagePanel(String filePath) {
       try {                
          image = ImageIO.read(new File(filePath));
       

       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }


}
