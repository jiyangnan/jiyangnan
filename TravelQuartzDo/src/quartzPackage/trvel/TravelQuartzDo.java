//测试main函数
//QuartzTest.java
package quartzPackage.trvel;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.dfjh.properties.PropertiesParse;



/**
 * @author jiyangnan
 *@date 2015-10-17 20:58:51
 */
public class TravelQuartzDo {

    /** *//**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	String propertiesName = "poiDB.properties";
    	String min = PropertiesParse.parse("min", propertiesName);
    	String hour = PropertiesParse.parse("hour", propertiesName);
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date d = new Date();
        String returnstr = DateFormat.format(d);    	
    	
        MyJob job = new MyJob();
        String job_name ="11";
        try {
        		System.out.println(returnstr+ "【系统启动】");
                System.out.println("等待执行。。。");
                System.out.println("获取执行时间："+hour +" :"+min);
                QuartzManager.addJob(job_name,job,"0 "+min+" "+hour+" * * ? *"); //每天凌晨1点执行一次

                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                //                QuartzManager.addJob(job_name,job,"0 08 11 * * ? *");

                
//             QuartzManager.removeJob(job_name);
//            QuartzManager.addJob(job_name,job,"10 40 17 * * ? *");
//            Thread.sleep(10000);
//            System.out.println("【修改时间】");
//            QuartzManager.modifyJobTime(job_name,"0/10 * * * * ?");
//            Thread.sleep(20000);
//            System.out.println("【移除定时】");
//            System.out.println("/n【添加定时任务】");
//            QuartzManager.addJob(job_name,job,"0/5 * * * * ?");
            
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
