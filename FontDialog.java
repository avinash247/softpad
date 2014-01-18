import java.awt.*;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class FontDialog extends JDialog{
	public FontDialog(JFrame owner, String title, boolean modal,JTextPane notepad){
		super(owner,title,modal);
		pad=notepad;
		init();
		setComponentsBounds();
		addComponents();
		setLayout(null);
		setBounds(45,45,450,300);
		addEventHandlers();
		setResizable(false);
	}
	public void init(){
		Font font=pad.getFont();
		lblFont=new JLabel("Select Font");
		txtFont=new JTextField("");
		Vector fonts=new Vector();
		all =ge.getAllFonts();
		for(int i=0;i<all.length;i++){
			fonts.add(all[i].getFontName());
		}
		fontList=new JList(fonts);
		list1 = new JScrollPane(fontList);
		lblStyle=new JLabel("Font Style");
		txtStyle=new JTextField("");
		Vector list=new Vector();
		list.add("Bold");
		list.add("Italic");
		list.add("Underline");
		styleList=new JList(list);
		list2 = new JScrollPane(styleList);
		int style=0;
		if(font.getStyle()==Font.BOLD){
			style=style+Font.BOLD;
		}else if(font.getStyle()==Font.ITALIC){
			style=style+Font.ITALIC;
		}
		lblSize=new JLabel("Font Size");
		txtSize=new JTextField("");
		Vector sizes=new Vector();
		for(int i=8;i<=50;i=i+1){
			sizes.add(Integer.toString(i));
		}
		sizeList=new JList(sizes);
		list3 = new JScrollPane(sizeList);
		txtSize.setText("10");
		updateFont(font.getFontName(),style,font.getSize());
		btnok=new JButton("OK");
		btnCancel=new JButton("Cancel");
	}
	public void setComponentsBounds(){
		lblFont.setBounds(15,30,150,30);
		txtFont.setBounds(15,65,150,30);
		list1.setBounds(15,100,150,120);

		lblStyle.setBounds(165,30,100,30);
		//txtStyle.setBounds(165,65,100,30);
		list2.setBounds(165,100,100,120);

		lblSize.setBounds(265,30,100,30);
		txtSize.setBounds(265,65,100,30);
		list3.setBounds(265,100,100,120);

		btnok.setBounds(370,65,70,20);
		btnCancel.setBounds(370,100,70,20);
	}
	public void addComponents(){
		add(lblFont);
		add(lblStyle);
		add(lblSize);
		add(txtFont);
		//add(txtStyle);
		add(txtSize);
		add(btnok);
		add(list1);
		add(list2);
		add(list3);
		add(btnCancel);
	}
	public void addEventHandlers(){
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				setVisible(false);
			}
		});
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				setVisible(false);
			}
		});
		fontList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent  e) {
				String fontStr=fontList.getSelectedValue().toString();
				txtFont.setText(fontStr);
			}
		});
		styleList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent  e) {
				Object str[]=styleList.getSelectedValues();
				String styleStr=null;
				for(int i=0;i<str.length;i++){
					if(styleStr==null){
						styleStr=str[i].toString();
					}else{
					styleStr=styleStr +"/" +str[i].toString();
					}
				}
				txtStyle.setText(styleStr);
			}
		});
		sizeList.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent  e) {
						String sizeStr=sizeList.getSelectedValue().toString();
						txtSize.setText(sizeStr);
					}
		});
		btnok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String fontName=txtFont.getText();
				int fontSize=Integer.parseInt(txtSize.getText());
				int style=1;
				Font tempFont=new Font(fontName,style,fontSize);
				pad.setFont(tempFont);
				System.out.println("Font set :- "+pad.getFont());
				setVisible(false);
			}
		});
	}
	public void updateFont(String fontName,int style,int size){
		txtFont.setText(fontName);
		txtSize.setText(""+size);
	}
	public static void main(String args[]){
		final JFrame f =new JFrame();
		f.setLayout(new FlowLayout());
		JButton font=new JButton("Font");
		final JTextPane te=new JTextPane();
		f.add(font);
		f.add(te);
		f.setVisible(true);
		f.setBounds(45,45,200,250);
		font.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				FontDialog fd=new FontDialog(f,"Select Font",true,te);
			}
		});
	}
	private JList fontList,styleList,sizeList;
	private JTextField txtFont,txtStyle,txtSize;
	private JLabel lblFont,lblStyle,lblSize,lblOther;
	private JButton btnok,btnCancel;
	private Font all[];
	private JTextPane pad;
	JScrollPane list1,list2,list3;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
}
