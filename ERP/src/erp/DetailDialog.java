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
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

import staff.Employee;
import staff.HourlyEmployee;
import staff.Manager;
import staff.SalesMan;

import java.awt.Component;
import java.awt.Dimension;

/**
 * @author C.Gerber
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
	private String employeeType;
	private Box verticalBoxTextFields;
	private JLabel labelIndex;
	private Box verticalBoxLabel;
	private Box horizontalBoxIndex;
	private Box horizontalBoxEmployeeType;
	private JLabel labelEmployeeType;
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
	private JComboBox<String> comboBoxEmployeeType;
	private boolean okPressed = false;

	/**
	 * Constructor used to add an element
	 * 
	 * @param id
	 *            The id of the element to add
	 */
	public DetailDialog(int id)
	{
		this(Employee.CLASS_NAME, id, "", 0, 0, 0, 0, 0);

	}

	/**
	 * Constructor used to edit an employee
	 * 
	 * @param employeeType
	 * @param name
	 * @param id
	 * @param hourlyRate
	 * @param hours
	 * @param commission
	 * @param sales
	 * @param salary
	 * @wbp.parser.constructor
	 */
	public DetailDialog(String employeeType, int id, String name,
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

		horizontalBoxEmployeeType = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxEmployeeType);

		labelEmployeeType = new JLabel("Type d'employé");
		labelEmployeeType.setPreferredSize(new Dimension(100, 20));
		horizontalBoxEmployeeType.add(labelEmployeeType);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBoxEmployeeType.add(horizontalStrut_1);

		comboBoxEmployeeType = new JComboBox<String>();

		horizontalBoxEmployeeType.add(comboBoxEmployeeType);

		comboBoxEmployeeType.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ HourlyEmployee.CLASS_NAME, SalesMan.CLASS_NAME, Manager.CLASS_NAME }));

		comboBoxEmployeeType.setSelectedItem(employeeType);

		comboBoxEmployeeType.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				updateGUI();
			}

		});

		horizontalBoxIndex = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxIndex);

		labelIndex = new JLabel("Index");
		labelIndex.setPreferredSize(new Dimension(100, 14));
		horizontalBoxIndex.add(labelIndex);

		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBoxIndex.add(horizontalStrut_7);

		textFieldID = new JTextField();
		horizontalBoxIndex.add(textFieldID);
		textFieldID.setEditable(false);
		textFieldID.setText(String.valueOf(id));

		horizontalBoxName = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxName);

		labelName = new JLabel("Nom");
		labelName.setPreferredSize(new Dimension(100, 14));
		horizontalBoxName.add(labelName);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBoxName.add(horizontalStrut);

		textFieldName = new JTextField();
		horizontalBoxName.add(textFieldName);
		textFieldName.setText(name);
		textFieldName.setColumns(10);

		horizontalBoxCommission = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxCommission);

		labelCommission = new JLabel("Commission");
		labelCommission.setPreferredSize(new Dimension(100, 14));
		horizontalBoxCommission.add(labelCommission);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBoxCommission.add(horizontalStrut_2);

		textFieldCommission = new JFormattedTextField(nf);
		horizontalBoxCommission.add(textFieldCommission);
		textFieldCommission.setText(String.valueOf(commission));
		textFieldCommission.setColumns(10);

		horizontalBoxSales = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxSales);

		labelSales = new JLabel("Ventes");
		labelSales.setPreferredSize(new Dimension(100, 14));
		horizontalBoxSales.add(labelSales);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBoxSales.add(horizontalStrut_3);

		textFieldSales = new JFormattedTextField(nf);
		horizontalBoxSales.add(textFieldSales);
		textFieldSales.setText(String.valueOf(sales));
		textFieldSales.setColumns(10);

		horizontalBoxHourlyRate = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxHourlyRate);

		labelHourlyRate = new JLabel("Tarif horaire");
		labelHourlyRate.setPreferredSize(new Dimension(100, 14));
		horizontalBoxHourlyRate.add(labelHourlyRate);

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBoxHourlyRate.add(horizontalStrut_5);

		textFieldHourlyRate = new JFormattedTextField(nf);
		horizontalBoxHourlyRate.add(textFieldHourlyRate);
		textFieldHourlyRate.setText(String.valueOf(hourlyRate));
		textFieldHourlyRate.setColumns(10);

		horizontalBoxHours = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxHours);

		labelHours = new JLabel("Heure");
		labelHours.setPreferredSize(new Dimension(100, 14));
		horizontalBoxHours.add(labelHours);

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBoxHours.add(horizontalStrut_6);

		textFieldHours = new JFormattedTextField(nf);

		horizontalBoxHours.add(textFieldHours);
		textFieldHours.setText(String.valueOf(hours));
		textFieldHours.setColumns(10);

		horizontalBoxSalary = Box.createHorizontalBox();
		verticalBoxTextFields.add(horizontalBoxSalary);

		labelSalary = new JLabel("Salaire");
		labelSalary.setPreferredSize(new Dimension(100, 14));
		horizontalBoxSalary.add(labelSalary);

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBoxSalary.add(horizontalStrut_4);

		textFieldSalary = new JFormattedTextField(nf);
		horizontalBoxSalary.add(textFieldSalary);
		textFieldSalary.setText(String.valueOf(salary));
		textFieldSalary.setColumns(10);

		okButton = new JButton("OK");

		verticalBoxTextFields.add(okButton);
		okButton.addActionListener(this);

		updateGUI();
	}

	private void updateGUI()
	{
		if (comboBoxEmployeeType.getSelectedItem() == HourlyEmployee.CLASS_NAME)
		{
			horizontalBoxSales.setVisible(false);
			horizontalBoxCommission.setVisible(false);
			horizontalBoxHourlyRate.setVisible(true);
			horizontalBoxHours.setVisible(true);
			horizontalBoxSalary.setVisible(false);
			employeeType = HourlyEmployee.CLASS_NAME;
		}
		else if (comboBoxEmployeeType.getSelectedItem() == SalesMan.CLASS_NAME)
		{
			horizontalBoxSales.setVisible(true);
			horizontalBoxCommission.setVisible(true);
			horizontalBoxHourlyRate.setVisible(true);
			horizontalBoxHours.setVisible(true);
			horizontalBoxSalary.setVisible(false);
			employeeType = SalesMan.CLASS_NAME;
		}
		else if (comboBoxEmployeeType.getSelectedItem() == Manager.CLASS_NAME)
		{
			horizontalBoxSales.setVisible(false);
			horizontalBoxCommission.setVisible(false);
			horizontalBoxHourlyRate.setVisible(false);
			horizontalBoxHours.setVisible(false);
			horizontalBoxSalary.setVisible(true);
			employeeType = Manager.CLASS_NAME;
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
	 * @return Employee Type
	 */
	public String getEmployeeType()
	{
		return employeeType;
	}

	/**
	 * @return if ok was pressed
	 */
	public boolean getOkPressed()
	{
		return okPressed;
	}
}
