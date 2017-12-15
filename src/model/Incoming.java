package model;

import java.time.LocalDateTime;

import model.food.RawItem;

/**
 * A record of the list of the raw items / ingredients that are delivered to or accepted in the inventory.
 */
public class Incoming {
    private LocalDateTime inDate; // The date when the transaction was recorded.
    private int quantity;
    private String remarks;
    private RawItem rawItem;

    public Incoming(LocalDateTime inDate, int quantity, String remarks, RawItem rawItem) {
        this.inDate = inDate;
        this.quantity = quantity;
        this.remarks = remarks;
        this.rawItem = rawItem;
    }

    public LocalDateTime getInDate() {
        return inDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public RawItem getRawItem() {
        return rawItem;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setRawItem(RawItem rawItem) {
        this.rawItem = rawItem;
    }
}