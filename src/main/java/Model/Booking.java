package Model;

import java.util.Objects;

public class Booking {
    private int id;
    private int passenger_id;
    private String serial_number;

    public Booking() {
    }

    public Booking(int passenger_id, String serial_number) {
        this.passenger_id = passenger_id;
        this.serial_number = serial_number;
    }

    public Booking(int id, int passenger_id, String serial_number) {
        this.id = id;
        this.passenger_id = passenger_id;
        this.serial_number = serial_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return getId() == booking.getId() && getPassenger_id() == booking.getPassenger_id() && Objects.equals(getSerial_number(), booking.getSerial_number());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPassenger_id(), getSerial_number());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passenger_id=" + passenger_id +
                ", serial_number='" + serial_number + '\'' +
                '}';
    }
}
