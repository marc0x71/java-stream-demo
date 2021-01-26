package org.example;

import lombok.Data;
import lombok.ToString;

import java.util.Calendar;

@Data
@ToString
public class Order {

    private String region;
    private String country;
    private String itemType;
    private String salesChannel;
    private String orderPriority;
    private Calendar orderDate;
    private long orderId;
    private Calendar shipDate;
    private int unitsSold;
    private double unitPrice;
    private double unitCost;
    private double totalRevenue;
    private double totalCost;
    private double totalProfit;

}
