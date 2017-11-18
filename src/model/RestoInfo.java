package model;

public class RestoInfo
{
    private int restoID;
    private String telephone;
    private String address;

    public RestoInfo(int restoID, String telephone, String address)
    {
        this.restoID = restoID;
        this.telephone = telephone;
        this.address = address;
    }

    public RestoInfo(String telephone, String address)
    {
        this.restoID = -1;
        this.telephone = telephone;
        this.address = address;
    }

    public int getRestoID()
    {
        return restoID;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public String getAddress()
    {
        return address;
    }
}