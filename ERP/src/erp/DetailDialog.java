/**
 * 
 */
package erp;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ibm.icu.text.NumberFormat;

import erp.Erp.EmployeType;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

/**
 * @author sirde
 * 
 */
public class DetailDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JButton okButton; // Composant Swing.

	private JTextField textFieldName;
	private JTextField textFieldID;
	private JTextField textFieldHourlyRate;
	private JTextField textFieldHours;
	private JTextField textFieldCommission;
	private JTextField textFieldSales;
	private JTextField textFieldSalary;
	private EmployeType employeType;
	private Box verticalBoxTextFields;
	private JLabel labelIndex;
	private Box verticalBoxLabel;
	private Box horizontalBoxIndex;
	private Box horizontalBoxEmployeType;
	private JLabel labelEmployeType;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Box horizontalBoxName;
	private Box horizontalBoxCommission;
	private Box horizontalBoxSales;
	private Box horizontalBoxSalary;
	private Box horizontalBoxHourlyRate;
	private Box horizontalBoxHours;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private JLabel labelName;
	private JLabel labelCommission;
	private JLabel labelSales;
	private JLabel labelSalary;
	private JLabel labelHourlyRate;
	private JLabel labelHours;
	private Component horizontalStrut_7;
	private JComboBox<String> comboBoxEmployeType;
	private boolean okPressed = false;

	/**
	 * Constructor used to add an element
	 * 
	 * @param id
	 *            The id of the element to add
	 */
	public DetailDialog(int id) {
		this(id, "", 0, 0, 0, 0, 0);
	}

	/**
	 * Constructeur utilisé pour éditer un champ
	 * 
	 * @param name
	 * @param id
	 * @param hourlyRate
	 * @param hours
	 * @param commission
	 * @param sales
	 * @param salary
	 * @wbp.parser.constructor
	 */
	public DetailDialog(int id, String name, double hourlyRate, double hours,
			double commission, double sales, double salary) {
		super((Frame) null, "Mon dialogue", true);

		setSize(300, 238);
		setLocationRelativeTo(null);

		java.awt.Container content = getContentPane();
		content.setLayout(new FlowLayout());

		verticalBoxLabel = Box.createVerticalBox();
		getContentPane().add(verticalBoxLabel);

		verticalBoxTextFields = Box.createVerticalBox();
		getContentPane().add(verticalBoxTextFields);

		horizontalBoxEmployeType = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxEmployeType);

		labelEmployeType = new JLabel(
				Messages.getString("DetailDialog.labelEmployeType.text")); //$NON-NLS-1$
		labelEmployeType.setPreferredSize(new Dimension(80, 20));
		horizontalBoxEmployeType.add(labelEmployeType);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBoxEmployeType.add(horizontalStrut_1);

		comboBoxEmployeType = new JComboBox<String>();

		horizontalBoxEmployeType.add(comboBoxEmployeType);
		comboBoxEmployeType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGUI();
			}

		});

		comboBoxEmployeType.setToolTipText(Messages
				.getString("Erp.comboBoxEmployeType.toolTipText")); //$NON-NLS-1$
		comboBoxEmployeType.setModel(new DefaultComboBoxModel<String>(
				new String[] { "HourlyEmploye", "Salesman", "Manager" }));

		horizontalBoxIndex = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxIndex);

		labelIndex = new JLabel(Messages.getString("erp.index.text")); //$NON-NLS-1$
		labelIndex.setPreferredSize(new Dimension(80, 14));
		horizontalBoxIndex.add(labelIndex);

		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBoxIndex.add(horizontalStrut_7);

		textFieldID = new JTextField();
		horizontalBoxIndex.add(textFieldID);
		textFieldID.setEditable(false);
		textFieldID.setToolTipText(Messages.getString("erp.index.text")); //$NON-NLS-1$
		textFieldID.setText(String.valueOf(id));

		horizontalBoxName = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxName);

		labelName = new JLabel(Messages.getString("erp.name.text")); //$NON-NLS-1$
		labelName.setPreferredSize(new Dimension(80, 14));
		horizontalBoxName.add(labelName);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBoxName.add(horizontalStrut);

		textFieldName = new JTextField();
		horizontalBoxName.add(textFieldName);
		textFieldName.setText(name);
		textFieldName.setColumns(10);

		horizontalBoxCommission = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxCommission);

		labelCommission = new JLabel(
				Messages.getString("Erp.textFieldCommission.toolTipText")); //$NON-NLS-1$
		labelCommission.setPreferredSize(new Dimension(80, 14));
		horizontalBoxCommission.add(labelCommission);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBoxCommission.add(horizontalStrut_2);

		textFieldCommission = new JFormattedTextField(
				NumberFormat.getInstance());
		horizontalBoxCommission.add(textFieldCommission);
		textFieldCommission.setToolTipText(Messages
				.getString("Erp.textFieldComission.text")); //$NON-NLS-1$
		textFieldCommission.setText(String.valueOf(commission));
		textFieldCommission.setColumns(10);

		horizontalBoxSales = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxSales);

		labelSales = new JLabel(Messages.getString("Erp.textFieldSales.text")); //$NON-NLS-1$
		labelSales.setPreferredSize(new Dimension(80, 14));
		horizontalBoxSales.add(labelSales);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBoxSales.add(horizontalStrut_3);

		textFieldSales = new JTextField();
		horizontalBoxSales.add(textFieldSales);
		textFieldSales.setToolTipText(Messages
				.getString("Erp.textFieldSales.toolTipText")); //$NON-NLS-1$
		textFieldSales.setText(String.valueOf(sales));
		textFieldSales.setColumns(10);

		horizontalBoxHourlyRate = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxHourlyRate);

		labelHourlyRate = new JLabel(
				Messages.getString("Erp.textFieldHourlyRate.text")); //$NON-NLS-1$
		labelHourlyRate.setPreferredSize(new Dimension(80, 14));
		horizontalBoxHourlyRate.add(labelHourlyRate);

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBoxHourlyRate.add(horizontalStrut_5);

		textFieldHourlyRate = new JFormattedTextField(
				NumberFormat.getInstance());
		horizontalBoxHourlyRate.add(textFieldHourlyRate);
		textFieldHourlyRate.setToolTipText(Messages
				.getString("Erp.textFieldHourlyRate.toolTipText")); //$NON-NLS-1$
		textFieldHourlyRate.setText(String.valueOf(hourlyRate));
		textFieldHourlyRate.setColumns(10);

		horizontalBoxHours = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxHours);

		labelHours = new JLabel(Messages.getString("Erp.textFieldHours.text")); //$NON-NLS-1$
		labelHours.setPreferredSize(new Dimension(80, 14));
		horizontalBoxHours.add(labelHours);

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBoxHours.add(horizontalStrut_6);

		textFieldHours = new JFormattedTextField(NumberFormat.getInstance());
		horizontalBoxHours.add(textFieldHours);
		textFieldHours.setToolTipText(Messages
				.getString("Erp.textFieldHours.toolTipText")); //$NON-NLS-1$
		textFieldHours.setText(String.valueOf(hours));
		textFieldHours.setColumns(10);

		horizontalBoxSalary = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxSalary);

		labelSalary = new JLabel(Messages.getString("Erp.textFieldSalary.text")); //$NON-NLS-1$
		labelSalary.setPreferredSize(new Dimension(80, 14));
		horizontalBoxSalary.add(labelSalary);

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBoxSalary.add(horizontalStrut_4);

		textFieldSalary = new JTextField();
		textFieldSalary.setEditable(false);
		horizontalBoxSalary.add(textFieldSalary);
		textFieldSalary.setToolTipText(Messages
				.getString("Erp.textFieldSalary.toolTipText"));
		textFieldSalary.setText(String.valueOf(salary));
		textFieldSalary.setColumns(10);

		okButton = new JButton("OK");

		/* Desactivé car créer des lenteurs
		getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					setVisible(false);
			}
		});

		comboBoxEmployeType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					setVisible(false);
			}
		});
		
		*/

		verticalBoxTextFields.add(okButton);
		okButton.addActionListener(this);

		updateGUI();
	}

	private void updateGUI() {
		// TODO switch to java 7 to use switch instead of if
		if (comboBoxEmployeType.getSelectedItem() == "HourlyEmploye") {
			horizontalBoxSales.setVisible(false);
			horizontalBoxCommission.setVisible(false);
			horizontalBoxHourlyRate.setVisible(true);
			horizontalBoxHours.setVisible(true);
			horizontalBoxSalary.setVisible(false);
			employeType = EmployeType.HOURLY_EMPLOYE;
		} else if (comboBoxEmployeType.getSelectedItem() == "Salesman") {
			horizontalBoxSales.setVisible(true);
			horizontalBoxCommission.setVisible(true);
			horizontalBoxHourlyRate.setVisible(true);
			horizontalBoxHours.setVisible(true);
			horizontalBoxSalary.setVisible(false);
			employeType = EmployeType.SALESMAN;
		} else if (comboBoxEmployeType.getSelectedItem() == "Manager") {
			horizontalBoxSales.setVisible(false);
			horizontalBoxCommission.setVisible(false);
			horizontalBoxHourlyRate.setVisible(false);
			horizontalBoxHours.setVisible(false);
			horizontalBoxSalary.setVisible(true);
			employeType = EmployeType.MANAGER;
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if(textFieldName.getText() == "")
				JOptionPane.showMessageDialog(getContentPane(), "Error, all fields are not correctly filled.");
			else
			{
				okPressed = true;
				setVisible(false);
			}

		} // met fin au dialogue et rend la boite invisible.
	}

	public String getTextFieldHours() {
		return textFieldHours.getText();
	}

	public String getTextFieldName() {
		return textFieldName.getText();
	}

	public String getTextFieldID() {
		return textFieldID.getText();
	}

	public String getTextFieldHourlyRate() {
		return textFieldHourlyRate.getText();
	}

	public String getTextFieldCommission() {
		return textFieldCommission.getText();
	}

	public String getTextFieldSales() {
		return textFieldSales.getText();
	}

	public String getTextFieldSalary() {
		return textFieldSalary.getText();
	}

	public EmployeType getEmployeType() {
		return employeType;
	}

	public boolean getTextFieldSalaryEditable() {
		return textFieldSalary.isEditable();
	}

	public void setTextFieldSalaryEditable(boolean editable) {
		textFieldSalary.setEditable(editable);
	}

	public boolean getOkPressed() {
		return okPressed;
	}
}
