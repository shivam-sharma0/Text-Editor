import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;


    //Menu item of File menu and edit menu
    JMenuItem newFile,openFile,saveFile,copy,paste,cut,selectAll,close;

    // This is for the textArea
    JTextArea textArea;
    TextEditor(){
        //Initialize the Frame
        frame=new JFrame();

        //Initialize the Menubar
        menuBar=new JMenuBar();

        //Initialize the Text Area
        textArea=new JTextArea();

        //Initialize the menus
        file=new JMenu("File");
        edit=new JMenu("Edit");


        // Initialize the File menu item
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open");
        saveFile=new JMenuItem("Save");
        // Add Action Lister to the file items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add the File menu items in file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);


        //Initialize the Edit menu item
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        cut=new JMenuItem("Cut");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //Add actionLister to the edit items
        copy.addActionListener(this);
        paste.addActionListener(this);
        cut.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add the Edit Menu Items in Edit menu
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        edit.add(close);


        // Add the File and Edit menus in the MenuBar
        menuBar.add(file);
        menuBar.add(edit);

        // Add the menubar in the Frame
        frame.setJMenuBar(menuBar);

//        //Add the text Area in the Frame
//        frame.add(textArea);


        // This is for scroller
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        //Add the panel into the frame
        frame.add(panel);
        // Set the diamension of the frame
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
        frame.setTitle("Text Editor");
        frame.setLayout(null);
    }

    // Actions For the Edit and File Menu's Item
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==copy){
            // Performe the copy function which is already present in the textArea class
            textArea.copy();

        }
        if(actionEvent.getSource()==cut){
            // Performe the cut function which is already present in the textArea class
            textArea.cut();

        }
        if(actionEvent.getSource()==paste){
            // Performe the paste function which is already present in the textArea class
            textArea.paste();

        }
        if(actionEvent.getSource()==selectAll){
            // Performe the selectall function which is already present in the textArea class
            textArea.selectAll();

        }
        if(actionEvent.getSource()==close){
            // Performe the close function
            System.exit(0); // System.exit with the status 0 it will help us to exit the program and clsoe the program
        }

        //File Menu item's Action
        if(actionEvent.getSource()==openFile) {
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            //If we have clicked an open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //Getting the Selected File
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try {
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize the BufferReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read content of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

            //If we have clicked on save button
            if(actionEvent.getSource()==saveFile){
                JFileChooser fileChooser=new JFileChooser("C:");
                int chooseOption=fileChooser.showSaveDialog(null);
                //If we have clicked an save button
                if(chooseOption==JFileChooser.APPROVE_OPTION){

                    File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                    try{
                        //Initialize the file writer
                        FileWriter fileWriter=new FileWriter(file);
                        //Initialize the Buffer writer
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                        //Write contents of textArea to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();

                    }catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }
            if(actionEvent.getSource()==newFile){
                TextEditor newTextEditor=new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}