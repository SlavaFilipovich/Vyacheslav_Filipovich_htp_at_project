package actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingUtils {


    public static String generateDateXPAth(int amountOfDays){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, amountOfDays);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateInsert = dateFormat.format(date);
        return String.format("//*[contains(@data-date,'%s')]", dateInsert);
    }


}
