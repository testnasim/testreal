package realestate.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddLeaseProperty extends JFrame {
	public AddLeaseProperty() {
		this.setLayout(new BorderLayout());	
		 
		  JLabel title=new JLabel("Add Lease Property",SwingConstants.CENTER);  
		  title.setFont( new Font("Serif", Font.PLAIN, 35));
		  this.add(title,BorderLayout.NORTH);
		  JPanel controlsPanel=new JPanel();
		  controlsPanel.setLayout(new GridLayout(0,2,25,50));
		  JLabel lblTown,lblAddress, lblUniquePropertyID, lblOwner,lblTenantName,lblTenantPhone;
		  lblTown=new JLabel("Town:",SwingConstants.CENTER);
		  lblAddress=new JLabel("Address:",SwingConstants.CENTER);
		  lblUniquePropertyID=new JLabel("ID:",SwingConstants.CENTER);
		  lblOwner=new JLabel("Owner:",SwingConstants.CENTER);
		  lblTenantName=new JLabel("Tenant Name:",SwingConstants.CENTER);
		  lblTenantPhone=new JLabel("Tenant Phone:",SwingConstants.CENTER);
		  JTextField txtTown=new JTextField();
		  JTextField txtId=new JTextField();
		  JComboBox ownerCb = new JComboBox();
		  for(Owner owner:DataManager.owners) {
			  ownerCb.addItem(owner.getOwnerID()+")"+owner.getName());
		  }
		  JTextField txtTenantName=new JTextField();
		  JTextField txtTenantPhone=new JTextField();
		  JTextArea txtAddress=new JTextArea();	

		  controlsPanel.add(lblUniquePropertyID);controlsPanel.add(txtId);
		  controlsPanel.add(lblTown);controlsPanel.add(txtTown);
		  controlsPanel.add(lblOwner);controlsPanel.add(ownerCb);
		  controlsPanel.add(lblAddress);controlsPanel.add(txtAddress);
		  controlsPanel.add(lblTenantName);controlsPanel.add(txtTenantName);
		  controlsPanel.add(lblTenantPhone);controlsPanel.add(txtTenantPhone);
		  
		  JButton btnAddProperty=new JButton("Add Property");
		  btnAddProperty.setBackground(Color.green);
		  btnAddProperty.setForeground(Color.white);
		  
		  btnAddProperty.addActionListener(new ActionListener(){  
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String town=txtTown.getText();
						String address=txtAddress.getText();
						if(txtId.getText().toString().length()==0){
							JOptionPane.showMessageDialog(null, "Error:Please enter unique id","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						int ownerID=ownerCb.getSelectedIndex();
						Owner owner=DataManager.owners.get(ownerID);
						
						if(txtTenantPhone.getText().toString().length()==0) {
							JOptionPane.showMessageDialog(null, "Error:Please enter tenant phone","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						String tenantPhone=txtTenantPhone.getText().toString();
						int uniqueID=Integer.parseInt(txtId.getText());
						if(town.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter town","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(address.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter address","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						for(Property p:DataManager.properties) {
							if( p.getPropertyID()==uniqueID) {
								JOptionPane.showMessageDialog(null, "Property id must be unique","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						String tenantName=txtTenantName.getText().toString();
						if(tenantName.equals("")) {
							JOptionPane.showMessageDialog(null, "Error:Please enter tenant name","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						
						RentalProperty rp=new RentalProperty(address, town, uniqueID, owner, tenantName,tenantPhone);
						DataManager.properties.add(rp);
						JOptionPane.showMessageDialog(null, "Lease property added!", "Information",JOptionPane.INFORMATION_MESSAGE);
						txtTown.setText("");
						txtAddress.setText("");
						txtId.setText("");
						txtTenantPhone.setText("");
						txtTenantName.setText("");
				
					
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error:"+ex.toString(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}  
		  }); 
		  
		  this.add(controlsPanel,BorderLayout.CENTER);
		  this.add(btnAddProperty,BorderLayout.SOUTH);
		  this.setSize(500,500);
		  this.setVisible( true );
		  this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  setLocationRelativeTo(null); 
	      setVisible(true);
		
	}
}
