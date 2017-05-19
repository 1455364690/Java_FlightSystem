package _interface;
//功能：实现增删改的窗口，显示航班信息
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import _flight.List;
import _manager.ModifyFlight;
public class Modify_interface extends JFrame implements ActionListener{
	private JTextArea text = new JTextArea(10,60);
	private JScrollPane textroll = new JScrollPane(text);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private Button _add = new Button("增加一个航班");
	private Button _del = new Button("删除一个航班");
	private Button _modi= new Button("修改一个航班");
	private Button _flush = new Button("刷新列表");
	public Modify_interface(){
		super("修改订票信息");
		setLayout(new GridLayout(2,1));
		add(jp1);add(jp2);
		jp1.add(textroll);//将滚动条加入容器中
//		textroll.setVerticalScrollBarPolicy( 
//				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置滚动条一直在
		textroll.setBounds(20,20,100,200);//设置滚动条大小

		text.setLineWrap(true); //滚动条自动换行
		text.setEditable(false);//可编辑
		
		showlist();
			
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
		setSize(700,400);
		setVisible(true);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						dispose();
					}
				});
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==_add){
			Modify_add_interface add = new Modify_add_interface();
			showlist();
		}else if(e.getSource()==_del){
			String temp1 = JOptionPane.showInputDialog("请输入要删除的航班序号");
			ModifyFlight.delFlight(Integer.parseInt(temp1));
			showlist();
		}
		else if(e.getSource()==_modi){
			Modify_modi_interface mo = new Modify_modi_interface();
			showlist();
		}else if(e.getSource()==_flush){
			showlist();
		}
	}
	public void showlist(){
		text.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t起飞时间\t飞行时间\r\n");
		
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[7];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"小时\r\n");
		}
	}
}
