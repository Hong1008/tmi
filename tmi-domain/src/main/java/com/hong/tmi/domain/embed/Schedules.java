package com.hong.tmi.domain.embed;

import com.hong.tmi.domain.Schedule;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Schedules {

    @OneToMany(mappedBy = "project")
    private List<Schedule> schedules = new ArrayList<>();

    public long getEndedCount() {
        return schedules.stream().filter(
                schedule -> schedule.getTaskManagement().getTaskStatus().equals(TaskManagement.TaskStatus.END)
        ).count();
    }

    public int getCount(){
        return schedules.size();
    }
}
