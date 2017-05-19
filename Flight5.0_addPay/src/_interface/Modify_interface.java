package _interface;
//功能：管理员界面，显示增删改的窗口，显示航班信息
//作者：孙加辉，时间：2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _flight.List;
import _manager.ModifyFlight;
public class Modify_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextArea text = new JTextArea(10,63);
	private JScrollPane textroll = new JScrollPane(text);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JButton _add = new JButton("增加一个航班");
	private JButton _del = new JButton("删除一个航班");
	private JButton _modi= new JButton("修改一个航班");
	private JButton _flush = new JButton("刷新列表");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Modify_interface(){
		super("修改订票信息");
		super.setIconImage(iocn);
		//界面属性
		setSize(750,400);
		setVisible(true);
		setLayout(new GridLayout(2,1));
		//添加控件
		add(jp1);add(jp2);
		jp1.add(textroll);//将滚动条加入容器中
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置滚动条一直在
		textroll.setBounds(20,20,100,220);//设置滚动条大小
		text.setLineWrap(true); //滚动条自动换行
		text.setEditable(false);//可编辑
		showlist();
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg2.jpg");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        //将其他控件设置为透明
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        textroll.setOpaque(false);
        //设置绝对大小和位置
    	setLocation(400, 150);
    	setResizable(false);
    	 //设置按钮大小
    	_add.setPreferredSize(new Dimension(150, 30));
    	_del.setPreferredSize(new Dimension(150, 30));
    	_modi.setPreferredSize(new Dimension(150, 30));
    	_flush.setPreferredSize(new Dimension(120, 30));
        //设置按钮颜色
    	_add.setBackground(new Color(155,155,255));
    	_add.setFont(new Font("幼圆",Font.BOLD,15));
    	_del.setBackground(new Color(255,155,155));
    	_del.setFont(new Font("幼圆",Font.BOLD,15));
    	_modi.setBackground(new Color(155,155,55));
    	_modi.setFont(new Font("幼圆",Font.BOLD,15));
    	_flush.setBackground(new Color(155,255,155));
    	_flush.setFont(new Font("幼圆",Font.BOLD,15));
		//添加按钮
		jp2.add(_add);
		jp2.add(_del);
		jp2.add(_modi);
		jp2.add(_flush);
		//添加功能
		_add.addActionListener(this);
		_del.addActionListener(this);
		_modi.addActionListener(this);
		_flush.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		//增加一个航班
		if(e.getSource()==_add){
			Modify_add_interface add = new Modify_add_interface();
			add.setEnabled(true);
			showlist();
		}
		//删除一个航班
		else if(e.getSource()==_del){
			String temp1 = JOptionPane.showInputDialog("请输入要删除的航班序号");
			if(temp1!=null)
			ModifyFlight.delFlight(Integer.parseInt(temp1));
			showlist();
		}
		//调整一个航班
		else if(e.getSource()==_modi){
			Modify_modi_interface mo = new Modify_modi_interface();
			mo.setEnabled(true);
			showlist();
		}
		//刷新航班列表
		else if(e.getSource()==_flush){
			showlist();
		}
	}
	//显示航班列表
	public void showlist(){
		text.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t票价\t起飞时间\t飞行时间\r\n");
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[8];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"小时\r\n");
		}
	}
}
