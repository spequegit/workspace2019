import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ReduView extends JFrame {

	private static final Font FONT2 = new Font("Verdana", 9, 9);
	private static final long serialVersionUID = 1L;
	private JTable table = new JTable();
	private JTable tableDuplicates = new JTable();
	private JScrollPane jScrollPane = new JScrollPane(table);
	private JScrollPane jScrollPaneDuplicates = new JScrollPane(tableDuplicates);
	private JPanel panel = new JPanel(new GridLayout(8, 1));
	private JTextField fieldName = new JTextField();
	private JTextField fieldDir = new JTextField();
	private JButton buttonDelFile = new JButton("delete file");
	private JButton buttonDelDir = new JButton("delete directory");
	private JButton buttonRefresh = new JButton("refresh");
	private JLabel labelInfo = new JLabel();
	private Reduplicator controller;

	public ReduView(Reduplicator controller) {
		this.controller = controller;
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jScrollPane, BorderLayout.NORTH);
		jScrollPane.setPreferredSize(new Dimension(100, 100));
		getContentPane().add(jScrollPaneDuplicates, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(200, 200));
		fieldName.setColumns(50);
		getFieldDir().setColumns(50);
		panel.add(getLabelInfo());
		panel.add(getFieldDir());
		panel.add(buttonDelDir);
		panel.add(fieldName);
		panel.add(buttonDelFile);
		panel.add(buttonRefresh);
		getContentPane().add(panel, BorderLayout.SOUTH);

		setFont();
		addListeners();
	}

	private void addListeners() {
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.refresh();
			}
		});
		buttonDelFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteFile();
			}
		});
		buttonDelDir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteDir();
			}
		});

		tableDuplicates.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				File row = controller.getSelectedFile();
				getFieldName().setText(row.getName());
				getFieldDir().setText(row.getParent());
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		tableDuplicates.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				File row = controller.getSelectedFile();
				getFieldName().setText(row.getName());
				getFieldDir().setText(row.getParent());
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

	private void setFont() {
		getLabelInfo().setFont(FONT2);
		fieldName.setFont(FONT2);
		buttonDelFile.setFont(FONT2);
		buttonRefresh.setFont(FONT2);
		getFieldDir().setFont(FONT2);
		buttonDelDir.setFont(FONT2);
		table.setFont(FONT2);
		tableDuplicates.setFont(FONT2);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTable getTableDuplicates() {
		return tableDuplicates;
	}

	public void setTableDuplicates(JTable tableDuplicates) {
		this.tableDuplicates = tableDuplicates;
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JLabel getLabelInfo() {
		return labelInfo;
	}

	public void setLabelInfo(JLabel labelInfo) {
		this.labelInfo = labelInfo;
	}

	public JTextField getFieldDir() {
		return fieldDir;
	}

	public void setFieldDir(JTextField fieldDir) {
		this.fieldDir = fieldDir;
	}
}
