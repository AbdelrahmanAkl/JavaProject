
package salesinvoicegenerator.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LinesTable extends AbstractTableModel{
    private ArrayList<SalesLine> lines;
    private String[] columnsNames ={"Num","Item Name","Item Price","Item Count","Total"};
    
public LinesTable(ArrayList<SalesLine> lines) {
        this.lines = lines;
    }
    
    @Override
    public int getRowCount() {
        return lines.size();
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
        SalesLine line = lines.get(rowIndex);
        switch(columnIndex){
            case 0: return line.getInvoice().getNumb();
            case 1: return line.getItemName();
            case 2: return line.getItmePrice();
            case 3: return line.getCount();
            case 4: return line.getSalesLineTotal();
            default: return " ";
        }
    }

    
    
}
