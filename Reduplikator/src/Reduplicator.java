import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

public class Reduplicator {

	private static final String DIR_TO_DELETE = "d:/_TO_DELETE/";
	private Map<Long, List<File>> files = new HashMap<Long, List<File>>();
	private ReduView view = new ReduView(this);
	private RTableModel model = new RTableModel();
	private RTableModel duplicatesModel = new RTableModel();

	public Reduplicator() throws IOException {
		refresh();
	}

	private void showDuplicates() {
		duplicatesModel = new RTableModel();
		duplicatesModel.addColumn("file");
		duplicatesModel.addColumn("size");
		for (List<File> list : files.values()) {
			if (list.size() > 1) {
				for (File file : list) {
					duplicatesModel.addRow(file);
				}
			}
		}
		view.getTableDuplicates().setModel(duplicatesModel);
	}

	private void showFiles() {
		model = new RTableModel();
		model.addColumn("file");
		model.addColumn("size");
		for (List<File> list : files.values()) {
			for (File file : list) {
				model.addRow(file);
			}
		}
		view.getTable().setModel(model);
	}

	private void addDirectory(File path) {
		File[] list = path.listFiles();
		if (list != null) {
			for (File file : list) {
				if (file.isDirectory()) {
					addDirectory(file);
				} else {
					addFile(file);
				}
			}
		}
	}

	private void addFile(File file) {
		if (files.containsKey(file.length())) {
			files.get(file.length()).add(file);
		} else {
			List<File> arrayList = new ArrayList<>();
			arrayList.add(file);
			files.put(file.length(), arrayList);
		}
	}

	public static void main(String[] args) throws IOException {
		new Reduplicator();
	}

	public File getSelectedFile() {
		JTable table = view.getTableDuplicates();
		File row = duplicatesModel.getRow(table.getSelectedRow());
		return row;
	}

	public void refresh() {
		files.clear();
		addDirectory(new File("g:/"));
		showFiles();
		showDuplicates();

		String info = model.getRowCount() + " files / " + duplicatesModel.getRowCount() + " duplicates / ";
		view.getLabelInfo().setText(info);
	}

	public void deleteFile() {
		File file = getSelectedFile();
		createDeleteDir();
		file.renameTo(new File(DIR_TO_DELETE + file.getName()));
		refresh();
	}

	public void deleteDir() {
		File file = getSelectedFile();
		File parent = file.getParentFile();

		createDeleteDir();
		String newPath = DIR_TO_DELETE + parent.getName();
		boolean renameTo = parent.renameTo(new File(newPath));
		System.out.println(newPath + " " + renameTo);
		refresh();
	}

	private void createDeleteDir() {
		File toDelete = new File(DIR_TO_DELETE);
		if (!toDelete.exists()) {
			toDelete.mkdirs();
		}
	}
}
