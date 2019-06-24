package quxiqi.leetcode.javac;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/6/24 14:27
 **/
public class RangeTest {
    public static void main(String[] args) {
        // 两个时间段列表:
        //      可用时间列表,例: [9:00-12:00],[14:00-20:00],[16:00-21:00]
        //      不可用时间列表,例: [12:00-14:00],[20:00-22:00],[13:00-15:00]
        // 时间间隔 interval分钟
        // 需要把所有可用的时间点和不可用的时间点返回出去。
        // 重叠的可用时间列表需要合并
        List<TimeFromToInfo> enables = new ArrayList<>();
        // 模拟配置的时间段
        enables.add(new TimeFromToInfo(100,true));
        enables.add(new TimeFromToInfo(200,false));
        enables.add(new TimeFromToInfo(300,true));
        enables.add(new TimeFromToInfo(400,false));

        // 模拟禁收收允许
        enables.add(new TimeFromToInfo(150,true));
        enables.add(new TimeFromToInfo(350,false));
        enables.sort(Comparator.comparing(it -> it.timestamp));
        // 时间间隔
        int interval = 50;

        Stack<TimeFromToInfo> stack = new Stack<>();
        List<List<TimeEnableDisableInfo>> enableTimeInfoGroup = new ArrayList<>();
        for (TimeFromToInfo timeData : enables) {
            if (timeData.from) {
                stack.push(timeData);
            } else {
                TimeFromToInfo pop = stack.pop();
                if (stack.empty()) {
                    enableTimeInfoGroup.add(TimeRange.toEnableTimeInfo(interval, pop.timestamp, timeData.timestamp));
                }
            }
        }

        List<TimeRange> disableTimeRanges = new ArrayList<>();
        disableTimeRanges.add(new TimeRange(150, 210));
        disableTimeRanges.add(new TimeRange(150, 250));
        disableTimeRanges.add(new TimeRange(0, 100));

        for (TimeRange disableTimeRange : disableTimeRanges) {
            for (List<TimeEnableDisableInfo> infos : enableTimeInfoGroup) {
                int firstGreaterThanDisableFrom = -1;
                for (int i = 0; i < infos.size(); i++) {
                    TimeEnableDisableInfo info = infos.get(i);
                    if (info.timestamp > disableTimeRange.fromTimestamp) {
                        if (firstGreaterThanDisableFrom == -1) {
                            firstGreaterThanDisableFrom = i;
                        }
                        if (info.timestamp < disableTimeRange.toTimestamp ) {
                            info.enable = false;
                        } else {
                            break;
                        }
                    }
                }
                if (firstGreaterThanDisableFrom - 1 >= 0) {
                    infos.get(firstGreaterThanDisableFrom - 1).enable = false;
                }
            }
        }

        System.out.println(enableTimeInfoGroup);
    }
}

class TimeRange {
    long fromTimestamp;
    long toTimestamp;
    public TimeRange(long fromTimestamp, long toTimestamp) {
        this.fromTimestamp = fromTimestamp;
        this.toTimestamp = toTimestamp;
    }

    public static List<TimeEnableDisableInfo> toEnableTimeInfo(int interval, long fromTimestamp, long toTimestamp) {
        List<TimeEnableDisableInfo> timeInfos = new ArrayList<>((int)(toTimestamp - fromTimestamp)/interval);
        for (long from = fromTimestamp; from <= toTimestamp - interval; from = from + interval) {
            timeInfos.add(new TimeEnableDisableInfo(from, true));
        }
        return timeInfos;
    }

}

class TimeEnableDisableInfo {
    long timestamp;
    boolean enable;
    public TimeEnableDisableInfo(long timestamp, boolean enable) {
        this.timestamp = timestamp;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "{" +
                "timestamp=" + timestamp +
                ", enable=" + enable +
                '}';
    }
}

class TimeFromToInfo {
    long timestamp;
    boolean from;
    public TimeFromToInfo(long timestamp, boolean from) {
        this.timestamp = timestamp;
        this.from = from;
    }
}