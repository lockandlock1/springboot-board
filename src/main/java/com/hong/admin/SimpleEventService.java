package com.hong.admin;

public class SimpleEventService implements EventService {

    @PerfLogging
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Created an Event");
    }

    @PerfLogging
    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Publish an Event");
    }

    @Override
    public void deleteEvent() {
        System.out.println("Delete an Event");
    }
}
