package photorenamer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Create and show a Photo Renamer GUI and links to a directory explorer, which displays the contents of a
 * directory.
 * 
 * GUI component code generated using the GUI builder in NetBeans IDE and the directory explorer code is almost the same as the code from a1 part 2
 */
public class PhotoRenamerGUI extends javax.swing.JFrame {
	// Variables declaration for GUI components
	private javax.swing.JButton btnAddTag;
	private javax.swing.JButton btnAvailableTags;
	private javax.swing.JButton btnChooseFile;
	private javax.swing.JButton btnOpenFileLocation;
	private javax.swing.JButton btnRemoveTag;
	private javax.swing.JButton btnRevertName;
	private javax.swing.JButton btnViewTagLog;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField txtTag;

	/**
	 * Create and return the window for the PhotoRenamer
	 *
	 * @return the window for the photo renamer
	 */

	public static JFrame buildWindow() {

		JFrame directoryFrame = new JFrame("File Renamer");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpeg", "jpg", "png", "gif", "bmp"));

		JLabel directoryLabel = new JLabel("Select an image file");

		// Set up the area for the directory contents.
		JTextArea textArea = new JTextArea(15, 50);
		textArea.setEditable(true);

		// Put it in a scroll pane in case the output is long.
		JScrollPane scrollPane = new JScrollPane(textArea);

		// The directory choosing button.
		JButton openButton = new JButton("Choose Directory");
		openButton.setVerticalTextPosition(AbstractButton.CENTER);
		openButton.setHorizontalTextPosition(AbstractButton.LEADING); // aka
																		// LEFT,
																		// for
																		// left-to-right
																		// locales
		openButton.setMnemonic(KeyEvent.VK_D);
		openButton.setActionCommand("disable");

		// The listener for openButton.
		ActionListener buttonListener = new FileChooserButtonListener(directoryFrame, directoryLabel, textArea,
				fileChooser);

		openButton.addActionListener(buttonListener);

		// Put it all together.
		Container c = directoryFrame.getContentPane();
		c.add(directoryLabel, BorderLayout.PAGE_START);
		c.add(scrollPane, BorderLayout.CENTER);
		c.add(openButton, BorderLayout.PAGE_END);

		directoryFrame.pack();
		return directoryFrame;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Initializes the GUI components
	 *
	 */
	
	private void initComponents() {

		jMenuItem1 = new javax.swing.JMenuItem();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		btnChooseFile = new javax.swing.JButton();
		btnAddTag = new javax.swing.JButton();
		btnRemoveTag = new javax.swing.JButton();
		btnAvailableTags = new javax.swing.JButton();
		txtTag = new javax.swing.JTextField();
		btnViewTagLog = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		btnOpenFileLocation = new javax.swing.JButton();
		btnRevertName = new javax.swing.JButton();

		jMenuItem1.setText("jMenuItem1");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 204));

		jLabel1.setFont(new java.awt.Font("Adobe Caslon Pro", 1, 24)); // NOI18N
		jLabel1.setText("File Renamer");

		btnChooseFile.setText("Choose a photo");
		btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChooseFileActionPerformed(evt);
			}
		});

		btnAddTag.setText("Add Tag");
		btnAddTag.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddTagActionPerformed(evt);
			}
		});

		btnRemoveTag.setText("Remove Tag");
		btnRemoveTag.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRemoveTagActionPerformed(evt);
			}
		});

		btnAvailableTags.setText("View All Tags");
		btnAvailableTags.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAvailableTagsActionPerformed(evt);
			}
		});

		txtTag.setForeground(new java.awt.Color(102, 102, 102));
		txtTag.setText("Tag to Add or Remove");

		btnViewTagLog.setText("View Tag Log");
		btnViewTagLog.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnViewTagLogActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		btnOpenFileLocation.setText("Open File Location");
		btnOpenFileLocation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOpenFileLocationActionPerformed(evt);
			}
		});

		btnRevertName.setText("Revert Name");
		btnRevertName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRevertNameActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(95, 95, 95).addComponent(btnViewTagLog)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
										btnOpenFileLocation))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(63, 63, 63).addGroup(jPanel1Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										jPanel1Layout.createSequentialGroup().addComponent(btnChooseFile)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnAvailableTags))))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(170, 170, 170).addComponent(jLabel1))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(136, 136, 136)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txtTag, javax.swing.GroupLayout.PREFERRED_SIZE, 223,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createSequentialGroup().addComponent(btnAddTag)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnRemoveTag))))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(175, 175, 175)
								.addComponent(btnRevertName)))
						.addContainerGap(75, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnChooseFile).addComponent(btnAvailableTags))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(txtTag, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnAddTag).addComponent(btnRemoveTag))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnRevertName)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnViewTagLog).addComponent(btnOpenFileLocation))
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		pack();
	}

	private void btnChooseFileActionPerformed(java.awt.event.ActionEvent evt) {
		
		//Show the current window
		PhotoRenamerGUI.buildWindow().setVisible(true);

		// Once the user chooses a file, they'll be able to access all of these
		// features. To prevent null pointers, disable some buttons before they choose a file
		this.btnAddTag.setEnabled(true);
		this.btnRemoveTag.setEnabled(true);
		this.btnRevertName.setEnabled(true);
		this.btnViewTagLog.setEnabled(true);
		this.btnOpenFileLocation.setEnabled(true);
		this.txtTag.setEnabled(true);
	}
	
	private void btnAddTagActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			FileChooserButtonListener.currentImage.addTag(this.txtTag.getText());
		} 
		catch (FileNotFoundException e) {
			System.out.println("GUI Button Tag Addition Issue");
			e.printStackTrace();
		}

	}

	private void btnAvailableTagsActionPerformed(java.awt.event.ActionEvent evt) {

		String allTags = "";
		
		//Deserialize the image list so that you get the list of tags even before selecting an image
		try {
			ImageDeserializer.deserialize();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < Image.masterTags.size(); i++) {
			int j = i+1;
			allTags += "\n" + j + ") "+Image.masterTags.get(i);
		}

		this.jTextArea1.setText(allTags);
	}

	private void btnRemoveTagActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			FileChooserButtonListener.currentImage.removeTag(this.txtTag.getText());
		} catch (FileNotFoundException e) {
			System.out.println("GUI Button Tag Removal Issue");
			e.printStackTrace();
		}
	}

	private void btnRevertNameActionPerformed(java.awt.event.ActionEvent evt) {

		Date date;

			System.out.println(this.txtTag.getText());
			FileChooserButtonListener.currentImage.revertName(this.txtTag.getText());

	}

	private void btnViewTagLogActionPerformed(java.awt.event.ActionEvent evt) {

		this.jTextArea1.setText(FileChooserButtonListener.currentImage.displayImageLog());

	}

	private void btnOpenFileLocationActionPerformed(java.awt.event.ActionEvent evt) {
		
		if (this.txtTag.getText().equals("View Image")){

			//Viewing the file itself
			try {
				Desktop.getDesktop().open(FileChooserButtonListener.currentImage.getImageFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//Opening the file location
			try {
				Desktop.getDesktop().open(FileChooserButtonListener.currentImage.getImageFile().getParentFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

	public PhotoRenamerGUI() {
		initComponents();

		// Keeping these fields disabled until the user chooses an image file
		this.btnAddTag.setEnabled(false);
		this.btnRemoveTag.setEnabled(false);
		this.btnRevertName.setEnabled(false);
		this.btnViewTagLog.setEnabled(false);
		this.btnOpenFileLocation.setEnabled(false);
		this.txtTag.setEnabled(false);
	}

	/**
	 * Create and show a photo renamer choice screen, which displays options to choose and manipulate a file
	 *
	 * @param argsv
	 *            the command-line arguments.
	 */
	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PhotoRenamerGUI().setVisible(true);

			}
		});

	}

}
