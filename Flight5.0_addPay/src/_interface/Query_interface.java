package _interface;
//���ܣ���ʾ������Ϣ�������������Ϣ��ѯ�������Ϣ�Ĵ��ڣ���ʾ��ѯ���ĺ�����Ϣ���Ƽ��ĺ�����Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
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
	private JLabel jl1 = new JLabel("��ʼ��ַ:");
	private JLabel jl2 = new JLabel("�յ��ַ:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("ȡ��");
	private JButton jb2 = new JButton("��   ѯ");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Query_interface(){
		super("��ѯ��Ʊ��Ϣ");
		super.setIconImage(iocn);
		//��������
		setSize(750,400);
		setLayout(new GridLayout(2,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
    	//��ӿؼ�
		add(jp1);add(jp2);
		jp1.add(textroll);
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll.setBounds(20,20,100,250);
		text.setLineWrap(true); 
		text.setEditable(false); 
		showlist();
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg2.jpg");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        //�������ؼ�����Ϊ͸��
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
    	 //���ð�ť��С
    	jb2.setPreferredSize(new Dimension(250, 50));
        //���ð�ť��ɫ
    	jb2.setBackground(new Color(155,155,155));
    	jl1.setFont(new Font("��Բ",Font.BOLD,20));
    	jl2.setFont(new Font("��Բ",Font.BOLD,20));
    	jb2.setFont(new Font("��Բ",Font.BOLD,30));
		//��ӿؼ�
		jp2.add(jp2_1);jp2.add(jp2_2);
		jp2_1.add(jl1);jp2_1.add(jt1);jp2_1.add(jl2);jp2_1.add(jt2);
		//jp2_2.add(jb1);
		jp2_2.add(jb2);
		//����¼�
		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1)
			dispose();
		else{
			//��ŷ�����Ϣ
			String[] num = new String[ModifyFlight.getMaxNum()+4];
			//�����ʱ������Ϣ��������ʾ�������Ϣ
			String[] temp = new String[8];
			//����Ƽ��������㺽���
			String[] addr1 = new String[ModifyFlight.getMaxNum()];
			//����Ƽ�������յ㺽���
			String[] addr2 = new String[ModifyFlight.getMaxNum()];
			//countΪ�Ƽ����������
			int count = Search.search(jt1.getText(), jt2.getText(),num,ModifyFlight.getMaxNum()+3,addr1,addr2);
			text.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\tƱ��\t���ʱ��\t����ʱ��\r\n");
			int i = 0;
			while(num[i]!=null){//���������һֱѭ��
				//a��b֮����Ƿ���Ҫ����а�
				if(num[i].equals("a")){
					text.append("����Ҫ�����:\r\n");
					i++;
				}
				//b��c֮����������Ҫ��ĺ���
				else if(num[i].equals("b")){
					text.append("������Ҫ�����:\r\n");
					i++;
				}
				//c֮�����յ����Ҫ��ĺ���
				else if(num[i].equals("c")){
					text.append("�յ����Ҫ�����:\r\n");
					i++;
				}
				else{//���������Ϣ
					List.list(Integer.parseInt(num[i]), temp);
					i++;
					text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"Сʱ\r\n");
				}
				
			}//while-end
			//��ʾ�Ƽ��ĺ�����Ϣ
			if(count>0){
				text.append("������Բ�ѯ�Ľ��������,���ǻ��Ƽ�������"+(count)+"�ַ���:\r\n");
				for(int k = 0;k<count;k++){
					text.append("**����"+(k+1)+"*******:\t*****************\t*****************\t*****************\t*****************\t*****************\t*****************\t***************\r\n");
					List.list(Integer.parseInt(addr1[k]), temp);
					text.append("*�������ȳ���:\t\t\t\t\t\t\t                      **\r\n");
					text.append("*"+temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"Сʱ      **\r\n");
					List.list(Integer.parseInt(addr2[k]), temp);
					text.append("*Ȼ��ת��:\t\t\t\t\t\t\t                      **\r\n");
					text.append("*"+temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"Сʱ      **\r\n");
					text.append("*****************\t*****************\t*****************\t*****************\t*****************\t*****************\t*****************\t***************\r\n");
				}
			}
		}//else-end
	}
	//��ʾ������Ϣ
	public void showlist(){
		text.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\tƱ��\t���ʱ��\t����ʱ��\r\n");
		
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[8];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"Сʱ\r\n");
		}
	}
}

