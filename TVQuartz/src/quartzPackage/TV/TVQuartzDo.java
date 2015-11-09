//测试main函数
//QuartzTest.java
package quartzPackage.TV;


import java.text.SimpleDateFormat;
import java.util.Date;


import com.dfjh.properties.PropertiesParse;




public class TVQuartzDo {

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
//              QuartzManager.addJob(job_name,job,"0 34 18 * * ? *");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
