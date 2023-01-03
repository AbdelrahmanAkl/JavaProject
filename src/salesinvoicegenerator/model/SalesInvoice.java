
package salesinvoicegenerator.model;

import java.util.ArrayList;


public class SalesInvoice {
    
    private int numb;
    private String datee;
    private String nameOfCustomer;
    private ArrayList<SalesLine> lines;

    public ArrayList<SalesLine> getLines() {
        if(lines == null)
            lines = new ArrayList<>();
        return lines;
    }
        public String getSaveInvoice(){
    
    return numb + "," + datee + "," + nameOfCustomer + "," ;
    }

    
    
    
    
    public double SalesInvoiceTotal(){
        double total = 0.0;
        for(SalesLine line:getLines()){
            total+=line.getSalesLineTotal();
        }
        return total;
    }
    public SalesInvoice() {
    }

    public SalesInvoice(int numb, String datee, String nameOfCustomer) {
        this.numb = numb;
        this.datee = datee;
        this.nameOfCustomer = nameOfCustomer;
    }

   


    
    
    

    
    


    @Override
    public String toString() {
        return "SalesInvoice{" + "numb=" + numb + ", datee=" + datee + ", nameOfCustomer=" + nameOfCustomer + '}';
    }
    
    
    public int getNumb() {
        return numb;
    }

    
    public void setNumb(int numb) {
        this.numb = numb;
    }

    
    public String getDatee() {
        return datee;
    }

   
    public void setDatee(String datee) {
        this.datee = datee;
    }

   
    public String getNameOfCustomer() {
        return nameOfCustomer;
    }

    
    public void setNameOfCustomer(String nameOfCustomer) {
        this.nameOfCustomer = nameOfCustomer;
    }

    
    
}


