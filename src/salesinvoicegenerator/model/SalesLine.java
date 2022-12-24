
package salesinvoicegenerator.model;


public class SalesLine {

    private String itemName;
    private double itmePrice;
    private int count;
    SalesInvoice Invoice;

    
    
    public SalesLine(int count, String itemName, double itmePrice, int count1, SalesInvoice Invoice) {
        this.itemName = itemName;
        this.itmePrice = itmePrice;
        this.count = count;
        this.Invoice= Invoice;
    }

    public SalesInvoice getInvoice() {
        return Invoice;
    }
    
    @Override
    public String toString() {
        return "SalesLine{" + "num=" + Invoice.getNumb() + ", itemName=" + itemName + ", itmePrice=" + itmePrice + ", count=" + count + ", Invoice=" + Invoice + '}';
    }
     
    public double getSalesLineTotal(){
       return itmePrice*count;
   }
    

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItmePrice() {
        return itmePrice;
    }

    public void setItmePrice(double itmePrice) {
        this.itmePrice = itmePrice;
    }
    
    
    
}
