//测试工作类
//TestJob.java

package quartzPackage.TV;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dfjh.HotPublishByClassMain;
import com.dfjh.MainTest;
import com.dfjh.main.ToPlatform;
import com.dfjh.main.ToTempMain;
import com.dfjh.main.ToTotalMain;


public class MyJob implements Job {
	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);  

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
		System.out.println("★★★★★★★★★★★");
		ToTotalMain tm = new ToTotalMain();
		tm.justDoToTal();
		System.out.println(returnstr+"#########");
		ToTempMain tt = new ToTempMain();
		tt.toTemp();
		System.out.println(returnstr+"@@@@@@@@@@@@");
		ToPlatform tp = new ToPlatform();
		tp.toPlatform();
		System.out.println("%%%%%%%%%%%");
		MainTest mt = new MainTest();
		mt.doPublish();
		System.out.println("#########");
		HotPublishByClassMain hpbcm = new HotPublishByClassMain();
		hpbcm.doInnerPublish();
		System.out.println("所有程序执行完毕！");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*********电视台榜单已生成*********");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
	}

}

