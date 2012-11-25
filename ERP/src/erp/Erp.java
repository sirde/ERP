package erp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

import javax.swing.JRadioButtonMenuItem;

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

	private Employe employe;
	private LinkedList employeList;

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

	private void openAddDialog() {

		DetailDialog addDialog = new DetailDialog(employeList.getSize() + 1);
		addDialog.setVisible(true);

		if (addDialog.getOkPressed()) {

			String name = addDialog.getTextFieldName();
			double hours = Double.valueOf(addDialog.getTextFieldHours());
			double hourlyRate = Double.valueOf(addDialog
					.getTextFieldHourlyRate());

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

			Object[] data = { employeList.getSize() + 1, name, employeType,
					employe.getPay() };

			employeList.add(employe);
			tableModel.addRow(data);
		}

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
		menuOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					int counter = employeList.getSize();
					while (counter > 0) {
						tableModel.removeRow(0);
						counter--;
					}

					FileInputStream fis = new FileInputStream("C:/list.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					employeList = (LinkedList) ois.readObject();
					ois.close();

					int i = 1;
					while (i <= employeList.getSize()) {
						employe = employeList.get(i);
						EmployeType employeType = EmployeType.MANAGER;

						if (employe instanceof HourlyEmploye)
							employeType = EmployeType.HOURLY_EMPLOYE;

						Object[] data = { i, employe.getName(), employeType,
								employe.getPay() };
						tableModel.addRow(data);
						i++;
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mnFile.add(menuOpenFile);

		JMenuItem menuSaveFile = new JMenuItem(
				Messages.getString("Erp.menuSaveFile.arg0")); //$NON-NLS-1$

		menuSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File file = new File("C:/list.ser");
					if (!file.exists())
						file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(employeList);
					oos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		mnFile.add(menuSaveFile);

		JMenu mnOption = new JMenu(Messages.getString("Erp.mnOption.text")); //$NON-NLS-1$
		menuBar.add(mnOption);

		JRadioButtonMenuItem rdbtnmntmEnglish = new JRadioButtonMenuItem(
				Messages.getString("Erp.rdbtnmntmEnglish.text")); //$NON-NLS-1$
		mnOption.add(rdbtnmntmEnglish);

		JRadioButtonMenuItem rdbtnmntmFrench = new JRadioButtonMenuItem(
				Messages.getString("Erp.rdbtnmntmFrench.text")); //$NON-NLS-1$
		mnOption.add(rdbtnmntmFrench);

		employeList = new LinkedList();

		table = new JTable();
		table.setBounds(0, 0, 300, 0);

		JScrollPane pane = new JScrollPane(table);
		getContentPane().add(pane, BorderLayout.CENTER);

		String[] columnNames = { Messages.getString("erp.index.text"),
				Messages.getString("erp.name.text"),
				Messages.getString("erp.type.text"),
				Messages.getString("erp.salary.text") };

		tableModel = new DefaultTableModel(columnNames, 0);

		table.setModel(tableModel);

		JPanel GestionPanel = new JPanel();
		getContentPane().add(GestionPanel, BorderLayout.SOUTH);
		GestionPanel.setMaximumSize(new Dimension(32767, 600));

		JButton btnAdd = new JButton(Messages.getString("erp.btnAdd.text"));

		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER)
					openAddDialog();
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAddDialog();
			}
		});

		GestionPanel.add(btnAdd);

		JButton btnEdit = new JButton(Messages.getString("erp.btnEdit.text")); //$NON-NLS-1$
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRows().length != 1)
					JOptionPane.showMessageDialog(getContentPane(), "Error, you should select 1 line.");
				else {
					int index = (int) table.getValueAt(table.getSelectedRow(),
							0);
					employe = employeList.get(index);
					String name = employe.getName();
					double hourlyRate = 0;
					double hours = 0;
					double commission = 0;
					double sales = 0;
					double salary = 0;

					if (employe instanceof HourlyEmploye) {
						name = employe.getName();
						hourlyRate = ((HourlyEmploye) employe).getRate();
						hours = ((HourlyEmploye) employe).getHours();
					}

					DetailDialog editDialog = new DetailDialog(index, name,
							hourlyRate, hours, commission, sales, salary);
					editDialog.setVisible(true);

					if (editDialog.getOkPressed()) {

						if(!name.equals(editDialog.getTextFieldName()))
							;//TODO replace value
						
						//if(hours.equals(Double.valueOf(editDialog.getTextFieldHours()))
							;// TODO replace value
						
						hourlyRate = Double.valueOf(editDialog
								.getTextFieldHourlyRate());

						EmployeType employeType = editDialog.getEmployeType();

						switch (employeType) {
						case HOURLY_EMPLOYE:
							employe = new HourlyEmploye(name, hourlyRate, hours);
							break;

						case SALESMAN:
							break;

						case MANAGER:
							break;
						}

						Object[] data = { employeList.getSize() + 1, name,
								employeType, employe.getPay() };

						employeList.replace(index, employe);
						tableModel.addRow(data);
					}
				}
			}

		});
		GestionPanel.add(btnEdit);

		JButton btnDelete = new JButton(
				Messages.getString("erp.btnDelete.text")); //$NON-NLS-1$
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRows().length < 1)
					JOptionPane.showMessageDialog(getContentPane(), "Error, you should select at least 1 line.");
				int[] selection;
				int index;
				while (table.getSelectedRows().length > 0) {
					selection = table.getSelectedRows();
					index = ((int) table.getValueAt(selection[0], 0)) - 1;

					tableModel.removeRow(selection[0]);
					employeList.delete(index);
				}
				table.clearSelection();

				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(i + 1, i, 0);
				}

			}
		});

		GestionPanel.add(btnDelete);
	}
}
