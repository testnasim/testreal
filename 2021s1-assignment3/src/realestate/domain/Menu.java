package realestate.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	public Menu() {
		  this.setLayout(new BorderLayout());	
		  JLabel title=new JLabel("Real-Estate Agency",SwingConstants.CENTER);  
		  title.setFont( new Font("Serif", Font.PLAIN, 45));
		  this.add(title,BorderLayout.NORTH);
		  JPanel optionsPanel=new JPanel();
		  optionsPanel.setLayout(new GridLayout(0,2));
		  JButton btnResetData=new JButton("Reset Data");
		  JButton btnAddSeller=new JButton("Add New Seller Data");
		  JButton btnAddLandlord=new JButton("Add New Landlord Data");
		  JButton btnAddSellProperty=new JButton("New Property for Sale");
		  JButton btnAddLeaseProperty=new JButton("New Property for Lease");
		  JButton btnReportSaleProperty=new JButton("Generate Report for Sale Properties");
		  JButton btnReportLeaseProperty=new JButton("Generate Report for Rental Properties");
		  JButton btnExit=new JButton("Exit");
		  optionsPanel.add(btnAddSeller);optionsPanel.add(btnAddLandlord);
		  optionsPanel.add(btnAddSellProperty);optionsPanel.add(btnAddLeaseProperty);
		  optionsPanel.add(btnReportSaleProperty);optionsPanel.add(btnReportLeaseProperty);
		  optionsPanel.add(btnResetData);optionsPanel.add(btnExit);
		  btnExit.setBackground(Color.RED);
		  btnExit.setForeground(Color.WHITE);
		  
		  
		  //to reset the data to startup files
		  btnResetData.addActionListener(new ActionListener(){ 
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DataManager.readDefaultOwnersCSV();
					DataManager.readDefaultPropertiesCSV();
					DataManager.saveOwners();
		    		DataManager.saveProperties();
				}  
			  
		  });
		  
		  
		//to show add seller form
		  btnAddSeller.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddSeller();
				DataManager.saveOwners();
			} 
		  });
		  
		//to show add sell property form
		  btnAddSellProperty.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddSaleProperty();
				DataManager.saveProperties();
				
			} 
		  });
		  
		//to show add landlord form
		  btnAddLandlord.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddLandlord();
				DataManager.saveOwners();
				
			} 
		  });
		  
		  
		//to show add leased property form
		  btnAddLeaseProperty.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddLeaseProperty();
				DataManager.saveProperties();
			} 
		  });
		  
		//to show add landlord form
		  btnAddLandlord.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddSaleProperty();
				DataManager.saveProperties();
			} 
		  });
		  
		  
		  //to close the application
		  btnExit.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DataManager.saveOwners();
	    		DataManager.saveProperties();
				System.exit(0); 
			}  
		   });  
		  
		  
		  this.add(optionsPanel,BorderLayout.CENTER);
		  this.setSize(400,500);
		  this.setVisible( true );
		  this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		  this.addWindowListener(new WindowAdapter() {
		      @Override
		      public void windowClosing(WindowEvent event) {
		    	  //to save before closing
		    	  DataManager.saveOwners();
		    	  DataManager.saveProperties();
		      }
		  });
		  setLocationRelativeTo(null); 
	      setVisible(true);
	}
}
