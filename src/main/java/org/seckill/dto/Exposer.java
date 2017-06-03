package org.seckill.dto;

/**
 * Created by Eddie on 2017/6/3.
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;

    private String md5;

    private long seckkillId;

    //系统当前时间
    private long now;
    //开启时间
    private long start;

    private long end;

    public Exposer(boolean exposed, String md5, long seckkillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckkillId = seckkillId;
    }

    public Exposer( boolean exposed,long seckkillId ,long now,long start, long end) {
        this.now = now;
        this.seckkillId=seckkillId;
        this.exposed = exposed;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckkillId) {
        this.exposed = exposed;
        this.seckkillId = seckkillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckkillId() {
        return seckkillId;
    }

    public void setSeckkillId(long seckkillId) {
        this.seckkillId = seckkillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckkillId=" + seckkillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
