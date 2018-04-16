package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kail on 2017/10/19.
 */
public class DateUtil {

    /**
     * string 转 Timestamp
     * @param timeStr
     * @return
     */
    public static Timestamp strToTime(String timeStr){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        try {
            Date date = sf.parse(timeStr);
            System.out.println(date);
             time = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            return time;
        }
    }
}
