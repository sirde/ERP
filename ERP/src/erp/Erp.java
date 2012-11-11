package erp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class Erp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 472, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel ShowingPanel = new JPanel();
		ShowingPanel.setBounds(97, 11, 279, 182);
		contentPane.add(ShowingPanel);
		
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		
		
		JTable table = new JTable(data, columnNames);
		
		
		table = new JTable();
		ShowingPanel.add(table);

		JPanel GestionPanel = new JPanel();
		GestionPanel.setBounds(97, 231, 279, 74);
		contentPane.add(GestionPanel);
		

		JButton btnAdd = new JButton(Messages.getString("TestGui.btnAdd.text"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO create dialog to add an employes
			}
		});
		
		GestionPanel.add(btnAdd);

		JButton btnEdit = new JButton(
				Messages.getString("TestGui.btnEdit.text")); //$NON-NLS-1$
		GestionPanel.add(btnEdit);

		JButton btnDelete = new JButton(
				Messages.getString("TestGui.btnDelete.text")); //$NON-NLS-1$
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO create dialog to delete the selected employe
			}
		});
		GestionPanel.add(btnDelete);

		JPanel filePanel = new JPanel();
		filePanel.setBounds(97, 309, 279, 74);
		contentPane.add(filePanel);

		JButton btnSave = new JButton(
				Messages.getString("TestGui.btnSave.text")); //$NON-NLS-1$
		filePanel.add(btnSave);

		JButton btnOpen = new JButton(
				Messages.getString("TestGui.btnOpen.text")); //$NON-NLS-1$
		filePanel.add(btnOpen);
	}
}
