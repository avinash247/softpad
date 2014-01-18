import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.print.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
public class SoftPad extends  JFrame{
	public static void main(String args[]){
            try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new SoftPad("Soft-Tech Textpad - Untitled");
			}catch(ClassNotFoundException ce){
			}catch(InstantiationException ie){
			}catch(IllegalAccessException ies){
			}catch(UnsupportedLookAndFeelException ue){
			}
	}
	public SoftPad(String title){
		setTitle(title);
		save=new FileDialog(this,"Save File As",FileDialog.SAVE);
		open=new FileDialog(this,"Open File",FileDialog.LOAD);
		setBounds(45,85,640,480);
		setLayout(new BorderLayout());
		createMenu();
		setJMenuBar(menubar);
		notepad=new  JTextPane();
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/softpad.png"));
		notepad.setFont(new Font("monospaced", Font.PLAIN, 12));
		flg=new FontDialog(SoftPad.this,"Select Font ",true,notepad);
		scrollPane=new  ScrollPane();
        scrollPane.add(notepad);
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        //add(toolbar,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.CENTER);
		statusBar=new  JLabel("");
		add(statusBar,BorderLayout.SOUTH);
		setVisible(true);
		//Menu Commands
		fileNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				newAction();
			}
		});
		fileOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				openAction();
			}
		});
		fileSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				saveAction();
			}
		});
		fileSaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				saveAction();
			}
		});
		filePrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					notepad.print();
				}catch(PrinterException pe){
				}
			}
		});
		fileExit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
							exitAction();
					}
		});
		editCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				notepad.cut();
			}
		});
		editCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				notepad.copy();
			}
		});
		editPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				notepad.paste();
			 }
		});
		editDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				notepad.replaceSelection("");
			}
		});
		editFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){

			}
		});
		editReplace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){

			}
		});
		editCount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String str=notepad.getText();
				String newString=notepad.getText().replace(" ","");
				JOptionPane.showMessageDialog(SoftPad.this,"Count  : -\n Total Words(With Space)      : -  "+str.length()+"\nTotal Words(Without Space)  : - "+newString.length(),"Words Count",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		editSelectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				notepad.selectAll();
			}
		});
		editTDate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Calendar currentDate = Calendar.getInstance();
				String time=null;
				String today= "Current Date is \n"+""+currentDate.get(Calendar.DATE)+"/"+(currentDate.get(Calendar.MONTH)+1)+"/"+currentDate.get(Calendar.YEAR);
				if(currentDate.get(Calendar.AM_PM  )==0){
					time="\n"+ "Current Time is \n"+""+currentDate.get(Calendar.HOUR )+":"+currentDate.get(Calendar.MINUTE)+":"+currentDate.get(Calendar.SECOND )+"AM";
				}else{
					time="\n"+ "Current Time is \n"+""+currentDate.get(Calendar.HOUR )+":"+currentDate.get(Calendar.MINUTE)+":"+currentDate.get(Calendar.SECOND )+"PM";
				}
				notepad.setText(notepad.getText()+today+time);
				JOptionPane.showMessageDialog(SoftPad.this,today+time, "Time and Date", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		formatFont.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				flg.show();
			}
		});
		helpAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(SoftPad.this,"Name : - Nikhil M. Bokade \n Class : - MCA IInd Year","About Us ",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/about.png")));
			}
		});
		notepad.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent e){
				Calendar currentDate = Calendar.getInstance();
				String time=null;
				String today= "Current Date : - "+currentDate.get(Calendar.DATE)+"/"+(currentDate.get(Calendar.MONTH)+1)+"/"+currentDate.get(Calendar.YEAR);
				if(currentDate.get(Calendar.AM_PM  )==0){
					time="Current Time : - "+currentDate.get(Calendar.HOUR )+":"+currentDate.get(Calendar.MINUTE)+":"+currentDate.get(Calendar.SECOND )+"AM";
				}else{
					time="Current Time : - "+currentDate.get(Calendar.HOUR )+":"+currentDate.get(Calendar.MINUTE)+":"+currentDate.get(Calendar.SECOND )+"PM";
				}
				statusBar.setText("Caret Position : "+ notepad.getCaretPosition() +"   Date : - "+today+" -- "+time );
			}
		});
		notepad.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyChar()=='<'){

				}
				if(ke.getKeyChar()=='>'){
				}
			}
		});
		formatColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Color color=JColorChooser.showDialog(SoftPad.this,"Choose Text color - ",notepad.getForeground());
				notepad.setForeground(color);
			}
		});
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
					exitAction();
			}
		});
	}
	private void createMenu(){
		menubar=new  JMenuBar();

		file=new  JMenu("File");
		edit=new  JMenu ("Edit");
		format=new  JMenu("Format");
		view=new  JMenu("View");
		help=new  JMenu("Help");

		fileNew=new  JMenuItem("New");
		fileNew.setMnemonic(KeyEvent.VK_N);
		fileOpen=new  JMenuItem("Open..");
		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileSave=new  JMenuItem("Save");
		fileSave.setMnemonic(KeyEvent.VK_S);
		filePrint=new JMenuItem("Print Document");
		filePrint.setMnemonic(KeyEvent.VK_P);
		fileSaveAs=new  JMenuItem("Save As...");
		fileExit=new  JMenuItem("Exit");

		editCut=new  JMenuItem("Cut");
		editCut.setMnemonic(KeyEvent.VK_X);
		editCopy=new  JMenuItem("Copy");
		editCopy.setMnemonic(KeyEvent.VK_C);
		editPaste=new  JMenuItem("Paste");
		editPaste.setMnemonic(KeyEvent.VK_P);
		editDelete=new  JMenuItem("Delete");
		editFind=new  JMenuItem("Find...");
		editReplace=new  JMenuItem("Replace...");
		editSelectAll=new  JMenuItem("Select All");
		editCount=new  JMenuItem("Count");
		editTDate=new  JMenuItem("Time/Date");

		formatFont=new  JMenuItem("Font...");
		formatFont.setMnemonic(KeyEvent.VK_F);
		formatColor=new JMenuItem("Color");

		viewStatus=new  JMenuItem("Status Bar");

		helpAbout=new  JMenuItem("About...");

		// Adding to the menubar

		file.add(fileNew);
		file.add(fileOpen);
		file.add(fileSave);
		file.add(fileSaveAs);
		file.addSeparator();
		file.add(filePrint);
		file.addSeparator();
		file.add(fileExit);

		edit.add(editCut);
		edit.add(editCopy);
		edit.add(editPaste);
		edit.add(editDelete);
		//edit.addSeparator();
		//edit.add(editFind);
		//edit.add(editReplace);
		edit.addSeparator();
		edit.add(editCount);
		edit.add(editTDate);
		edit.add(editSelectAll);

		format.add(formatFont);
		format.add(formatColor);

		view.add(viewStatus);

		help.add(helpAbout);

		menubar.add(file);
		menubar.add(edit);
		menubar.add(format);
		//menubar.add(view);
		menubar.add(help);


	}
	public void newAction(){
		if(notepad.getText().equals("")){
			notepad.setText("");
		}else{
			int n=JOptionPane.showConfirmDialog(SoftPad.this,"Document will be lost -  want to save ?", "Do you want to save the document ", JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION ){
				saveAction();
				notepad.setText("");
			}else{
				notepad.setFocusable(true);
				notepad.setText("");
			}
		}
		setTitle("Soft-Tech Textpad - Untitled");
	}
	public void exitAction(){
		if(notepad.getText().equals("")){
					notepad.setText("");
		}else{
			int n=JOptionPane.showConfirmDialog(SoftPad.this,"Document will be lost -  want to save ?", "Do you want to save the document ", JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION ){
				saveAction();
				System.exit(1);
			}
		}
		System.exit(1);
	}
	public void openAction( ){
		String f,d;
		open.show();
		f=open.getFile();
		d=open.getDirectory();
		setTitle(d+f);
		if ((f == null) || (f.length() == 0)) return;
		        File file;
		        FileReader in = null;
		        try {
		            file = new File(d,f);
		            in = new FileReader(file);
		            char[] buffer = new char[4096];
		            int len;
		            notepad.setText("");
		            notepad.read(in,new Object());
					setTitle("Soft-Tech Textpad - [ " +d+f+"]");
					}catch (IOException e) {
						notepad.setText(e.getClass().getName() + ": " + e.getMessage());
						setTitle("Soft-Tech Textpad - [" + f + " - I/O Exception]");
					}finally { try { if (in!=null) in.close(); } catch (IOException e) {} }
	}
	public void saveAction(){
	String f1,d1,s3;
	save.show();
	f1=save.getFile();
	d1=save.getDirectory();
	setTitle(d1+f1);
	if ((f1 == null) || (f1.length() == 0)) return;
			File file1;
			FileWriter out = null;
			try {
				file1 = new File(d1,f1);
				out = new FileWriter(file1);
				s3=notepad.getText();
				out.write(s3);
			   setTitle("Soft-Tech Textpad - [ " +d1+f1+"]");
			}catch (IOException e1) {
				notepad.setText(e1.getClass().getName() + ": " + e1.getMessage());
				setTitle("Soft-Tech Textpad  - [" + f1 + " - I/O Exception]");
		}finally { try { if (out!=null) out.close(); } catch (IOException e) {} }
	}
	//Menu
	private  JMenuBar menubar;
	private  JMenu file,edit,format,view,help;
	private  JMenuItem fileNew,fileOpen,fileSave,fileSaveAs,filePrint,fileExit;
	private  JMenuItem editCut,editCopy,editPaste,editDelete,editFind,editReplace,editCount,editSelectAll,editTDate;
	private  JMenuItem formatFont,formatColor;
	private  JMenuItem viewStatus,viewTools;
	private FileDialog save,open;
	private FontDialog flg;
	private JToolBar  toolbar=new JToolBar();
	private JMenuItem helpWebsite,helpContent,helpAbout;
	//textArea
	private  ScrollPane scrollPane;
	private  JTextPane notepad;
	//status bar
	JLabel statusBar;
}
