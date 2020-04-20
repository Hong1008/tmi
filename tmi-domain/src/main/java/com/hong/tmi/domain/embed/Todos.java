package com.hong.tmi.domain.embed;

import com.hong.tmi.domain.Todo;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Todos {

    @OneToMany(mappedBy = "project")
    private List<Todo> todos = new ArrayList<>();

    public long getEndedCount() {
        return todos.stream().filter(todo -> todo.getTaskManagement().getTaskStatus().equals(TaskManagement.TaskStatus.END)).count();
    }

    public int getCount(){
        return todos.size();
    }
}
