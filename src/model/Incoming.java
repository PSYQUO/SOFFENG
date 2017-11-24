package model;

import java.time.LocalDateTime;

/**
 * A record of the list of the raw items / ingredients that are delivered to or accepted in the inventory.
 */
public class Incoming {
    private LocalDateTime inDate; // The date when the transaction was recorded.
    private int quantity;
    private String remarks;

    public Incoming(LocalDateTime inDate, int quantity, String remarks) {
        this.inDate = inDate;
        this.quantity = quantity;
        this.remarks = remarks;
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

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}