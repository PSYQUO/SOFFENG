package model;

import java.time.LocalDateTime;

public class Outgoing {
    private LocalDateTime outDate;
    private int quantity;
    private String remarks;

    public Outgoing(LocalDateTime outDate, int quantity, String remarks) {
        this.outDate = outDate;
        this.quantity = quantity;
        this.remarks = remarks;
    }

    public LocalDateTime getOutDate() {
        return outDate;
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