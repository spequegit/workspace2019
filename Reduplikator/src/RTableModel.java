import java.io.File;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class RTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<File> list = new ArrayList<>();

	public File getRow(int row) {
		return list.get(row);
	}

	public void addRow(File file) {
		list.add(file);
		super.addRow(new String[] { file.getAbsolutePath(), file.length() + " bytes" });
	}
}
