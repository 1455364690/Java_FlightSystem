package _interface;
//���ܣ�����Ա���棬��ʾ��ɾ�ĵĴ��ڣ���ʾ������Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
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
	private JButton _add = new JButton("����һ������");
	private JButton _del = new JButton("ɾ��һ������");
	private JButton _modi= new JButton("�޸�һ������");
	private JButton _flush = new JButton("ˢ���б�");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Modify_interface(){
		super("�޸Ķ�Ʊ��Ϣ");
		super.setIconImage(iocn);
		//��������
		setSize(750,400);
		setVisible(true);
		setLayout(new GridLayout(2,1));
		//��ӿؼ�
		add(jp1);add(jp2);
		jp1.add(textroll);//������������������
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//���ù�����һֱ��
		textroll.setBounds(20,20,100,220);//���ù�������С
		text.setLineWrap(true); //�������Զ�����
		text.setEditable(false);//�ɱ༭
		showlist();
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg2.jpg");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        //�������ؼ�����Ϊ͸��
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        textroll.setOpaque(false);
        //���þ��Դ�С��λ��
    	setLocation(400, 150);
    	setResizable(false);
    	 //���ð�ť��С
    	_add.setPreferredSize(new Dimension(150, 30));
    	_del.setPreferredSize(new Dimension(150, 30));
    	_modi.setPreferredSize(new Dimension(150, 30));
    	_flush.setPreferredSize(new Dimension(120, 30));
        //���ð�ť��ɫ
    	_add.setBackground(new Color(155,155,255));
    	_add.setFont(new Font("��Բ",Font.BOLD,15));
    	_del.setBackground(new Color(255,155,155));
    	_del.setFont(new Font("��Բ",Font.BOLD,15));
    	_modi.setBackground(new Color(155,155,55));
    	_modi.setFont(new Font("��Բ",Font.BOLD,15));
    	_flush.setBackground(new Color(155,255,155));
    	_flush.setFont(new Font("��Բ",Font.BOLD,15));
		//��Ӱ�ť
		jp2.add(_add);
		jp2.add(_del);
		jp2.add(_modi);
		jp2.add(_flush);
		//��ӹ���
		_add.addActionListener(this);
		_del.addActionListener(this);
		_modi.addActionListener(this);
		_flush.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		//����һ������
		if(e.getSource()==_add){
			Modify_add_interface add = new Modify_add_interface();
			add.setEnabled(true);
			showlist();
		}
		//ɾ��һ������
		else if(e.getSource()==_del){
			String temp1 = JOptionPane.showInputDialog("������Ҫɾ���ĺ������");
			if(temp1!=null)
			ModifyFlight.delFlight(Integer.parseInt(temp1));
			showlist();
		}
		//����һ������
		else if(e.getSource()==_modi){
			Modify_modi_interface mo = new Modify_modi_interface();
			mo.setEnabled(true);
			showlist();
		}
		//ˢ�º����б�
		else if(e.getSource()==_flush){
			showlist();
		}
	}
	//��ʾ�����б�
	public void showlist(){
		text.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\tƱ��\t���ʱ��\t����ʱ��\r\n");
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[8];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"Сʱ\r\n");
		}
	}
}
