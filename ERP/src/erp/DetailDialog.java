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

import java.text.NumberFormat;
import erp.Erp.EmployeType;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

import staff.Messages;

import java.awt.Component;
import java.awt.Dimension;

/**
 * @author sirde
 * 
 */
public class DetailDialog extends JDialog implements ActionListener
{

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
	public DetailDialog(int id)
	{
		this(EmployeType.HOURLY_EMPLOYE, id, "", 0, 0, 0, 0, 0);

	}

	/**
	 * Constructor used to edit an employe
	 * 
	 * @param employeType
	 * @param name
	 * @param id
	 * @param hourlyRate
	 * @param hours
	 * @param commission
	 * @param sales
	 * @param salary
	 * @wbp.parser.constructor
	 */
	public DetailDialog(EmployeType employeType, int id, String name,
			double hourlyRate, double hours, double commission, double sales,
			double salary)
	{
		super((Frame) null, "Mon dialogue", true);

		NumberFormat numberFormat = NumberFormat.getNumberInstance(); // in
																		// javax.swing.text
		numberFormat.setMaximumFractionDigits(2);
		NumberFormatter nf = new NumberFormatter(numberFormat);
		nf.setMinimum(0.0);
		nf.setMaximum(999999999.9);
		nf.setAllowsInvalid(false);

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

		comboBoxEmployeType.setToolTipText(Messages
				.getString("Erp.comboBoxEmployeType.toolTipText")); //$NON-NLS-1$
		comboBoxEmployeType.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "HourlyEmploye", "Salesman", "Manager" }));

		comboBoxEmployeType.setSelectedIndex(employeType.ordinal());

		comboBoxEmployeType.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				updateGUI();
			}

		});

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

		textFieldCommission = new JFormattedTextField(nf);
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

		textFieldSales = new JFormattedTextField(nf);
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

		textFieldHourlyRate = new JFormattedTextField(nf);
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

		textFieldHours = new JFormattedTextField(nf);

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

		textFieldSalary = new JFormattedTextField(nf);
		horizontalBoxSalary.add(textFieldSalary);
		textFieldSalary.setToolTipText(Messages
				.getString("Erp.textFieldSalary.toolTipText"));
		textFieldSalary.setText(String.valueOf(salary));
		textFieldSalary.setColumns(10);

		okButton = new JButton("OK");

		/*
		 * Desactivé car créer des lenteurs getContentPane().addKeyListener(new
		 * KeyAdapter() {
		 * 
		 * @Override public void keyTyped(KeyEvent e) { if (e.getKeyChar() ==
		 * KeyEvent.VK_ENTER) setVisible(false); } });
		 * 
		 * comboBoxEmployeType.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyTyped(KeyEvent e) { if (e.getKeyChar() ==
		 * KeyEvent.VK_ENTER) setVisible(false); } });
		 */

		verticalBoxTextFields.add(okButton);
		okButton.addActionListener(this);

		updateGUI();
	}

	private void updateGUI()
	{
		if (comboBoxEmployeType.getSelectedItem() == "HourlyEmploye")
		{
			horizontalBoxSales.setVisible(false);
			horizontalBoxCommission.setVisible(false);
			horizontalBoxHourlyRate.setVisible(true);
			horizontalBoxHours.setVisible(true);
			horizontalBoxSalary.setVisible(false);
			employeType = EmployeType.HOURLY_EMPLOYE;
		}
		else if (comboBoxEmployeType.getSelectedItem() == "Salesman")
		{
			horizontalBoxSales.setVisible(true);
			horizontalBoxCommission.setVisible(true);
			horizontalBoxHourlyRate.setVisible(true);
			horizontalBoxHours.setVisible(true);
			horizontalBoxSalary.setVisible(false);
			employeType = EmployeType.SALESMAN;
		}
		else if (comboBoxEmployeType.getSelectedItem() == "Manager")
		{
			horizontalBoxSales.setVisible(false);
			horizontalBoxCommission.setVisible(false);
			horizontalBoxHourlyRate.setVisible(false);
			horizontalBoxHours.setVisible(false);
			horizontalBoxSalary.setVisible(true);
			employeType = EmployeType.MANAGER;
		}

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == okButton)
		{
			if (textFieldName.getText().equals("")) JOptionPane
					.showMessageDialog(getContentPane(),
							"Error, a name is required.");
			else
			{
				okPressed = true;
				setVisible(false);
			}

		} // met fin au dialogue et rend la boite invisible.
	}

	/**
	 * @return hours
	 */
	public String getTextFieldHours()
	{
		return textFieldHours.getText();
	}

	/**
	 * @return name
	 */
	public String getTextFieldName()
	{
		return textFieldName.getText();
	}

	/**
	 * @return index
	 */
	public String getTextFieldID()
	{
		return textFieldID.getText();
	}

	/**
	 * @return Hourly rate
	 */
	public String getTextFieldHourlyRate()
	{
		return textFieldHourlyRate.getText();
	}

	/**
	 * @return Commission
	 */
	public String getTextFieldCommission()
	{
		return textFieldCommission.getText();
	}

	/**
	 * @return Sales
	 */
	public String getTextFieldSales()
	{
		return textFieldSales.getText();
	}

	/**
	 * @return Salary
	 */
	public String getTextFieldSalary()
	{
		return textFieldSalary.getText();
	}

	/**
	 * @return Employe Type
	 */
	public EmployeType getEmployeType()
	{
		return employeType;
	}

	/**
	 * @return if ok was pressed
	 */
	public boolean getOkPressed()
	{
		return okPressed;
	}
}
