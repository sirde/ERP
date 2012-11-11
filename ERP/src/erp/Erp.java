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
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.JDesktopPane;

public class Erp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private static DefaultTableModel tableModel;
	private static int counter = 0;

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

		JPanel GestionPanel = new JPanel();
		getContentPane().add(GestionPanel, BorderLayout.SOUTH);
		GestionPanel.setMaximumSize(new Dimension(32767, 600));

		JButton btnAdd = new JButton(Messages.getString("erp.btnAdd.text"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO create dialog to add an employes

				Object[] data = { counter, "Kathy", "Employe", new Integer(5) };
				tableModel.addRow(data);
				counter++;

			}
		});

		GestionPanel.add(btnAdd);

		JButton btnEdit = new JButton(Messages.getString("erp.btnEdit.text")); //$NON-NLS-1$
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

		table = new JTable();
		table.setBounds(0, 0, 300, 0);

		JScrollPane pane = new JScrollPane(table);
		getContentPane().add(pane, BorderLayout.WEST);
		
		String[] columnNames = { Messages.getString("erp.index.text"),
				Messages.getString("erp.name.text"),
				Messages.getString("erp.type.text"),
				Messages.getString("erp.salary.text") };
		
		tableModel = new DefaultTableModel(columnNames, 0);

		table.setModel(tableModel);



		
	}
}
