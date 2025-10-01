package choong.board._core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    //날짜시간
    public static String format(LocalDateTime time) {
        if (time == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        return time.format(formatter);
    }

    //날짜
    public static String formatDate(LocalDateTime time) {
        if (time == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        return time.format(formatter);
    }
}
