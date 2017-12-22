package com.spark.schedule;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class ScheduleJobs {


    public final static long SECOND2 = 1 * 1000;
    FastDateFormat fdf2 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /**
     * 固定等待时间 @Scheduled(fixedDelay = 时间间隔 )
     * @throws InterruptedException
     */
    @Scheduled(fixedDelay = SECOND2 * 2)
    public void fixedDelayJob() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("[FixedDelayJob Execute]"+fdf2.format(new Date()));
    }

    /**
     * 固定间隔时间 @Scheduled(fixedRate = 时间间隔 )
     */


    public final static long SECOND1 = 1 * 1000;
    FastDateFormat fdf1 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    @Scheduled(fixedRate = SECOND1 * 4)
    public void fixedRateJob() {
        System.out.println("[FixedRateJob Execute]"+fdf1.format(new Date()));
    }


    /**
     *  Corn表达式 @Scheduled(cron = Corn表达式)
     */
    public final static long SECOND = 1 * 1000;
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    @Scheduled(cron = "0/4 * * * * ?")
    public void cronJob() {
        System.out.println("[CronJob Execute]"+fdf.format(new Date()));
    }

}
