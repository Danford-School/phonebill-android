package edu.pdx.cs410J.danford.phonebill;

import java.util.Comparator;

public class Sorter implements Comparator<PhoneCall> {
    @Override
    public int compare(PhoneCall o, PhoneCall t1) {
        int result = o.getStartDateAndTime().compareTo(t1.getStartDateAndTime());
        if(result == 0) {
            result = o.getCaller().compareTo(t1.getCaller());
        }
        return result;
    }
}
