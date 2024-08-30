package com.airbnb.entity;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_price")
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_in_date")
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_out_date")
    private Date checkOutDate;

    @Column(name = "check_in_time")
    private String checkInTime;

    @Column(name = "check_out_time")
    private String checkOutTime;
//    @Column(name = "total_nights")
//    private int totalNights;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "total_nights", nullable = false)
    private Integer totalNights;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(Integer totalNights) {
        this.totalNights = totalNights;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public PropertyUser getPropertyUser() {
        return propertyUser;
    }

    public void setPropertyUser(PropertyUser propertyUser) {
        this.propertyUser = propertyUser;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @PrePersist
    @PreUpdate
    public void updateDates() {
        Calendar checkInCalendar = Calendar.getInstance();
        checkInCalendar.setTime(checkInDate);

        if ("Morning".equals(checkInTime)) {
            checkInCalendar.set(Calendar.HOUR_OF_DAY, 9);
            checkInCalendar.set(Calendar.MINUTE, 0);
        } else {
            checkInCalendar.set(Calendar.HOUR_OF_DAY, 21);
            checkInCalendar.set(Calendar.MINUTE, 0);
        }

        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.setTime(checkOutDate);

        if ("Morning".equals(checkOutTime)) {
            checkOutCalendar.set(Calendar.HOUR_OF_DAY, 8);
            checkOutCalendar.set(Calendar.MINUTE, 30);
        } else {
            checkOutCalendar.set(Calendar.HOUR_OF_DAY, 20);
            checkOutCalendar.set(Calendar.MINUTE, 30);
        }

        checkInDate = checkInCalendar.getTime();
        checkOutDate = checkOutCalendar.getTime();

        // Calculate total nights
        long diff = checkOutDate.getTime() - checkInDate.getTime();
        totalNights = (int) (diff / (1000 * 60 * 60 * 24));
    }
}
