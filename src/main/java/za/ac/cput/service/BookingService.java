package za.ac.cput.service;

import za.ac.cput.domain.Booking;

import java.util.List;

public interface BookingService extends IService <Booking, String> {
    public List<Booking> getall();
}
