package rabbitmq.domain;

public class NotificationDTO {

    private int notificationId;
    private String notification;

    public NotificationDTO() {
    }

    public NotificationDTO(int notificationId, String notification) {
        this.notificationId = notificationId;
        this.notification = notification;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "notificationId=" + notificationId +
                ", notification='" + notification + '\'' +
                '}';
    }
}
