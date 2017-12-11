package model;

import model.food.RawItem;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BackUp
{
    private DatabaseModel dbm;
    private String filePath;
    private DateFormat dateFormat;
    private Date date;
    
    public BackUp(String filePath)
    {   
        this.filePath = filePath;
        dbm = new DatabaseModel();
        
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        date = new Date();

        BackUpInventory();
        BackUpXReading();
        BackUpZReading();
    }

    public void BackUpInventory()
    {
        ArrayList<RawItem> data = dbm.getRawItems();
        try
        {
            String fname = "Inventory " + dateFormat.format(date) + ".csv";
            FileWriter fw = new FileWriter(filePath + fname);
            for(RawItem r: data)
            {   
                fw.append(r.getName());
                fw.append(",");
                fw.append(r.getQuantity() + "");
                fw.append("\n");
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    public void BackUpXReading()
    {
        ArrayList<XReading> data = dbm.getXReadAll();
        try
        {
            String fname = "XRead" + dateFormat.format(date) + ".csv";
            FileWriter fw = new FileWriter(filePath + fname);
            for(XReading x: data)
            {   
                fw.append(x.getDate());
                fw.append(",");
                fw.append(x.getUser().getUserLoginName());
                fw.append(",");
                fw.append(x.getTotal() + "");
                fw.append("\n");
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    public void BackUpZReading()
    {
        ArrayList<ZReading> data = dbm.getZReadAll();
        try
        {
            String fname = "ZRead" + dateFormat.format(date) + ".csv";
            FileWriter fw = new FileWriter(filePath + fname);
            for(ZReading z: data)
            {   
                fw.append(z.getDate());
                fw.append(",");
                fw.append(z.getTotal() + "");
                fw.append("\n");
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}