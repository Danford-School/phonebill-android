package edu.pdx.cs410J.danford.phonebill;


import java.util.HashMap;

public class PhoneBillDataBase {
    public static final int ADD_PHONECALL_RESULT = 0;
    public HashMap<String, PhoneBill> PhoneBillList = new HashMap<>();

    public PhoneBill getPhoneBill(String name) {
        PhoneBill bill = PhoneBillList.get(name);
        if(bill == null) {
            bill = new PhoneBill(name);
        }
        return bill;
    }

    public void addPhoneBillToDB(PhoneBill bill) {
        if(PhoneBillList.containsKey(bill.customer)) {
            PhoneBillList.replace(bill.customer, bill);
        } else {
            PhoneBillList.put(bill.customer, bill);
        }
    }

    public void removePhoneBill(String name) {
        PhoneBillList.remove(name);
    }
}
