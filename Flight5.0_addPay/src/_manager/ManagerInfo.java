package _manager;
//��Ź�����Ϣ�����ݿ���Ϣ�Ⱥ궨����Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.sql.Connection;
import java.sql.Statement;

public class ManagerInfo {
	//59.110.159.191
	//root
	//sj521LJ!!
		public final static String systemN = "root";//����Ա�û���
		public final static String systemP = "root";//����Ա����
		public final static String DB_URL = "jdbc:mysql://127.0.0.1/flightsystem?characterEncoding=utf8&useSSL=false";//���ݿ�·��
		public final static String DB_URL_INIT = "jdbc:mysql://127.0.0.1/mysql?characterEncoding=utf8&useSSL=false";//���ݿ�ԭʼ·��
		public final static String DB_USER = "root";//���ݿ��û���
		public final static String DB_PW = "root";//��������
		public final static String USER_TABLE = "_user";//�û���Ϣ����
		public final static String FLIGHT_TABLE = "_flight";//������Ϣ����
		public final static String USER_FLIGHT_TABLE = "_user_flight";//�û���Ʊ��Ϣ����
		public static Connection conn = null;//����
		public static Statement stmt = null;//���ݿ�״̬
		public final static int MINWAITTIME = 30;//�û����˺���ʱ���ٵȴ�ʱ�䣬��λΪ��
		public final static int MAXWAITTIME = 60*24*3;//�û����˺���ʱ��ȴ�ʱ�䣬��λΪ�֣�����
		public final static int MAXMONEY = 1000000;//�û��˻�������ŵ�Ǯ�޶�
}
