package realestate.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddLandlord extends JFrame {
	public AddLandlord() {
		this.setLayout(new BorderLayout());	
		 
		  JLabel title=new JLabel("Add Landlord",SwingConstants.CENTER);  
		  title.setFont( new Font("Serif", Font.PLAIN, 35));
		  this.add(title,BorderLayout.NORTH);
		  JPanel controlsPanel=new JPanel();
		  controlsPanel.setLayout(new GridLayout(0,2,25,50));
		  JLabel lblName,lblAddress, lblUniqueID, lblBankDetails,lblRentAmount;
		  lblName=new JLabel("Name:",SwingConstants.CENTER);
		  lblAddress=new JLabel("Address:",SwingConstants.CENTER);
		  lblUniqueID=new JLabel("ID:",SwingConstants.CENTER);
		  lblBankDetails=new JLabel("Bank Details:",SwingConstants.CENTER);
		  lblRentAmount=new JLabel("Weekly Rent Amount:",SwingConstants.CENTER);
		  JTextField txtName=new JTextField();
		  JTextField txtId=new JTextField();
		  JTextField txtRentAmount=new JTextField();
		  JTextField txtBankDetails=new JTextField();		  
		  JTextArea txtAddress=new JTextArea();	
		  

		  controlsPanel.add(lblUniqueID);controlsPanel.add(txtId);
		  controlsPanel.add(lblName);controlsPanel.add(txtName);
		  controlsPanel.add(lblBankDetails);controlsPanel.add(txtBankDetails);
		  controlsPanel.add(lblRentAmount);controlsPanel.add(txtRentAmount);
		  controlsPanel.add(lblAddress);controlsPanel.add(txtAddress);
		  JButton btnAddSeller=new JButton("Add Landlord");
		  btnAddSeller.setBackground(Color.green);
		  btnAddSeller.setForeground(Color.white);
		  
		  btnAddSeller.addActionListener(new ActionListener(){  
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String name=txtName.getText();
						String address=txtAddress.getText();
						String bankdetails=txtBankDetails.getText();
						if(txtId.getText().toString().length()==0){
							JOptionPane.showMessageDialog(null, "Error:Please enter unique id","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if(txtRentAmount.getText().toString().length()==0){
							JOptionPane.showMessageDialog(null, "Error:Please enter rent amount","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						double rentAmount=Double.parseDouble(txtRentAmount.getText());
						int uniqueID=Integer.parseInt(txtId.getText());
						String conveyancer=txtBankDetails.getText();
						if(name.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter name","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(address.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter address","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(bankdetails.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter bank details","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						
						if(conveyancer.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter conveyancer","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						for(Owner owner:DataManager.owners) {
							if(owner.getOwnerID()==uniqueID) {
								JOptionPane.showMessageDialog(null, "Error:Owner id must be unique","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
						
						
						Landlord landlord=new Landlord(name, address, uniqueID, bankdetails,rentAmount);
						DataManager.owners.add(landlord);
						JOptionPane.showMessageDialog(null, "Landlord added!", "Information",JOptionPane.INFORMATION_MESSAGE);
						txtName.setText("");
						txtAddress.setText("");
						txtId.setText("");
						txtBankDetails.setText("");
						txtRentAmount.setText("");
					
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error:"+ex.toString(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}  
		  }); 
		  
		  this.add(controlsPanel,BorderLayout.CENTER);
		  this.add(btnAddSeller,BorderLayout.SOUTH);
		  this.setSize(500,500);
		  this.setVisible( true );
		  this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  setLocationRelativeTo(null); 
	      setVisible(true);
		
	}
}
