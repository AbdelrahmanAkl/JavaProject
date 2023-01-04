
package salesinvoicegenerator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesinvoicegenerator.model.InvoicesTable;
import salesinvoicegenerator.model.LinesTable;
import salesinvoicegenerator.model.SalesInvoice;
import salesinvoicegenerator.model.SalesLine;
import salesinvoicegenerator.view.InvoiceDial;

import salesinvoicegenerator.view.InvoiceFrame;
import salesinvoicegenerator.view.LineDial;


public class ControllerControl  implements ActionListener,ListSelectionListener{
    private InvoiceFrame frame;
    private InvoiceDial invoiceDial;
    private LineDial lineDial;
    public ControllerControl(InvoiceFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action: "+actionCommand);
        switch(actionCommand){
            case "Upload Files":
                uploadFiles();
                break;
            case "Save Files":
                saveFiles();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            
            case"Create New Item":
                    createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "saveInvoice":
                saveInvoice();
                break;
            case "cancelInvoice":
                cancelInvoice();
                break;
            case "saveLine":
                saveLine();
                break;
            case "cancelLine":
                cancelLine();
                break;
                 
                    
        }
    
    
}
    
 @Override
    public void valueChanged(ListSelectionEvent e) {
        int IndexSelect = frame.getInvoiceRecordTable().getSelectedRow();
        if(IndexSelect != -1){
        System.out.println("Selected Row is:"+IndexSelect);
        SalesInvoice currentInvoice = frame.getInvoicees().get(IndexSelect);
        frame.getInvoiceNumberLabel().setText(""+currentInvoice.getNumb());
        frame.getInvoiceDateLabel().setText(currentInvoice.getDatee());
        frame.getCustomerNameLabel().setText(currentInvoice.getNameOfCustomer());
        frame.getInvoiceTotalLabel().setText(""+currentInvoice.SalesInvoiceTotal());
        LinesTable linetable = new LinesTable(currentInvoice.getLines());
        frame.getInvoiceItemsTable().setModel(linetable);
        linetable.fireTableDataChanged();
        }
    }
    private void uploadFiles() {
        JFileChooser fileChoose = new JFileChooser();
        try{
       int res= fileChoose.showOpenDialog(frame);
       if(res==JFileChooser.APPROVE_OPTION){
           File headFile = fileChoose.getSelectedFile();
           Path headpath = Paths.get(headFile.getAbsolutePath());
           List<String> LineHeadFiles = Files.readAllLines(headpath);
           System.out.println("Check point");
           ArrayList<SalesInvoice> invoicees = new ArrayList<>();
           for(String lineHeadFiles : LineHeadFiles){
               String[] part =  lineHeadFiles.split(",");
               int num = Integer.parseInt(part[0]);
               String date = part[1];
               String name = part[2];
               
               
               SalesInvoice invoices = new SalesInvoice(num,date,name);
               invoicees.add(invoices);
           }
               System.out.println("check point");
                 res = fileChoose.showOpenDialog(frame);
               if(res==JFileChooser.APPROVE_OPTION){
                   File lineFilee = fileChoose.getSelectedFile();
                   Path linePath = Paths.get(lineFilee.getAbsolutePath());
                   List<String> lineFileLines = Files.readAllLines(linePath);
                   for(String line: lineFileLines){
                       String parrt[] =  line.split(",");
                       int Num =Integer.parseInt(parrt[0]);
                       String Name = parrt[1];
                       double Price = Double.parseDouble(parrt[2]);
                       int count = Integer.parseInt(parrt[3]);
                       System.out.println("check pouint");
                       SalesInvoice Invo = null ;
                       for (SalesInvoice invoice : invoicees){
                           if(invoice.getNumb() == Num){
                               System.out.println("check1");
                               Invo = invoice;
                               System.out.println("check2");

                               
                               break;
                           }
                               
                       }
                       
                       
                       SalesLine linee = new SalesLine(Num,Name,Price,count,Invo);
                       Invo.getLines().add(linee);
                       
                   }
               }
               frame.setInvoicees(invoicees);
               InvoicesTable invoicestable = new InvoicesTable(invoicees);
               frame.setInvoicestable(invoicestable);
               frame.getInvoiceRecordTable().setModel(invoicestable);
               frame.getInvoicestable().fireTableDataChanged();
               
           }
                   
       }
       catch(IOException ex){
        ex.printStackTrace();
    }
    }

    private void saveFiles() {
        ArrayList <SalesInvoice> invoiceFiles = frame.getInvoicees();
        String invoice="";
        String createline="";
        for (SalesInvoice file :invoiceFiles){
            String invoicefile =file.getSaveInvoice();
            invoice+=invoicefile;
            invoice+="\n";
           
            for (SalesLine creaetedline: file.getLines()){
                String lineFile = creaetedline.getSaveLine();
                createline+=lineFile;
                createline+="\n";
                
            }
        }
        try{
        JFileChooser save=new JFileChooser();
        int buttonChooser=save.showSaveDialog(frame);
        if(buttonChooser==JFileChooser.APPROVE_OPTION){
            File invoiceHeader=save.getSelectedFile();
            FileWriter header= new FileWriter(invoiceHeader);
            header.write(invoice);
            header.flush();
            header.close();
            buttonChooser=save.showSaveDialog(frame);
            
            if(buttonChooser==JFileChooser.APPROVE_OPTION){
                File invoiceLine =save.getSelectedFile();
                FileWriter liner = new FileWriter(invoiceLine);
                liner.write(createline);
                liner.flush();
                liner.close();
            }
        }
        }catch(Exception a){}
        }

    private void createNewInvoice() {

        invoiceDial = new InvoiceDial(frame);
         invoiceDial.setVisible(true);
         int invoiceIndex = frame.getInvoiceRecordTable().getSelectedRow();
            int rowIndex = frame.getInvoiceItemsTable().getSelectedRow();
            if((invoiceIndex&rowIndex)==-1)
            {}
            else{
                SalesInvoice selectedInvoice=frame.getInvoicees().get(invoiceIndex);
                selectedInvoice.getLines().remove(rowIndex);
                LinesTable modified= new LinesTable(selectedInvoice.getLines());
                frame.getInvoiceItemsTable().setModel(modified);
                modified.fireTableDataChanged(); 
                frame.getInvoicestable().fireTableDataChanged();
                
                
            
        }
        
       }
    

    

    private void deleteInvoice() {
        int SelectRow = frame.getInvoiceRecordTable().getSelectedRow();
        if(SelectRow != -1){
            frame.getInvoicees().remove(SelectRow);
            frame.getInvoicestable().fireTableDataChanged();
        }
        
       
    }

    
        

                    
              
                        
                        
                   
    

    private void deleteItem() {
        int SelectInvoiceRow = frame.getInvoiceRecordTable().getSelectedRow();
        int SelectedLineRow = frame.getInvoiceItemsTable().getSelectedRow();
        if(SelectInvoiceRow != -1 && SelectedLineRow != -1){
            SalesInvoice invoicee = frame.getInvoicees().get(SelectInvoiceRow);
            invoicee.getLines().remove(SelectedLineRow);
            LinesTable linetable = new LinesTable(invoicee.getLines());
            frame.getInvoiceItemsTable().setModel(linetable);
            linetable.fireTableDataChanged();
        }
                
        
       
    }

    private void saveInvoice() {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        String CustomerName = invoiceDial.getCustomerName().getText();
        String date = invoiceDial.getDate().getText();
        int invoiceNum=frame.getNewNum();
        try{
            format.parse(date);
            SalesInvoice newCreated = new SalesInvoice(invoiceNum, date, CustomerName);
            frame.getInvoicees().add(newCreated);
            frame.getInvoicestable().fireTableDataChanged();
                 invoiceDial.setVisible(false);
                 invoiceDial.dispose();
                 invoiceDial = null;
        }catch(Exception a){
            JOptionPane.showMessageDialog(frame, "Invalid Format", "Invalid date ",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cancelInvoice() {
            invoiceDial.setVisible(false);
            invoiceDial.dispose();
            invoiceDial = null;    }

    private void createNewItem() {
         lineDial = new LineDial(frame);
         lineDial.setVisible(true);
    }

    private void cancelLine() {
    }

    private void saveLine() {
        String item = lineDial.getItemName().getText();
        String count = lineDial.getItemQuantity().getText();
        String price = lineDial.getItemPrice().getText();
        int countt = Integer.parseInt(count);
        double princee = Double.parseDouble(price);
        int SelectedInvoiceRow = frame.getInvoiceRecordTable().getSelectedRow();
        if(SelectedInvoiceRow != -1){
            SalesInvoice salesInvoice = frame.getInvoicees().get(SelectedInvoiceRow);
            SalesLine salesline = new SalesLine(countt, item, princee, countt, salesInvoice);
            salesInvoice.getLines().add(salesline);
            LinesTable linetable = (LinesTable) frame.getInvoiceItemsTable().getModel();
            linetable.fireTableDataChanged();
            frame.getInvoicestable().fireTableDataChanged();
            lineDial.setVisible(false);
            lineDial.dispose();
            
            
            
        }
    }

}
   
       
    


