package erp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
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
	private JTextField textFieldName;
	private JTextField textFieldID;
	private JTextField textFieldHourlyRate;
	private JTextField textFieldHours;
	private JTextField textFieldCommission;
	private JTextField textFieldSales;
	private JTextField textFieldSalary;
	private static EmployeType employeType;
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

		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setToolTipText(Messages
				.getString("Erp.textFieldID.toolTipText")); //$NON-NLS-1$
		textFieldID.setText(Messages.getString("erp.index.text")); //$NON-NLS-1$
		panelInput.add(textFieldID);

		final JComboBox<String> comboBoxEmployeType = new JComboBox<String>();
		comboBoxEmployeType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// TODO switch to java 7 to use switch instead of if
				if (comboBoxEmployeType.getSelectedItem() == "HourlyEmploye") {
					textFieldSales.setVisible(false);
					textFieldCommission.setVisible(false);
					textFieldHourlyRate.setVisible(true);
					textFieldHours.setVisible(true);
					employeType = EmployeType.HOURLY_EMPLOYE;
				} else if (comboBoxEmployeType.getSelectedItem() == "Salesman") {
					textFieldSales.setVisible(true);
					textFieldCommission.setVisible(true);
					textFieldHourlyRate.setVisible(true);
					textFieldHours.setVisible(true);
					employeType = EmployeType.SALESMAN;
				} else if (comboBoxEmployeType.getSelectedItem() == "Manager") {
					textFieldSales.setVisible(false);
					textFieldCommission.setVisible(false);
					textFieldHourlyRate.setVisible(false);
					textFieldHours.setVisible(false);
					employeType = EmployeType.MANAGER;
				}

			}
		});
		comboBoxEmployeType.setToolTipText(Messages
				.getString("Erp.comboBoxEmployeType.toolTipText")); //$NON-NLS-1$
		comboBoxEmployeType.setModel(new DefaultComboBoxModel<String>(
				new String[] { "HourlyEmploye", "Salesman", "Manager" }));
		panelInput.add(comboBoxEmployeType);

		textFieldName = new JTextField();
		textFieldName.setText(Messages.getString("erp.name.text")); //$NON-NLS-1$
		panelInput.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldCommission = new JFormattedTextField(
				NumberFormat.getInstance());
		textFieldCommission.setToolTipText(Messages.getString("Erp.textFieldCommission.toolTipText")); //$NON-NLS-1$
		textFieldCommission.setText(Messages
				.getString("Erp.textFieldComission.text")); //$NON-NLS-1$
		textFieldCommission.setColumns(10);
		panelInput.add(textFieldCommission);

		textFieldSales = new JTextField();
		textFieldSales.setToolTipText(Messages.getString("Erp.textFieldSales.toolTipText")); //$NON-NLS-1$
		textFieldSales.setText(Messages.getString("Erp.textFieldSales.text")); //$NON-NLS-1$
		textFieldSales.setColumns(10);
		panelInput.add(textFieldSales);

		textFieldSalary = new JTextField();
		textFieldSalary.setToolTipText(Messages.getString("Erp.textFieldSalary.toolTipText")); //$NON-NLS-1$
		textFieldSalary.setEditable(false);
		textFieldSalary.setText(Messages.getString("Erp.textFieldSalary.text")); //$NON-NLS-1$
		textFieldSalary.setColumns(10);
		panelInput.add(textFieldSalary);
		
				textFieldHourlyRate = new JFormattedTextField(
						NumberFormat.getInstance());
				textFieldHourlyRate.setToolTipText(Messages
						.getString("Erp.textFieldHourlyRate.toolTipText")); //$NON-NLS-1$
				textFieldHourlyRate.setText(Messages
						.getString("Erp.textFieldHourlyRate.text")); //$NON-NLS-1$
				textFieldHourlyRate.setColumns(10);
				panelInput.add(textFieldHourlyRate);
				
						textFieldHours = new JFormattedTextField(
								NumberFormat.getInstance());
						textFieldHours.setToolTipText(Messages.getString("Erp.textFieldHours.toolTipText")); //$NON-NLS-1$
						textFieldHours.setText(Messages.getString("Erp.textFieldHours.text")); //$NON-NLS-1$
						textFieldHours.setColumns(10);
						panelInput.add(textFieldHours);

		JPanel GestionPanel = new JPanel();
		getContentPane().add(GestionPanel, BorderLayout.SOUTH);
		GestionPanel.setMaximumSize(new Dimension(32767, 600));

		JButton btnAdd = new JButton(Messages.getString("erp.btnAdd.text"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO create dialog to add an employes

				textFieldID.setText(Integer.toString(counter));

				String name = textFieldName.getText();
				double hourlyRate = Double.valueOf(textFieldHourlyRate
						.getText());
				double hours = Double.valueOf(textFieldHours.getText());

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
				textFieldSalary.setText(Double.toString(salary));

				Object[] data = { counter, name, employeType, employe.getPay() };
				tableModel.addRow(data);
				counter++;

			}
		});

		GestionPanel.add(btnAdd);

		JButton btnEdit = new JButton(Messages.getString("erp.btnEdit.text")); //$NON-NLS-1$
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JOptionPane optionPane = new JOptionPane("Press it",
						JOptionPane.QUESTION_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION);
				JOptionPane.showMessageDialog(getContentPane(),
						"Eggs are not supposed to be green.");
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
