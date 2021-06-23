package edu.pdx.cs410J.danford.phonebill;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class PhoneBill extends PhoneBillDataBase {
    public String customer;
    public Collection<PhoneCall> calls = new TreeSet<>(new Sorter());

    public PhoneBill(String customer){
        this.customer = customer;
    }

    public void addPhoneCall(PhoneCall call) {
        this.calls.add(call);
    }

    public String getCustomer() {
        return this.customer;
    }

    public Collection<PhoneCall> getPhoneCalls() { return this.calls; }

    public PhoneBill billSearch(PhoneBill bill, Date fromThisTime, Date toThisTime) {
        PhoneBill returnMe = new PhoneBill(bill.customer);
        for (PhoneCall call : bill.calls) {
            if (call.getStartDate().after(fromThisTime) && call.getStartDate().before(toThisTime)) {
                returnMe.addPhoneCall(call);
            }
        }
        return returnMe;
    }
}
