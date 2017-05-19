package _interface;
//功能：显示航班信息，根据输入的信息查询航班的信息的窗口，显示查询到的航班信息和推荐的航班信息
//作者：孙加辉，时间：2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import _flight.List;
import _manager.ModifyFlight;
import _user.Search;
public class Query_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextArea text = new JTextArea(10,63);
	private JScrollPane textroll = new JScrollPane(text);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp2_1 = new JPanel();
	private JPanel jp2_2 = new JPanel();
	private JLabel jl1 = new JLabel("起始地址:");
	private JLabel jl2 = new JLabel("终点地址:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("取消");
	private JButton jb2 = new JButton("查   询");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Query_interface(){
		super("查询订票信息");
		super.setIconImage(iocn);
		//界面属性
		setSize(750,400);
		setLayout(new GridLayout(2,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
    	//添加控件
		add(jp1);add(jp2);
		jp1.add(textroll);
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll.setBounds(20,20,100,250);
		text.setLineWrap(true); 
		text.setEditable(false); 
		showlist();
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg2.jpg");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        //将其他控件设置为透明
    	text.setOpaque(false);
    	textroll.setOpaque(false);
    	jp1.setOpaque(false);
    	jp2.setOpaque(false);
    	jp2_1.setOpaque(false);
    	jp2_2.setOpaque(false);
    	jl1.setOpaque(false);
    	jl2.setOpaque(false);
    	jt1.setOpaque(false);
    	jt2.setOpaque(false);    	
    	 //设置按钮大小
    	jb2.setPreferredSize(new Dimension(250, 50));
        //设置按钮颜色
    	jb2.setBackground(new Color(155,155,155));
    	jl1.setFont(new Font("幼圆",Font.BOLD,20));
    	jl2.setFont(new Font("幼圆",Font.BOLD,20));
    	jb2.setFont(new Font("幼圆",Font.BOLD,30));
		//添加控件
		jp2.add(jp2_1);jp2.add(jp2_2);
		jp2_1.add(jl1);jp2_1.add(jt1);jp2_1.add(jl2);jp2_1.add(jt2);
		//jp2_2.add(jb1);
		jp2_2.add(jb2);
		//添加事件
		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1)
			dispose();
		else{
			//存放返回信息
			String[] num = new String[ModifyFlight.getMaxNum()+4];
			//存放临时航班信息，用来显示航班的信息
			String[] temp = new String[8];
			//存放推荐航班的起点航班号
			String[] addr1 = new String[ModifyFlight.getMaxNum()];
			//存放推荐航班的终点航班号
			String[] addr2 = new String[ModifyFlight.getMaxNum()];
			//count为推荐航班的数量
			int count = Search.search(jt1.getText(), jt2.getText(),num,ModifyFlight.getMaxNum()+3,addr1,addr2);
			text.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t票价\t起飞时间\t飞行时间\r\n");
			int i = 0;
			while(num[i]!=null){//如果不空则一直循环
				//a和b之间的是符号要求的行班
				if(num[i].equals("a")){
					text.append("符合要求的有:\r\n");
					i++;
				}
				//b和c之间是起点符号要求的航班
				else if(num[i].equals("b")){
					text.append("起点符合要求的有:\r\n");
					i++;
				}
				//c之后是终点符合要求的航班
				else if(num[i].equals("c")){
					text.append("终点符合要求的有:\r\n");
					i++;
				}
				else{//输出航班信息
					List.list(Integer.parseInt(num[i]), temp);
					i++;
					text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"小时\r\n");
				}
				
			}//while-end
			//显示推荐的航班信息
			if(count>0){
				text.append("如果您对查询的结果不满意,我们还推荐您以下"+(count)+"种方案:\r\n");
				for(int k = 0;k<count;k++){
					text.append("**方案"+(k+1)+"*******:\t*****************\t*****************\t*****************\t*****************\t*****************\t*****************\t***************\r\n");
					List.list(Integer.parseInt(addr1[k]), temp);
					text.append("*您可以先乘坐:\t\t\t\t\t\t\t                      **\r\n");
					text.append("*"+temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"小时      **\r\n");
					List.list(Integer.parseInt(addr2[k]), temp);
					text.append("*然后转乘:\t\t\t\t\t\t\t                      **\r\n");
					text.append("*"+temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"小时      **\r\n");
					text.append("*****************\t*****************\t*****************\t*****************\t*****************\t*****************\t*****************\t***************\r\n");
				}
			}
		}//else-end
	}
	//显示航班信息
	public void showlist(){
		text.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t票价\t起飞时间\t飞行时间\r\n");
		
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[8];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"小时\r\n");
		}
	}
}

