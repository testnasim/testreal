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

public class AddSaleProperty extends JFrame {
	public AddSaleProperty() {
		this.setLayout(new BorderLayout());	
		 
		  JLabel title=new JLabel("Add Sale Property",SwingConstants.CENTER);  
		  title.setFont( new Font("Serif", Font.PLAIN, 35));
		  this.add(title,BorderLayout.NORTH);
		  JPanel controlsPanel=new JPanel();
		  controlsPanel.setLayout(new GridLayout(0,2,25,50));
		  JLabel lblTown,lblAddress, lblUniquePropertyID, lblOwner,lblPrice,lblArea;
		  lblTown=new JLabel("Town:",SwingConstants.CENTER);
		  lblAddress=new JLabel("Address:",SwingConstants.CENTER);
		  lblUniquePropertyID=new JLabel("ID:",SwingConstants.CENTER);
		  lblOwner=new JLabel("Owner:",SwingConstants.CENTER);
		  lblPrice=new JLabel("Asking Price:",SwingConstants.CENTER);
		  lblArea=new JLabel("Indoor Area:",SwingConstants.CENTER);
		  JTextField txtTown=new JTextField();
		  JTextField txtId=new JTextField();
		  JComboBox ownerCb = new JComboBox();
		  for(Owner owner:DataManager.owners) {
			  ownerCb.addItem(owner.getOwnerID()+")"+owner.getName());
		  }
		  JTextField txtprice=new JTextField();
		  JTextField txtarea=new JTextField();
		  JTextArea txtAddress=new JTextArea();	

		  controlsPanel.add(lblUniquePropertyID);controlsPanel.add(txtId);
		  controlsPanel.add(lblTown);controlsPanel.add(txtTown);
		  controlsPanel.add(lblOwner);controlsPanel.add(ownerCb);
		  controlsPanel.add(lblAddress);controlsPanel.add(txtAddress);
		  controlsPanel.add(lblPrice);controlsPanel.add(txtprice);
		  controlsPanel.add(lblArea);controlsPanel.add(txtarea);
		  
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
						
						if(txtprice.getText().toString().length()==0) {
							JOptionPane.showMessageDialog(null, "Error:Please enter price","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						int price=Integer.parseInt(txtprice.getText());
						
						if(txtarea.getText().toString().length()==0) {
							JOptionPane.showMessageDialog(null, "Error:Please enter area","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						double area=Double.parseDouble(txtarea.getText());
						
						
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
						
						SaleProperty sp=new SaleProperty(address, town, uniqueID, owner,price, area);
						DataManager.properties.add(sp);
						JOptionPane.showMessageDialog(null, "Sale property added!", "Information",JOptionPane.INFORMATION_MESSAGE);
						txtTown.setText("");
						txtAddress.setText("");
						txtId.setText("");
						txtarea.setText("");
						txtprice.setText("");
				
					
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