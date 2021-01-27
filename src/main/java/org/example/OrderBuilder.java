package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrderBuilder {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private static final Object obj = new Object();

    public static Order build(String s) {
        String[] v = s.split(",");
        if (v.length != 14) {
            System.err.println("Invalid row <" + s + "> fields are <" + v.length + "> instead of <14>");
            return new Order();
        }
        try {
            Order order = new Order();

            order.setRegion(v[0]);
            order.setCountry(v[1]);
            order.setItemType(v[2]);
            order.setSalesChannel(v[3]);
            order.setOrderPriority(v[4]);
            order.setOrderDate(toCal(v[5]));
            order.setOrderId(Long.parseLong(v[6]));
            order.setShipDate(toCal(v[7]));
            order.setUnitsSold(Integer.parseInt(v[8]));
            order.setUnitPrice(Double.parseDouble(v[9]));
            order.setUnitCost(Double.parseDouble(v[10]));
            order.setTotalRevenue(Double.parseDouble(v[11]));
            order.setTotalCost(Double.parseDouble(v[12]));
            order.setTotalProfit(Double.parseDouble(v[13]));

            return order;
        } catch (NumberFormatException | ParseException e) {
            System.err.println("Invalid row <" + s + "> invalid date");
        }
        return new Order();
    }

    private static Calendar toCal(String s) throws ParseException {
        synchronized (obj) {
            return toCal(dateFormat.parse(s));
        }
    }

    private static Calendar toCal(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }
}
