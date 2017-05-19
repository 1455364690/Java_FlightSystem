package _manager;
//存放管理信息，数据库信息等宏定义信息
//作者：孙加辉，时间：2017/05/07
import java.sql.Connection;
import java.sql.Statement;

public class ManagerInfo {
	//59.110.159.191
	//root
	//sj521LJ!!
		public final static String systemN = "root";//管理员用户名
		public final static String systemP = "root";//管理员密码
		public final static String DB_URL = "jdbc:mysql://127.0.0.1/flightsystem?characterEncoding=utf8&useSSL=false";//数据库路径
		public final static String DB_URL_INIT = "jdbc:mysql://127.0.0.1/mysql?characterEncoding=utf8&useSSL=false";//数据库原始路径
		public final static String DB_USER = "root";//数据库用户名
		public final static String DB_PW = "root";//数据密码
		public final static String USER_TABLE = "_user";//用户信息表名
		public final static String FLIGHT_TABLE = "_flight";//航班信息表名
		public final static String USER_FLIGHT_TABLE = "_user_flight";//用户购票信息表名
		public static Connection conn = null;//连接
		public static Statement stmt = null;//数据库状态
		public final static int MINWAITTIME = 30;//用户换乘航班时最少等待时间，单位为分
		public final static int MAXWAITTIME = 60*24*3;//用户换乘航班时最长等待时间，单位为分，三天
		public final static int MAXMONEY = 1000000;//用户账户中最多存放的钱限额
}
