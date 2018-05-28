import javax.swing.filechooser.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.*;

public class makeFrame {
  JFrame jframe = new JFrame();
 JPanel leftP = new JPanel();
 JPanel centerP = new JPanel();
 JPanel rightP = new JPanel();
 JPanel button = new JPanel();
 JPanel button2 = new JPanel();

 private JFileChooser filechoose = new JFileChooser();

 private JButton load = new JButton("load");
 private JButton edit = new JButton("edit");
 private JButton save = new JButton("save");
 private JButton load2 = new JButton("load");
 private JButton edit2 = new JButton("edit");
 private JButton save2 = new JButton("save");
 private JButton cmpare = new JButton("compare");
 private JButton mergeRi = new JButton("merge >>");
 private JButton mergeLe = new JButton("merge <<");

 JTextArea jta = new JTextArea();
 JTextArea jta2 = new JTextArea();
 JScrollPane scroll = new JScrollPane(jta);
 JScrollPanel scroll2 = new JScrollPane(jta2);
  
  public makeFrame() {
  button.setLayout(new GridLayout(1, 3));
  button.add(load);
  button.add(edit);
  button.add(save);
  load.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Text file", "txt"));
    filechoose.setAcceptAllFileFilterUsed(false);
    int returnVal = filechoose.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
     try {
      File f = filechoose.getSelectedFile();
      fileopen(f, jta);
     } catch (Exception event) {
      return;
     }
    }
   }
  });
    
    edit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        load.setEnabled(false);
        jta.setEditable(true);
      }
    });
    save.adActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        load.setEnabled(true);
        jta.setEditable(false);
      }
    });
    
    button2.setLayout(new GridLayout(1,3));
    button2.add(load2);
    button2.add(edit2);
    button2.add(save2);
    
    load2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Text file", "txt"));
    filechoose.setAcceptAllFileFilterUsed(false);
    int returnVal = filechoose.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        File f = filechoose.getSelectedFile();
        fileopen(f,jta2);
      }
      catch(IOException event){
        return;
      }
    }
      }
    });
    
    edit2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        load2.setEnabled(false);
        jta2.setEditable(true);
      }
    });
    
    save2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        load2.setEnabled(true);
        jta2.setEditable(false);
      }
    });
    
    leftP.setLayout(new GridLayout(2,1));
    leftP.add(button);
    leftP.setPreferredSize(new Dimension(280,580));
    leftP.add(scroll);
  jta.setEditable(false);
    
    centerP.setLayout(new GridLayout(2, 1));
  centerP.add(button2);
  centerP.setPreferredSize(new Dimension(280, 580));
  centerP.add(scroll2);
  jta2.setEditable(false);
   
    rightP.setLayout(new GridLayout(3, 1));
  rightP.setPreferredSize(new Dimension(100, 550));
  rightP.setPreferredSize(new Dimension(100, 550));
  rightP.setPreferredSize(new Dimension(100, 550));
  rightP.setPreferredSize(new Dimension(100, 550));
    
    jframe.add(leftP, "West");
    jframe.add(rightP,"Center");
    jframe.add(centerP, "East");
    
     jframe.setSize(700, 600);
  jframe.setVisible(true);

  jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);

 }
  
  void fileopen(File f, JTextArea a) throws FileNotFoundException, IOException {
  FileReader filereader = new FileReader(f);
  BufferedReader reader = new BufferedReader(filereader);
    
    String line = null;
    if(a!= nul){
      a.setText("");
    }
    while((line =reader.readLine())!= null){
      a.append(line);
      a.append("\n");
    }
    reader.close();
  }
}