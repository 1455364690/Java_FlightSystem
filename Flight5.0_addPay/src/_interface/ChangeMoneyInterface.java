package _interface;
//���ܣ��ҵ�Ǯ������ʾ�û�Ǯ����Ϣ���û���ֵ�����ܣ���ʾ�û�������Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import _user.GetMoney;
import _user.GetID;
import _user.ChangeMoney;
import _manager.DataCheck;
public class ChangeMoneyInterface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;//���к�
	private static String name;//����û���
	private static String id;//����û�ID
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JLabel jl1 = new JLabel("��ֵ���:");
	private JLabel jl2 = new JLabel("�����:");
	private JTextArea text = new JTextArea(5,40);
	private JScrollPane textroll = new JScrollPane(text);
	private JTextField jt1 = new JTextField(10);
	private JTextField jt2 = new JTextField(10);
	private JButton jb1 = new JButton("��  ֵ");
	private JButton jb2 = new JButton("��  ��");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public ChangeMoneyInterface(String _name){
		super("�ҵ�Ǯ��");
		super.setIconImage(iocn);
		//���û���������
		name = _name;
		//���÷����������û�����ȡID
		id = GetID.get(_name)+"";
		//���ý�������
		setLocation(400, 150);
		setSize(500,400);
		setResizable(false);
		setVisible(true);
		//��ӿؼ�
		setLayout(new GridLayout(2,1));
		add(jp1);add(jp2);
		jp2.setLayout(new GridLayout(2,1));
		jp2.add(jp3);jp2.add(jp4);
		jp3.add(jl1);jp3.add(jt1);jp3.add(jb1);
		jp4.add(jl2);jp4.add(jt2);jp4.add(jb2);
		jp1.add(textroll);
		//���ò�����Ϣչʾ�����������
		textroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll.setBounds(20,20,40,40);
		text.setLineWrap(true); 
		text.setEditable(false); 
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		//�������ؼ�����Ϊ͸��
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jl1.setOpaque(false);
		jl2.setOpaque(false);
		//���ð�ť��С
		jb1.setPreferredSize(new Dimension(100, 30));
		jb2.setPreferredSize(new Dimension(100, 30));
		//���ð�ť��ɫ����������
		jb1.setBackground(new Color(155,155,155));
		jb1.setFont(new Font("��Բ",Font.BOLD,15));
		jb2.setBackground(new Color(155,155,155));
		jb2.setFont(new Font("��Բ",Font.BOLD,15));
		//���ñ�ǩ�ַ�����
		jl1.setFont(new Font("�����п�",Font.BOLD,20));
		jl2.setFont(new Font("�����п�",Font.BOLD,20));
		//��Ӽ�����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		//����û���Ϣ
		text.setText("�𾴵��û�"+name+":\r\n");
		text.append("����ʣ����Ϊ:"+GetMoney.getMoney(id)+"Ԫ!!\r\n");
	}
	public void actionPerformed(ActionEvent e){
		//����
		if(e.getSource()==jb1){
			//�����û����ݵļ�鷽��������û�����������Ƿ���Ϲ淶
			if(DataCheck.check(jt1.getText())){
				//�������ڸ�ʽ
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬��ʾ�û�����ʱ��
				text.append(df.format(new Date())+"\r\n");
				//���ô�Ǯ�ķ���
				if(ChangeMoney.addMoney(Integer.parseInt(jt1.getText()), id)){
					//��ʾ�û�������Ϣ
					text.append("������"+jt1.getText()+"Ԫ,ʣ����Ϊ"+GetMoney.getMoney(id)+"Ԫ!!\r\n");
				}else{
					text.append("��Ǯ���е�Ǯ�����޶��ֵʧ��!!!\r\n");
				}
				
				
			}
			else{
				text.append("��������\r\n");
			}
			//��Ǯ������ɺ��ı������
			jt1.setText("");
			jt2.setText("");
		}
		//ȡǮ����
		else if(e.getSource()==jb2){
			//�����û����ݵļ�鷽��������û�����������Ƿ���Ϲ淶
			if(DataCheck.check(jt2.getText())){
				//�������ڸ�ʽ
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬���û�������Ϣ�ı������
				text.append(df.format(new Date())+"\r\n");
				//����ȡǮ�ķ���
				if(ChangeMoney.delMoney(Integer.parseInt(jt2.getText()), id)){
				text.append("��ȡ��"+jt2.getText()+"Ԫ,ʣ����Ϊ"+GetMoney.getMoney(id)+"Ԫ!!\r\n");
				}else{
					text.append("����,���ʧ��!!\r\n");
				}
			}
			else{
				text.append("��������!!ʣ����Ϊ"+GetMoney.getMoney(id)+"Ԫ!!\r\n");
			}
			//ȡǮ�������ı������
			jt1.setText("");
			jt2.setText("");
		}
	}
//	public static void main(String[] args){
//		ChangeMoneyInterface t = new ChangeMoneyInterface("s123123");
//	}
}
