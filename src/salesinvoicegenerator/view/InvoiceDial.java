/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.view;
import java.awt.GridLayout;
import javax.swing.*;
/**
 *
 * @author abdelrahman.ahmed
 */
public class InvoiceDial  extends JDialog{
    private JTextField insertCustomerName;
    private JTextField insertDate;
    private JLabel customerName;
    private JLabel Date;
    private JButton save;
    private JButton cancel;
     public InvoiceDial(InvoiceFrame invoiceFrame) {
        customerName = new JLabel("Customer Name:");
        insertCustomerName = new JTextField(20);
        Date = new JLabel("Invoice Date:");
        insertDate = new JTextField(20);
        save = new JButton("Save");
        cancel = new JButton("Cancel");
        
        save.setActionCommand("saveInvoice");
        cancel.setActionCommand("cancelInvoice");
        
        save.addActionListener(invoiceFrame.getControllerControl());
        cancel.addActionListener(invoiceFrame.getControllerControl());
        
        GridLayout CreateNewInvoice = new GridLayout(3,2);
        setLayout(CreateNewInvoice);        
        
        add(customerName);
        add(insertCustomerName);
        add(Date);
        add(insertDate);
        add(save);
        add(cancel);
        pack();
    }

    public JTextField getCustomerName() {
        return insertCustomerName;
    }

    public JTextField getDate() {
        return insertDate;
    }
}
