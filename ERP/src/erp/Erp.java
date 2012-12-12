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

	private LinkedList<Employe> employeList;
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
				"Employe database", ERP_EXTENSION, ERP_EXTENSION);
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

		employeList = new LinkedList<Employe>();

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

					openEditDialog(row);
				}
			}
		});

		btnAdd = new JButton("Ajouter");

		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				openAddDialog();
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
					openEditDialog(table.getSelectedRow());
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
					employeList.delete(index);
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
	private void openEditDialog(int row)
	{

		int index = (int) tableModel.getValueAt(table.getSelectedRow(), 0);

		Employe employe = employeList.get(index);
		String name = employe.getName();
		double hourlyRate = 0;
		double hours = 0;
		double commission = 0;
		double sales = 0;
		double salary = 0;
		String employeType = Employe.CLASS_NAME;

		if (employe instanceof HourlyEmploye)
		{
			name = employe.getName();
			hourlyRate = ((HourlyEmploye) employe).getRate();
			hours = ((HourlyEmploye) employe).getHours();
			employeType = HourlyEmploye.CLASS_NAME;
		}
		if (employe instanceof Manager)
		{
			name = employe.getName();
			salary = ((Manager) employe).getSalary();
			employeType = Manager.CLASS_NAME;
		}
		if (employe instanceof Salesman)
		{
			name = employe.getName();
			hourlyRate = ((Salesman) employe).getRate();
			hours = ((Salesman) employe).getHours();
			commission = ((Salesman) employe).getCommission();
			sales = ((Salesman) employe).getSales();
			employeType = Salesman.CLASS_NAME;
		}

		// Opens the modal dialog
		DetailDialog editDialog = new DetailDialog(employeType, index, name,
				hourlyRate, hours, commission, sales, salary);
		editDialog.setVisible(true);

		// Check if the dialog has been exited with ok or exit
		if (editDialog.getOkPressed())
		{

			hourlyRate = Double.valueOf(editDialog.getTextFieldHourlyRate());
			employeType = editDialog.getEmployeType();
			name = editDialog.getTextFieldName();
			hours = Double.valueOf(editDialog.getTextFieldHours());
			hourlyRate = Double.valueOf(editDialog.getTextFieldHourlyRate());
			commission = Double.valueOf(editDialog.getTextFieldCommission());
			sales = Double.valueOf(editDialog.getTextFieldSales());
			salary = Double.valueOf(editDialog.getTextFieldSalary());

			Employe newEmploye;

			switch (employeType)
			{
				case HourlyEmploye.CLASS_NAME:
					newEmploye = new HourlyEmploye(name, hourlyRate, hours);
					break;

				case Salesman.CLASS_NAME:
					newEmploye = new Salesman(name, hourlyRate, hours,
							commission, sales);
					break;

				case Manager.CLASS_NAME:
				default:
					newEmploye = new Manager(name, salary);
					break;
			}

			if (!newEmploye.equals(employe))
			{
				employeList.replace(index, newEmploye);
				tableModel.setValueAt(newEmploye.getName(), index - 1, 1);
				tableModel.setValueAt(employeType, index - 1, 2);
				tableModel.setValueAt(newEmploye.getPay(), index - 1, 3);
			}
		}
	}

	/**
	 * Opens the dialog to add a new employee Takes no parameter
	 */
	private void openAddDialog()
	{

		DetailDialog addDialog = new DetailDialog(employeList.getSize() + 1);
		addDialog.setVisible(true);

		// Checks if the dialog was exited using OK
		if (addDialog.getOkPressed())
		{

			Employe employe;
			String name = addDialog.getTextFieldName();
			double hours = Double.valueOf(addDialog.getTextFieldHours());
			double hourlyRate = Double.valueOf(addDialog
					.getTextFieldHourlyRate());
			double commission = Double.valueOf(addDialog
					.getTextFieldCommission());
			double sales = Double.valueOf(addDialog.getTextFieldSales());
			double salary = Double.valueOf(addDialog.getTextFieldSalary());

			String employeType = addDialog.getEmployeType();

			switch (employeType)
			{
				case HourlyEmploye.CLASS_NAME:
					employe = new HourlyEmploye(name, hourlyRate, hours);
					break;

				case Salesman.CLASS_NAME:
					employe = new Salesman(name, hourlyRate, hours, commission,
							sales);
					break;

				case Manager.CLASS_NAME:
				default:
					employe = new Manager(name, salary);
					break;
			}

			Object[] data =
			{ employeList.getSize() + 1, name, employeType, employe.getPay() };

			employeList.add(employe);
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
				oos.writeObject(employeList);
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
					employeList = (LinkedList<Employe>) ois.readObject();
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
				Employe employe;
				while (i <= employeList.getSize())
				{
					employe = employeList.get(i);
					String employeType = Employe.CLASS_NAME;

					if (employe instanceof HourlyEmploye) employeType = HourlyEmploye.CLASS_NAME;

					if (employe instanceof Manager) employeType = Manager.CLASS_NAME;

					if (employe instanceof Salesman) employeType = Salesman.CLASS_NAME;

					Object[] data =
					{ i, employe.getName(), employeType, employe.getPay() };
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
