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

public class AddSeller extends JFrame {
	public AddSeller() {
		this.setLayout(new BorderLayout());	
		 
		  JLabel title=new JLabel("Add Seller",SwingConstants.CENTER);  
		  title.setFont( new Font("Serif", Font.PLAIN, 35));
		  this.add(title,BorderLayout.NORTH);
		  JPanel controlsPanel=new JPanel();
		  controlsPanel.setLayout(new GridLayout(0,2,25,50));
		  JLabel lblName,lblAddress, lblUniqueID, lblConveyancer;
		  lblName=new JLabel("Name:",SwingConstants.CENTER);
		  lblAddress=new JLabel("Address:",SwingConstants.CENTER);
		  lblUniqueID=new JLabel("ID:",SwingConstants.CENTER);
		  lblConveyancer=new JLabel("Conveyancer:",SwingConstants.CENTER);
		  JTextField txtName=new JTextField();
		  JTextField txtId=new JTextField();
		  JTextField txtConveyancer=new JTextField();		  
		  JTextArea txtAddress=new JTextArea();	

		  controlsPanel.add(lblUniqueID);controlsPanel.add(txtId);
		  controlsPanel.add(lblName);controlsPanel.add(txtName);
		  controlsPanel.add(lblConveyancer);controlsPanel.add(txtConveyancer);
		  controlsPanel.add(lblAddress);controlsPanel.add(txtAddress);
		  JButton btnAddSeller=new JButton("Add Seller");
		  btnAddSeller.setBackground(Color.green);
		  btnAddSeller.setForeground(Color.white);
		  
		  btnAddSeller.addActionListener(new ActionListener(){  
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String name=txtName.getText();
						String address=txtAddress.getText();
						if(txtId.getText().toString().length()==0){
							JOptionPane.showMessageDialog(null, "Error:Please enter unique id","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						int uniqueID=Integer.parseInt(txtId.getText());
						String conveyancer=txtConveyancer.getText();
						if(name.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter name","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(address.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter address","Error",JOptionPane.ERROR_MESSAGE);
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
						
						Seller seller=new Seller(name, address, uniqueID, conveyancer);
						DataManager.owners.add(seller);
						JOptionPane.showMessageDialog(null, "Seller added!", "Information",JOptionPane.INFORMATION_MESSAGE);
						txtName.setText("");
						txtAddress.setText("");
						txtId.setText("");
						txtConveyancer.setText("");
					
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
