package receipt;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class ReceiptPrinter implements Printable
{
    private String[] parts;

    public void printReceipt(String receipt)
    {
        this.parts = receipt.split("\n");

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        try
        {
            job.print();
        }
        catch(PrinterException ex)
        {
            ex.printStackTrace();
        }

    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException
    {
        if(page > 0)
            return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        g.setFont(new Font("Consolas", 0, 10));

        int y = 0;
        for(String p : parts)
        {
            y = y + 10;
            g.drawString(p, 0, y);
        }

        return PAGE_EXISTS;
    }
}
