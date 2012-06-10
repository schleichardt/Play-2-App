package models;

import java.util.Date;

public class Task {
    public String title;
    public boolean done = false;
    public Date dueDate;
    public User assignedTo;
    public String folder;
}
