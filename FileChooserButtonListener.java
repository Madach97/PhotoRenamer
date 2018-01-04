package photorenamer;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.io.*;

/**
 * The listener for the button to choose a directory. This is where most of the
 * work is done.
 */
public class FileChooserButtonListener implements ActionListener {

	/** The window the button is in. */
	private JFrame directoryFrame;
	/** The label for the full path to the chosen directory. */
	private JLabel directoryLabel;
	/** The file chooser to use when the user clicks. */
	private JFileChooser fileChooser;
	/** The area to use to display the nested directory contents. */
	private JTextArea textArea;

	public static Image currentImage;

	/**
	 * An action listener for window dirFrame, displaying a file path on
	 * dirLabel, using fileChooser to choose a file.
	 *
	 * @param dirFrame
	 *            the main window
	 * @param dirLabel
	 *            the label for the directory path
	 * @param fileChooser
	 *            the file chooser to use
	 */
	public FileChooserButtonListener(JFrame dirFrame, JLabel dirLabel, JTextArea textArea, JFileChooser fileChooser) {
		this.directoryFrame = dirFrame;
		this.directoryLabel = dirLabel;
		this.textArea = textArea;
		this.fileChooser = fileChooser;
	}

	/**
	 * Handles the user clicking on the open button.
	 *
	 * @param e
	 *            the event object
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		int returnVal = fileChooser.showOpenDialog(directoryFrame.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();

			if (file.exists()) {
				directoryLabel.setText("Selected File" + file.getAbsolutePath());

				// Make an image object out of that file and serialize it
				currentImage = new Image(file, extractExistingTags(file), new HashMap<Date, File>(), file.getName(),
						file.getName());

				this.textArea.setText("Go back to previous window");

				try {
					ImageDeserializer.deserialize();
					
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				// Checking that the object is not already in the array
				boolean inArray = false;
				for (Image image : ImageManager.images) {
					if (image.getImgName().equals(currentImage.getImgName())) {
						inArray = true;
					}
				}
				//Add the image object if it's not in the array already
				if (inArray == false) {
					ImageManager.images.add(currentImage);
				}
				
				//Update the .ser file
				try {
					ImageManager.reserializeArray();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				ArrayList<Image> tester = ImageManager.getImages();
				System.out.println(tester.size());

				for (int i = 0; i < tester.size(); i++) {
					System.out.println("Added " + i + " " + tester.get(i));
				}

			}
		} else {
			directoryLabel.setText("No Path Selected");
		}
	}

	/**
	 * Returns a list the the tags found in the file name
	 * 
	 * @param file
	 * @return ArrayList<String>  ArrayList of tag Strings
	 */
	public ArrayList<String> extractExistingTags(File file) {
		String fileName = file.getName();
		ArrayList<String> tags = new ArrayList<String>();

		int start = 0;
		int end = 0;

		for (int i = 0; i < fileName.length(); i++) {
			// Slicing out each individual tag
			if (fileName.substring(i) == "@") {
				start = i;
				end = i;
				while (fileName.substring(i + 1) != "@") {
					end += 1;
				}
				tags.add(fileName.substring(start, end));
			}
		}
		return tags;

	}

}
