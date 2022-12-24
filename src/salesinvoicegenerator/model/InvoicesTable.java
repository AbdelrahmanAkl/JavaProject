
package salesinvoicegenerator.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;



public class InvoicesTable extends AbstractTableModel {
    private ArrayList<SalesInvoice> invoices;
    private String[] columnsNames ={"Num","Date","CustomerName","Total"};

    public InvoicesTable(ArrayList<SalesInvoice> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnsNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnsNames[column];
    }

    
    
   

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SalesInvoice invoice = invoices.get(rowIndex);
        switch(columnIndex){
            case 0: return invoice.getNumb();
            case 1: return invoice.getDatee();
            case 2: return invoice.getNameOfCustomer();
            case 3: return invoice.SalesInvoiceTotal();
            default: return " ";
        }
        
        
    }

    
}

