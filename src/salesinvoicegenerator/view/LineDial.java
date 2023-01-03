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
public class LineDial extends JDialog{
    private JTextField itemName;
    private JTextField itemQuantity;
    private JTextField itemPrice;
    private JLabel itemNameLabel;
    private JLabel itemQuantityLabel;
    private JLabel itemPriceLabel;
    private JButton save;
    private JButton cancel;
    
    public LineDial(InvoiceFrame invoiceFrame) {
        itemName = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        
        itemQuantity = new JTextField(20);
        itemQuantityLabel = new JLabel("Item Quantity");
        
        itemPrice = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        
        save = new JButton("Save");
        cancel = new JButton("Cancel");
        
        save.setActionCommand("saveLine");
        cancel.setActionCommand("cancelLine");
        
        save.addActionListener(invoiceFrame.getControllerControl());
        cancel.addActionListener(invoiceFrame.getControllerControl());
        GridLayout CreateNewItem = new GridLayout(4,2);
        setLayout(CreateNewItem);

        add(itemNameLabel);
        add(itemName);
        add(itemQuantityLabel);
        add(itemQuantity);
        add(itemPriceLabel);
        add(itemPrice);
        add(save);
        add(cancel);   
        pack();
    }

    public JTextField getItemName() {
        return itemName;
    }

    public JTextField getItemQuantity() {
        return itemQuantity;
    }

    public JTextField getItemPrice() {
        return itemPrice;
    }
}
