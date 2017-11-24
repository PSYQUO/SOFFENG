package model;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.FileWriter;

public class BackUp
{
    private DatabaseModel dbm;
    private String filePath;
    private String date = "12-12-12"; // TODO must be LocalDateTime
    
    public BackUp(String filePath)
    {
        dbm = new DatabaseModel();
        this.filePath = filePath;
        BackUpInventory();
        BackUpXReading();
        BackUpZReading();
    }

    public void BackUpInventory()
    {
        ArrayList<RawItem> data = dbm.getRawItems();
        try
        {
            String fname = "Inventory " + date + ".csv";
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
            String fname = "XRead.csv";
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
            String fname = "ZRead.csv";
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