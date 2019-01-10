package com.sportsgen.Organiser.Models;

import android.graphics.Bitmap;

import com.sportsgen.Organiser.Fragments.EntryFeesFragment;

import java.io.Serializable;
import java.util.List;

public class CreateEventData implements Serializable {
    String Eventtype;
    String Eventname;
    String EventSport;
    String EventDescription;
    String Venue;
    String Venue_lat;
    String Venue_lng;
    Boolean is_multiple_days;
    String single_date;
    String from_date;
    String to_date;
    String from_time;
    String to_time;
    Boolean is_paid;
    List<ModelEntryFees> list_entry_fees;
    Bitmap img_banner;

    public String getEventtype() {
        return Eventtype;
    }

    public void setEventtype(String eventtype) {
        Eventtype = eventtype;
    }

    public String getEventname() {
        return Eventname;
    }

    public void setEventname(String eventname) {
        Eventname = eventname;
    }

    public String getEventSport() {
        return EventSport;
    }

    public void setEventSport(String eventSport) {
        EventSport = eventSport;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getVenue_lat() {
        return Venue_lat;
    }

    public void setVenue_lat(String venue_lat) {
        Venue_lat = venue_lat;
    }

    public String getVenue_lng() {
        return Venue_lng;
    }

    public void setVenue_lng(String venue_lng) {
        Venue_lng = venue_lng;
    }

    public Boolean getIs_multiple_days() {
        return is_multiple_days;
    }

    public void setIs_multiple_days(Boolean is_multiple_days) {
        this.is_multiple_days = is_multiple_days;
    }

    public String getSingle_date() {
        return single_date;
    }

    public void setSingle_date(String single_date) {
        this.single_date = single_date;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public Boolean getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(Boolean is_paid) {
        this.is_paid = is_paid;
    }

    public List<ModelEntryFees> getList_entry_fees() {
        return list_entry_fees;
    }

    public void setList_entry_fees(List<ModelEntryFees> list_entry_fees) {
        this.list_entry_fees = list_entry_fees;
    }

    public Bitmap getImg_banner() {
        return img_banner;
    }

    public void setImg_banner(Bitmap img_banner) {
        this.img_banner = img_banner;
    }

    public interface OnDataEntryListener extends Serializable {
        void setEventType(String Eventtype);
        void setEventName(String EventName);
        void setEventSport(String EventSport);
        void setEventDescription(String EventDescription);
    }
}
