package erp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.DefaultComboBoxModel;

import com.ibm.icu.text.NumberFormat;

public class Erp extends JFrame {

	public enum EmployeType {
		HOURLY_EMPLOYE, SALESMAN, MANAGER;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private static DefaultTableModel tableModel;
	private static int counter = 0;
	
	Employe employe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Erp frame = new Erp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Erp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 588);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu(Messages.getString("Erp.mnFile.text")); //$NON-NLS-1$
		menuBar.add(mnFile);

		JMenuItem menuOpenFile = new JMenuItem(
				Messages.getString("Erp.mntmOpenFile.text")); //$NON-NLS-1$
		mnFile.add(menuOpenFile);

		JMenuItem menuSaveFile = new JMenuItem(
				Messages.getString("Erp.menuSaveFile.arg0")); //$NON-NLS-1$
		mnFile.add(menuSaveFile);

		JMenu mnOption = new JMenu(Messages.getString("Erp.mnOption.text")); //$NON-NLS-1$
		menuBar.add(mnOption);

		JRadioButtonMenuItem rdbtnmntmEnglish = new JRadioButtonMenuItem(
				Messages.getString("Erp.rdbtnmntmEnglish.text")); //$NON-NLS-1$
		mnOption.add(rdbtnmntmEnglish);

		JRadioButtonMenuItem rdbtnmntmFrench = new JRadioButtonMenuItem(
				Messages.getString("Erp.rdbtnmntmFrench.text")); //$NON-NLS-1$
		mnOption.add(rdbtnmntmFrench);

		table = new JTable();
		table.setBounds(0, 0, 300, 0);

		JScrollPane pane = new JScrollPane(table);
		getContentPane().add(pane, BorderLayout.NORTH);

		String[] columnNames = { Messages.getString("erp.index.text"),
				Messages.getString("erp.name.text"),
				Messages.getString("erp.type.text"),
				Messages.getString("erp.salary.text") };

		tableModel = new DefaultTableModel(columnNames, 0);

		table.setModel(tableModel);

		JPanel panelInput = new JPanel();
		getContentPane().add(panelInput, BorderLayout.CENTER);
		panelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));



		JPanel GestionPanel = new JPanel();
		getContentPane().add(GestionPanel, BorderLayout.SOUTH);
		GestionPanel.setMaximumSize(new Dimension(32767, 600));

		JButton btnAdd = new JButton(Messages.getString("erp.btnAdd.text"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO create dialog to add an employes
				
				DetailDialog addDialog = new DetailDialog();
				addDialog.setVisible(true);
				
				

				String name = addDialog.getTextFieldName();
				double hours = Double.valueOf(addDialog.getTextFieldHours());
				double hourlyRate = Double.valueOf(addDialog.getTextFieldHourlyRate());
				
				EmployeType employeType = addDialog.getEmployeType();
				
				switch (employeType) {
				case HOURLY_EMPLOYE:
					employe = new HourlyEmploye(name, hourlyRate, hours);
					break;

				case SALESMAN:
					break;

				case MANAGER:
					break;

				}
				
				double salary = employe.getPay();

				Object[] data = { counter, name, employeType, employe.getPay() };
				tableModel.addRow(data);
				counter++;

			}
		});

		GestionPanel.add(btnAdd);

		JButton btnEdit = new JButton(Messages.getString("erp.btnEdit.text")); //$NON-NLS-1$
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getSelectedRows().length != 1)
					; //TODO create error dialog when more or less than 1 line is selected
				else
				{
					//Get the values of the selected row
					table.getSelectedRow();
	
					EmployeType employeType;
					
					DetailDialog bd = new DetailDialog();
					bd.setVisible(true);
				}

			    
			}
		});
		GestionPanel.add(btnEdit);

		JButton btnDelete = new JButton(
				Messages.getString("erp.btnDelete.text")); //$NON-NLS-1$
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int[] selection;
				while (table.getSelectedRows().length > 0) {
					selection = table.getSelectedRows();
					tableModel.removeRow(selection[0]);
					counter--;
				}
				table.clearSelection();

			}
		});

		GestionPanel.add(btnDelete);

	}
}
