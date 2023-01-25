package rabbitmq.domain;

import java.util.Date;

public class TaskDTO
{
    private String taskId;
    private String email;
    private String statusName;
    private String priority;
    private String taskName;
    private String spaceName;
    private String taskDescription;
    private Date startDate;
    private Date endDate;

    public TaskDTO()
    {

    }

    public TaskDTO(String taskId, String email, String statusName, String priority, String taskName, String spaceName, String taskDescription, Date startDate, Date endDate) {
        this.taskId = taskId;
        this.email = email;
        this.statusName = statusName;
        this.priority = priority;
        this.taskName = taskName;
        this.spaceName = spaceName;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskId='" + taskId + '\'' +
                ", email='" + email + '\'' +
                ", statusName='" + statusName + '\'' +
                ", priority='" + priority + '\'' +
                ", taskName='" + taskName + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
