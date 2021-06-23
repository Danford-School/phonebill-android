package edu.pdx.cs410J.danford.phonebill;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCall extends PhoneBillDataBase implements Serializable {

    private String Customer;
    private String Caller;
    private String Callee;
    private String StartDate;
    private String StartTime;
    private String StartAMPM;
    private String EndDate;
    private String EndTime;
    private String EndAMPM;
    private String StartDateAndTime;
    private String EndDateAndTime;
    private Date DateFormattedStart;
    private Date DateFormattedEnd;

    public PhoneCall(String Customer, String caller, String callee, String startDate, String startTime, String startTimeAMPM, String endDate, String endTime, String endTimeAMPM) throws ParseException {
        this.Customer = Customer;
        this.Caller = phoneNumberCheck(caller);
        this.Callee = phoneNumberCheck(callee);
        this.StartDateAndTime = fakeDateString(dateChecker(startDate), timeChecker(startTime), checkAMPM(startTimeAMPM));
        this.EndDateAndTime = fakeDateString(dateChecker(endDate), timeChecker(endTime), checkAMPM(endTimeAMPM));
        this.DateFormattedEnd = makeDateObjectFromString(this.StartDateAndTime);
        this.DateFormattedEnd = makeDateObjectFromString(this.EndDateAndTime);
    }

    public String getStartDateAndTime() { return this.StartDateAndTime; }

    public String getEndDateAndTime() { return this.EndDateAndTime; }

    public String getCaller() { return this.Caller; }

    public String getCallee() { return this.Callee; }

    public String getCustomer() { return this.Customer; }

    public Date getStartDate() { return this.DateFormattedStart; }

    public Date getEndDate() { return this.DateFormattedEnd; }

    private Date makeDateObjectFromString(String stringDate) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy, hh:mm a");
        Date date = formatter.parse(stringDate);
        return date;
    }

    private String fakeDateString(String date, String time, String AMPM) {
        return date.concat(", ").concat(time).concat(" ").concat(AMPM);
    }

    private static String phoneNumberCheck(String aPhoneNumber) {
        if (!aPhoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
            System.out.println("\nThe phone number is not in the correct format.\nPlease use ###-###-####\n");
            throw new IllegalArgumentException();
        }
        return aPhoneNumber;
    }

    private static String dateChecker(String date) {
        if(!date.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")) {
            System.out.println("\nThe date is not valid.\nPlease use the form mm/dd/yyyy, m/dd/yyyy, mm/d/yyyy, or m/d/yyyy\n");
            throw new IllegalArgumentException();
        }
        return date;
    }

    private static String timeChecker(String time) {
        if (!time.matches("(1[012]|[1-9]):[0-5][0-9]")) {
            System.out.println("\nThe time is not valid.\nPlease use the form hh:mm or h:mm\n");
            throw new IllegalArgumentException();
        }
        return time;
    }

    private static String checkAMPM(String AMPM) {
        if(AMPM.contains("AM") | AMPM.contains("am") | AMPM.contains("PM") | AMPM.contains("pm")) {
            return AMPM.toLowerCase();
        } else {
            System.err.println("AM or PM required (Case insensitive)");
            throw new IllegalArgumentException();
        }
    }

    public String phoneCallToOutput() {
        return this.Customer + " at " + this.Caller + " called " + this.Callee +
                " starting on " + this.StartDateAndTime +
                ", and ending on " + this.EndDateAndTime + ".";
    }


}
