package notepad;

import java.awt.*;
import java.awt.event.*;
//import javax.swing.border.Border;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener
{
    JTextArea area;
    String text;
    Notepad()
    {
        super("Notepad Clone");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/download.png"));
        Image icon = i1.getImage();
        setIconImage(icon);
        
        JMenuBar m1=new JMenuBar();
        m1.setBackground(Color.WHITE);
        
        JMenu file = new JMenu("File");
        file.setFont(new Font("ARIAL",Font.BOLD,17));
        
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        
        JMenuItem opendoc = new JMenuItem("Open");
        opendoc.addActionListener(this);
        opendoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        
        JMenuItem savedoc = new JMenuItem("Save");
        savedoc.addActionListener(this);
        savedoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        
        JMenuItem printdoc = new JMenuItem("Print");
        printdoc.addActionListener(this);
        printdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,ActionEvent.CTRL_MASK));
        
        file.add(newdoc);
        file.add(opendoc);
        file.add(savedoc);
        file.add(printdoc);
        file.add(exit);
        
        m1.add(file);
        
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("ARIAL",Font.BOLD,17));
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        
        JMenuItem select = new JMenuItem("Select All");
        select.addActionListener(this);
        select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
               
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(select);
        
        m1.add(edit);
        
        JMenu help = new JMenu("Help");
        help.setFont(new Font("ARIAL",Font.BOLD,17));
        
        JMenuItem window = new JMenuItem("Windows");
        window.addActionListener(this);
        window.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));
        
        help.add(window);
        
        m1.add(help);
        
        
        setJMenuBar(m1);
        
        
        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getActionCommand().equals("New"))
        {
            area.setText("");
        }
        else if (ae.getActionCommand().equals("Open"))
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);
            
            int action = chooser.showOpenDialog(this);
            
            if (action != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            
            File file = chooser.getSelectedFile();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Save"))
        {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            
            int action = saveas.showOpenDialog(this);
            
            if (action != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try
            {
               outFile = new BufferedWriter(new FileWriter(filename));
               area.write(outFile);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        else if (ae.getActionCommand().equals("Print"))
        {
            try
            {
                area.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy"))
        {
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste"))
        {
            area.insert(text,area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut"))
        {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
            
        }
        else if(ae.getActionCommand().equals("Select All"))
        {
            area.selectAll();
        }
//        else if(ae.getActionCommand().equals("Help"))
//        {
//            area.selectAll();
//        }
        
    }
    
    public static void main(String args[])
    {
        new Notepad();
    }
}
