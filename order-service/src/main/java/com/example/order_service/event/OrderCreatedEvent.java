package com.example.order_service.event;

public class OrderCreatedEvent {
    private Long orderId;
    private String customerName;
    private String itemName;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Long orderId,
                             String customerName,
                             String itemName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.itemName = itemName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
