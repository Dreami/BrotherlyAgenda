package brotherpackage;

import java.time.LocalDate;


public class Task {
    private String name;
    private String description;
    private String dueDate;
    
    public Task(String n, String d, String ld) {
        this.name = n;
        this.description = d;
        this.dueDate = ld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
