package erp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import javax.swing.ListSelectionModel;

import staff.*;
import utility.LinkedList;

/**
 * @author C.Gerber ERP: means Enterprise resource planning
 * 
 */

//TODO : fix bug when we enter characters in field dedicated for number !
// sometimes, the filed for number becomes empty ! it generate many exeption when we press ok...
//show massage pop-up when entry error or save file when table entry doesn't exist

public class Erp extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1272665389579959888L;

	/**
	 * Define the default extension of the save file
	 */
	public static final String ERP_EXTENSION = "erp";
	/**
	 * Define if the debug mode is activated
	 */
	public final static boolean DEBUG = true;

	/**
	 * 
	 */
	private static JTable table;
	private static DefaultTableModel tableModel;

	private LinkedList<Employee> employeeList;
	private JButton btnShow;
	private JFileChooser chooser;
	private JButton btnAdd;
	private JButton btnDelete;
	private JMenu mnFile;
	private JMenuBar menuBar;
	private JMenuItem menuSaveFile;
	private JScrollPane pane;

	/**
	 * Launch the application by opening the main frame
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Erp frame = new Erp();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Erp()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 588);

		JPanel GestionPanel = new JPanel();
		getContentPane().add(GestionPanel, BorderLayout.SOUTH);
		GestionPanel.setMaximumSize(new Dimension(32767, 600));

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		/*
		 * Definition of the default saving location and extension
		 */
		String defaultLocation = getClass().getProtectionDomain()
				.getCodeSource().getLocation().getFile();
		chooser = new JFileChooser(defaultLocation);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Employee database", ERP_EXTENSION, ERP_EXTENSION);
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);

		mnFile = new JMenu("fichier");
		menuBar.add(mnFile);

		JMenuItem menuOpenFile = new JMenuItem("Ouvrir Fichier");
		menuOpenFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				openFile();
			}
		});

		mnFile.add(menuOpenFile);

		menuSaveFile = new JMenuItem("Sauver fichier");

		menuSaveFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				saveFile();
			}
		});

		mnFile.add(menuSaveFile);

		employeeList = new LinkedList<Employee>();

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBounds(0, 0, 300, 0);

		pane = new JScrollPane(table);
		getContentPane().add(pane, BorderLayout.CENTER);

		String[] columnNames =
		{ "Index", "nom", "Type", "Salaire" };

		// TableModel used to manage the data in the table
		tableModel = new DefaultTableModel(columnNames, 0)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -4957135629083872096L;

			// Overrides this method in order to disable the edition of cells
			@Override
			public boolean isCellEditable(int row, int column)
			{
				// all cells false
				return false;
			}
		};

		table.setModel(tableModel);

		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();

					editEmployee(row);
				}
			}
		});

		btnAdd = new JButton("Ajouter");

		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				addEmployee();
			}
		});

		GestionPanel.add(btnAdd);

		btnShow = new JButton("Editer");
		btnShow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				if (table.getSelectedRows().length != 1) JOptionPane
						.showMessageDialog(getContentPane(),
								"Erreur, il faut sélectionner une ligne");
				else
				{
					editEmployee(table.getSelectedRow());
				}
			}
		});
		GestionPanel.add(btnShow);

		btnDelete = new JButton("Effacer");
		btnDelete.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (table.getSelectedRows().length < 1) JOptionPane
						.showMessageDialog(getContentPane(),
								"Erreur, vous devez sélectionner 1 ligne ou plus");
				int[] selection;
				int index;
				while (table.getSelectedRows().length > 0)
				{
					selection = table.getSelectedRows();
					index = ((int) tableModel.getValueAt(selection[0], 0));

					tableModel.removeRow(selection[0]);
					employeeList.delete(index);
				}
				table.clearSelection();

				for (int i = 0; i < table.getRowCount(); i++)
				{
					tableModel.setValueAt(i + 1, i, 0);
				}
			}
		});

		GestionPanel.add(btnDelete);
	}

	/**
	 * Create the dialog to show and edit the data
	 * 
	 * @param row
	 *            the row to be shown/edited
	 */
	private void editEmployee(int row)
	{

		int index = (int) tableModel.getValueAt(table.getSelectedRow(), 0);

		Employee employee = employeeList.get(index);
		String name = employee.getName();
		double hourlyRate = 0;
		double hours = 0;
		double commission = 0;
		double sales = 0;
		double salary = 0;
		String employeeType = Employee.CLASS_NAME;

		if (employee instanceof HourlyEmployee)
		{
			name = employee.getName();
			hourlyRate = ((HourlyEmployee) employee).getWageRate();
			hours = ((HourlyEmployee) employee).getHours();
			employeeType = HourlyEmployee.CLASS_NAME;
		}
		else if (employee instanceof Manager)
		{
			name = employee.getName();
			salary = ((Manager) employee).getSalary();
			employeeType = Manager.CLASS_NAME;
		}
		else if (employee instanceof SalesMan)
		{
			name = employee.getName();
			hourlyRate = ((SalesMan) employee).getWageRate();
			hours = ((SalesMan) employee).getHours();
			commission = ((SalesMan) employee).getCommission();
			sales = ((SalesMan) employee).getSales();
			employeeType = SalesMan.CLASS_NAME;
		}

		// Opens the modal dialog
		AddModifyEmployeeDialog editDialog = new AddModifyEmployeeDialog(employeeType, index, name,
				hourlyRate, hours, commission, sales, salary);
		editDialog.setVisible(true);

		// Check if the dialog has been exited with ok or exit
		if (editDialog.getOkPressed())
		{

			hourlyRate = Double.valueOf(editDialog.getTextFieldHourlyRate());
			employeeType = editDialog.getEmployeeType();
			name = editDialog.getTextFieldName();
			hours = Double.valueOf(editDialog.getTextFieldHours());
			hourlyRate = Double.valueOf(editDialog.getTextFieldHourlyRate());
			commission = Double.valueOf(editDialog.getTextFieldCommission());
			sales = Double.valueOf(editDialog.getTextFieldSales());
			salary = Double.valueOf(editDialog.getTextFieldSalary());

			Employee newEmployee;

			switch (employeeType)
			{
				case HourlyEmployee.CLASS_NAME:
					newEmployee = new HourlyEmployee(name, hourlyRate, hours);
					break;

				case SalesMan.CLASS_NAME:
					newEmployee = new SalesMan(name, hourlyRate, hours,
							commission, sales);
					break;

				case Manager.CLASS_NAME:
				default:
					newEmployee = new Manager(name, salary);
					break;
			}

			if (!newEmployee.equals(employee))
			{
				employeeList.replace(index, newEmployee);
				tableModel.setValueAt(newEmployee.getName(), index - 1, 1);
				tableModel.setValueAt(employeeType, index - 1, 2);
				tableModel.setValueAt(newEmployee.getPay(), index - 1, 3);
			}
		}
	}

	/**
	 * Opens the dialog to add a new employee Takes no parameter
	 */
	private void addEmployee()
	{

		AddModifyEmployeeDialog addEmployeeDialog = new AddModifyEmployeeDialog(employeeList.getSize() + 1);
		addEmployeeDialog.setVisible(true);

		// Checks if the dialog was exited using OK
		if (addEmployeeDialog.getOkPressed())
		{

			Employee employee;
			String name = addEmployeeDialog.getTextFieldName();
			double hours = Double.valueOf(addEmployeeDialog.getTextFieldHours());
			double hourlyRate = Double.valueOf(addEmployeeDialog
					.getTextFieldHourlyRate());
			double commission = Double.valueOf(addEmployeeDialog
					.getTextFieldCommission());
			double sales = Double.valueOf(addEmployeeDialog.getTextFieldSales());
			double salary = Double.valueOf(addEmployeeDialog.getTextFieldSalary());

			String employeeType = addEmployeeDialog.getEmployeeType();

			switch (employeeType)
			{
				case HourlyEmployee.CLASS_NAME:
					employee = new HourlyEmployee(name, hourlyRate, hours);
					break;

				case SalesMan.CLASS_NAME:
					employee = new SalesMan(name, hourlyRate, hours,
							commission, sales);
					break;

				case Manager.CLASS_NAME:
				default:
					employee = new Manager(name, salary);
					break;
			}

			Object[] data =
			{ employeeList.getSize() + 1, name, employeeType, employee.getPay() };

			employeeList.addAtEnd(employee);
			tableModel.addRow(data);
		}

	}

	private void saveFile()
	{
		int returnVal = chooser.showSaveDialog(getContentPane());
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();

			String fileName = file.getName();
			int position = fileName.lastIndexOf('.');
			String extension = "";
			if (position >= 0) extension = fileName.substring(position + 1);

			if (!extension.equals(ERP_EXTENSION)) file = new File(
					file.getPath() + ".erp");

			if (DEBUG) System.out.println("You chose to save this file: "
					+ file.getPath());
			try
			{
				if (!file.exists()) file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(employeeList);
				oos.close();
			}
			catch (IOException e)
			{
				if (DEBUG) System.out.println("Error while saving the file");
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void openFile()
	{
		int returnVal = chooser.showOpenDialog(getContentPane());
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getPath());
			File file = chooser.getSelectedFile();

			if (file.exists())
			{
				try
				{
					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);
					employeeList = (LinkedList<Employee>) ois.readObject();
					ois.close();
				}
				catch (Exception e)
				{
					if (DEBUG) System.out
							.println("Error while opening the file");
					e.printStackTrace();
					return;
				}

				while (tableModel.getRowCount() > 0)
				{
					tableModel.removeRow(0);
				}

				int i = 1;
				Employee employee;
				while (i <= employeeList.getSize())
				{
					employee = employeeList.get(i);
					String employeeType = Employee.CLASS_NAME;

					if (employee instanceof HourlyEmployee) employeeType = HourlyEmployee.CLASS_NAME;

					if (employee instanceof Manager) employeeType = Manager.CLASS_NAME;

					if (employee instanceof SalesMan) employeeType = SalesMan.CLASS_NAME;

					Object[] data =
					{ i, employee.getName(), employeeType, employee.getPay() };
					tableModel.addRow(data);

					i++;
				}
			}
			else
			{
				if (DEBUG) System.out.println("The file does not exist");
			}
		}
	}

}
