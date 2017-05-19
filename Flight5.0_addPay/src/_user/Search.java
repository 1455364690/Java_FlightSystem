package _user;
//�����û��������ʼ��ַ��������Ӧ����Ϣ���Ƽ�����Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;
public class Search extends ManagerInfo{
	public static int search(String address1,String address2,String[] retStr,int num,String[] addr1,String[] addr2){
		//��������յ㶼����Ҫ��ĺ����ID
		String[] tempStr1 = new String[num];
		//���������Ҫ��ĺ����ID
		String[] tempStr2 = new String[num];
		//����յ����Ҫ��ĺ����ID
		String[] tempStr3= new String[num];
		String[] tempStr2_2 = new String[num];
		String[] tempStr3_2= new String[num];
		//����֮��Ϊת�����ʱ��
		long[] addr1Mins = new long[num];
		long[] addr2Mins = new long[num];
		int  top1 = 0,top2 = 0, top3 = 0;
		//�Ƽ�������
		int count = 0;
		//��1�ˣ���ѯ�����յ㶼����Ҫ���վ
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			String quaryAll = "select * from "+FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(quaryAll);
			while(res.next()){
				//�����յ㶼����Ҫ��ĺ���
				if((res.getString("address1")).equals(address1)&&(res.getString("address2")).equals(address2)){
					tempStr1[top1++] = res.getString("id");
				}
				//������Ҫ��ĺ���
				else if((res.getString("address1")).equals(address1)){
					tempStr2[top2] = res.getString("id");
					tempStr2_2[top2] = res.getString("address2");
					addr1Mins[top2] = Integer.parseInt(res.getString("month"))*30*24*60
							+Integer.parseInt(res.getString("day"))*24*60
							+Integer.parseInt(res.getString("hour"))*60
							+Integer.parseInt(res.getString("minute"))
							+Integer.parseInt(res.getString("time"))*60;
					top2++;
				}
				///�յ����Ҫ��ĺ���
				else if((res.getString("address2")).equals(address2)){
					tempStr3[top3] = res.getString("id");
					tempStr3_2[top3] = res.getString("address1");
					addr2Mins[top3] = Integer.parseInt(res.getString("month"))*30*24*60
							+Integer.parseInt(res.getString("day"))*24*60
							+Integer.parseInt(res.getString("hour"))*60
							+Integer.parseInt(res.getString("minute"));
					top3++;
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
		
		//�Ƽ��ĺ��࣬��������Ҫ����յ����յ����Ҫ��������ͬ
		for(int a = 0;a<top2;a++){
			for(int b = 0;b<top3;b++){
				if(tempStr2_2[a].equals(tempStr3_2[b])){
					//����������������ʱ�����Ҫ��
					if((addr2Mins[b]-addr1Mins[a]>=MINWAITTIME)&&(addr2Mins[b]-addr1Mins[a]<=MAXWAITTIME)){
						addr1[count] = tempStr2[a];
						addr2[count] = tempStr3[b];
						count++;
					}
				}
			}
		}
		int j = 1;
		//���ñ�־-��ʶ���ϲ�ѯҪ��
		retStr[0]="a";
		for(int i = 0;i<top1;i++)
			retStr[j++] = tempStr1[i];
		//���ñ�־-��ʶ�������
		retStr[j++] = "b";
		for(int i = 0;i<top2;i++)
			retStr[j++] = tempStr2[i];
		//���ñ�־-��ʶ�����յ�
		retStr[j++] = "c";
		for(int i = 0;i<top3;i++)
			retStr[j++] = tempStr3[i];
		//�Ƽ��ĺ�����
		return count;
	}
	
}
