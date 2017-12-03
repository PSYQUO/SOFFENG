package receipt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;

public class ReceiptPrinter implements Printable{
    
    private String[] parts;
    
    public ReceiptPrinter(){

    }

    public void printado(String resibo){

        this.parts = resibo.split("\n");

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        //boolean ok = job.printDialog();
        //if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
       // }
    }
    
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException
    {

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        g.setFont(new Font("Consolas", 0, 10));
        
        /* Now we perform our rendering */
        //g.drawString("Hello world!", 125, 125);
        //g.drawString("DLSU!", 125, 135);
        //g.drawString("Animo La Salle", 125, 145);
        //g.drawString("Allahu Akhbar!!!", 125, 155);

        int y = 0;
        for(String p : parts)
        {
            y = y + 10;
            g.drawString(p, 0, y);
        }

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
}
