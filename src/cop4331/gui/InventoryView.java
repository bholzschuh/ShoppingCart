package cop4331.gui;

import cop4331.client.Inventory;
import cop4331.client.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryView {

    private JPanel invList;
    private JPanel vendorPanel;
    private JButton reportButton;
    private JButton updateButton;
    private JButton addButton;
    private ArrayList<JButton> deleteButtons;
    private ArrayList<JTextField> quantityFields;

    public InventoryView(){
        deleteButtons = new ArrayList<>();
        quantityFields = new ArrayList<>();

        vendorPanel = new JPanel();
        vendorPanel.setLayout(new BorderLayout());

        invList = new JPanel();
        invList.setLayout(new GridBagLayout());

        Inventory inv = Inventory.getInstance();
        addRows(inv.getInventoryList());

        // Buttons
        reportButton = new JButton("Report");
        updateButton = new JButton("Update");
        addButton = new JButton("Add Item");

        // Buttons Panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(reportButton);
        buttons.add(addButton);
        buttons.add(updateButton);

        // Final panel adds
        vendorPanel.add(invList, BorderLayout.CENTER);
        vendorPanel.add(buttons, BorderLayout.SOUTH);
    }

    public JPanel getView(){
        return vendorPanel;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public ArrayList getDeleteButtons() { return deleteButtons; }

    public ArrayList getQuantityFields() { return quantityFields; }

    public void addRows(ArrayList<Item> list){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
        int y = 0;
        for(Item item: list){
            c.gridx = 0;
            c.gridy = y;
            JLabel nameLabel = new JLabel(item.getName());
            invList.add(nameLabel, c);

            c.gridx = 1;
            String cost = "$" + String.valueOf(item.getPrice());
            JLabel priceLabel = new JLabel(cost);
            invList.add(priceLabel, c);

            c.gridx = 2;
            JTextField quantityField = new JTextField();
            quantityField.setColumns(3);
            String amount = String.valueOf(item.getQuantity());
            quantityField.setText(amount);
            invList.add(quantityField, c);
            quantityFields.add(quantityField);

            c.gridx = 3;
            JButton deleteButton = new JButton("X");
            invList.add(deleteButton, c);
            deleteButtons.add(deleteButton);

            y++;
        }
    }
}
