package prac.mid01.obsercer;

import java.time.LocalTime;

public interface Observer {
    public void update(LocalTime now);
}
