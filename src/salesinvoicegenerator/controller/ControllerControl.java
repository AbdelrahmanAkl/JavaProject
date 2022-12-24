
package salesinvoicegenerator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesinvoicegenerator.model.InvoicesTable;
import salesinvoicegenerator.model.LinesTable;
import salesinvoicegenerator.model.SalesInvoice;
import salesinvoicegenerator.model.SalesLine;
import salesinvoicegenerator.view.InvoiceFrame;


public class ControllerControl  implements ActionListener,ListSelectionListener{
    private InvoiceFrame frame;
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
            case "createInvoiceCancel": 
                createInvoiceCancel();
                break;
            case "createInvoiceOK":
                createInvoiceOK();
                break;
            case "createLineOK":
                createLineOK();
                break;
            case "createLineCancel":
                createLineCancel();
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
    }

    private void createNewInvoice() {
       
        
    }

    private void deleteInvoice() {
        int SelectRow = frame.getInvoiceRecordTable().getSelectedRow();
        if(SelectRow != -1){
            frame.getInvoicees().remove(SelectRow);
            frame.getInvoicestable().fireTableDataChanged();
        }
        
       
    }

    private void createNewItem() {
        
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

    private void createInvoiceCancel() {
    }

    private void createInvoiceOK() {
    }

    private void createLineOK() {
    }

    private void createLineCancel() {
    }

   
       
    
}

