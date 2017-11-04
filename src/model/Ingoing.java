package model;

import java.time.LocalDateTime;

public class Ingoing {
    private LocalDateTime inDate;
    private int quantity;
    private String remarks;

    public Ingoing(LocalDateTime inDate, int quantity, String remarks) {
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