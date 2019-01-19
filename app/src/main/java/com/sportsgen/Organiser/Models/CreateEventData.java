package com.sportsgen.Organiser.Models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.sportsgen.Organiser.Fragments.EntryFeesFragment;

import java.io.Serializable;
import java.util.List;

public class CreateEventData implements Parcelable {
    String Eventtype;
    String Eventname;
    String EventSport;
    String EventDescription;

    String Venuename;
    String Venueplace;
    String Venueaddress;
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

    Boolean is_limited_reg;
    Boolean is_24_hours;
    Boolean is_custom_tshirts;
    Boolean is_custom_certi;
    String reg_no;
    String hrs_before;

    protected CreateEventData(Parcel in) {
        Eventtype = in.readString();
        Eventname = in.readString();
        EventSport = in.readString();
        EventDescription = in.readString();
        Venuename = in.readString();
        Venueplace = in.readString();
        Venueaddress = in.readString();
        Venue_lat = in.readString();
        Venue_lng = in.readString();
        byte tmpIs_multiple_days = in.readByte();
        is_multiple_days = tmpIs_multiple_days == 0 ? null : tmpIs_multiple_days == 1;
        single_date = in.readString();
        from_date = in.readString();
        to_date = in.readString();
        from_time = in.readString();
        to_time = in.readString();
        byte tmpIs_paid = in.readByte();
        is_paid = tmpIs_paid == 0 ? null : tmpIs_paid == 1;
        byte tmpIs_limited_reg = in.readByte();
        is_limited_reg = tmpIs_limited_reg == 0 ? null : tmpIs_limited_reg == 1;
        byte tmpIs_24_hours = in.readByte();
        is_24_hours = tmpIs_24_hours == 0 ? null : tmpIs_24_hours == 1;
        byte tmpIs_custom_tshirts = in.readByte();
        is_custom_tshirts = tmpIs_custom_tshirts == 0 ? null : tmpIs_custom_tshirts == 1;
        byte tmpIs_custom_certi = in.readByte();
        is_custom_certi = tmpIs_custom_certi == 0 ? null : tmpIs_custom_certi == 1;
        reg_no = in.readString();
        hrs_before = in.readString();
        img_banner = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Eventtype);
        dest.writeString(Eventname);
        dest.writeString(EventSport);
        dest.writeString(EventDescription);
        dest.writeString(Venuename);
        dest.writeString(Venueplace);
        dest.writeString(Venueaddress);
        dest.writeString(Venue_lat);
        dest.writeString(Venue_lng);
        dest.writeByte((byte) (is_multiple_days == null ? 0 : is_multiple_days ? 1 : 2));
        dest.writeString(single_date);
        dest.writeString(from_date);
        dest.writeString(to_date);
        dest.writeString(from_time);
        dest.writeString(to_time);
        dest.writeByte((byte) (is_paid == null ? 0 : is_paid ? 1 : 2));
        dest.writeByte((byte) (is_limited_reg == null ? 0 : is_limited_reg ? 1 : 2));
        dest.writeByte((byte) (is_24_hours == null ? 0 : is_24_hours ? 1 : 2));
        dest.writeByte((byte) (is_custom_tshirts == null ? 0 : is_custom_tshirts ? 1 : 2));
        dest.writeByte((byte) (is_custom_certi == null ? 0 : is_custom_certi ? 1 : 2));
        dest.writeString(reg_no);
        dest.writeString(hrs_before);
        dest.writeParcelable(img_banner, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CreateEventData> CREATOR = new Creator<CreateEventData>() {
        @Override
        public CreateEventData createFromParcel(Parcel in) {
            return new CreateEventData(in);
        }

        @Override
        public CreateEventData[] newArray(int size) {
            return new CreateEventData[size];
        }
    };

    public Boolean getIs_limited_reg() {
        return is_limited_reg;
    }

    public void setIs_limited_reg(Boolean is_limited_reg) {
        this.is_limited_reg = is_limited_reg;
    }

    public Boolean getIs_24_hours() {
        return is_24_hours;
    }

    public void setIs_24_hours(Boolean is_24_hours) {
        this.is_24_hours = is_24_hours;
    }

    public Boolean getIs_custom_tshirts() {
        return is_custom_tshirts;
    }

    public void setIs_custom_tshirts(Boolean is_custom_tshirts) {
        this.is_custom_tshirts = is_custom_tshirts;
    }

    public Boolean getIs_custom_certi() {
        return is_custom_certi;
    }

    public void setIs_custom_certi(Boolean is_custom_certi) {
        this.is_custom_certi = is_custom_certi;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getHrs_before() {
        return hrs_before;
    }

    public void setHrs_before(String hrs_before) {
        this.hrs_before = hrs_before;
    }

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

    public String getVenuename() {
        return Venuename;
    }

    public void setVenuename(String venuename) {
        Venuename = venuename;
    }

    public String getVenue_lat() {
        return Venue_lat;
    }

    public String getVenueplace() {
        return Venueplace;
    }

    public void setVenueplace(String venueplace) {
        Venueplace = venueplace;
    }

    public String getVenueaddress() {
        return Venueaddress;
    }

    public void setVenueaddress(String venueaddress) {
        Venueaddress = venueaddress;
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


    public CreateEventData() {
    }


    public interface OnDataEntryListener {
        void setEventType(String Eventtype);

        void setEventName(String EventName);

        void setEventSport(String EventSport);

        void setEventDescription(String EventDescription);

        void setVenuename(String Venuename);

        void setVenueplace(String VenuePlace);

        void setVenueaddress(String Venueaddress);

        void setVenueLat(String VenueLat);

        void setVenueLng(String VenueLng);

        void set_is_multiple_days(Boolean is_multiple_days);

        void set_single_date(String single_date);

        void setFrom_date(String from_date);

        void set_to_date(String to_date);

        void set_from_time(String from_time);

        void set_to_time(String to_time);

        void set_is_paid(Boolean is_paid);

        void set_list_of_categories(List<ModelEntryFees> mlist);

        void set_is_ltd_reg(Boolean is_ltd_reg);

        void set_is_hrs(Boolean is_hrs);

        void set_is_custom_jersey(Boolean is_custom_jersey);

        void set_is_custom_certi(Boolean is_custom_certi);

        void set_ltd_hrs(String ltd_hrs);

        void set_ltd_reg(String ltd_reg);

    }
}
