package photorenamer;

import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Madumitha Ravichandran
 * @author Cheryl Lao
 *
 */
public class Image implements Serializable {

	/** File that you get from the directory explorer **/
	private File imageFile;

	/** List of all the tags. **/
	private ArrayList<String> tags;

	/** Maps the timestamp to the image file at that point **/
	private HashMap<Date, File> log;

	/** Master list of all the tags across different images. **/
	public static ArrayList<String> masterTags = new ArrayList<String>();

	public String logPath = "C:/Users/owner/Documents/madu/Assignment2/group_0707/a2/Assignment2/src/photorenamer/imageLog.txt";

	/** latest name of the image **/
	private String imgName;

	/** The original name of the image **/
	private String originalName;

	/**
	 * Creates a new Image object
	 *
	 * @param imageFile
	 *            A file that represents the selected image file
	 * @param tags
	 *            An ArrayList of the tags that an Image has
	 * @param log
	 *            A HashMap of Timestamps to files
	 * @param imgName
	 *            The current name of the image, including any tags it may have
	 * @param originalName
	 *            The original name of the image, without tags
	 */
	public Image(File imageFile, ArrayList<String> tags, HashMap<Date, File> log, String imgName, String originalName) {

		this.imageFile = imageFile;
		this.tags = tags;
		this.log = log;
		this.imgName = imgName;
		this.originalName = originalName;
	}

	/**
	 * Gives this image's name
	 * 
	 * @return String The image's name
	 */
	public String getImgName() {
		return imgName;
	}

	/**
	 * Gives the list of the image's tags at the moment it was called
	 * 
	 * @return ArrayList<String> The image's present set of tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * Adds a new tag to the image Precondition: tag shouldn't have an '@'
	 * symbol
	 * 
	 * @param tag
	 *            the tag to be added to the image
	 * @throws FileNotFoundException
	 */
	public void addTag(String tag) throws FileNotFoundException {
		if (imageFile.exists()) {
			if (!(tags.contains(tag))) {
				String oldName = imageFile.getName();
				String path = imageFile.getAbsolutePath();
				String newName = imgName.substring(0, imgName.lastIndexOf(".")) + "@" + tag
						+ imgName.substring(imgName.lastIndexOf("."));
				String newPath = path.replace(oldName, newName);
				Boolean ok = imageFile.renameTo(new File(newPath));
				updateFileName(newName);
				if (ok) {
					tags.add(tag);

					if (!(masterTags.contains(tag))) {
						masterTags.add(tag);
					}
					
					System.out.println(" " + masterTags);
					// Change how the image is represented in the master list of
					// images

					for (int i = 0; i < ImageManager.images.size(); i++) {
						if (ImageManager.images.get(i).imgName == oldName) {
							// replace the old version of the object with a
							// newer updated one
							ImageManager.images.set(i, this);
						}

					}

					System.out.println("Successfully added tag" + oldName + " " + tag);

					File file = new File(logPath);
					FileWriter writer;
					try {
						writer = new FileWriter(file, true);
						PrintWriter printer = new PrintWriter(writer);
						printer.append("\n" + new Date().toString() + " ~ " + "Added \"" + tag + "\": " + oldName
								+ " -> " + newName);
						printer.close();
					} catch (IOException e) {
						System.out.println("Couldn't write to the log!");
					}

					// Reserialize the object
					String filePath = "C:/Users/owner/Documents/madu/Assignment2/group_0707/a2/Assignment2/src/photorenamer/imageObjects.ser";

					try {
						OutputStream file2 = new FileOutputStream(filePath);
						OutputStream buffer = new BufferedOutputStream(file2);
						ObjectOutput output = new ObjectOutputStream(buffer);
						// serialize the list of images
						output.writeObject(ImageManager.images);
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Something wrong in adding tag");
				}
			} else {
				System.out.println("Tag already exists");
			}
		}

		else {
			throw new FileNotFoundException(imageFile.getPath() + ": Image file does not exist");
		}
	}

	/**
	 * Updates the name of the file after adding or removing a tag and logs it
	 * 
	 * @param newname
	 *            the new name to be given to the image
	 */
	public void updateFileName(String newName) {
		String path = imageFile.getAbsolutePath();
		String newPath = path.replace(imageFile.getName(), newName);
		imageFile = new File(newPath);
		imgName = imageFile.getName();
		log.put(new Date(), this.imageFile);

		// Reserialize the array after the update
		try {
			ImageManager.reserializeArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Removes the given tag from the image's tags and the image's name
	 * 
	 * @param tag
	 *            the tag to be removed from the image
	 * @param removeFromMasterList
	 *            whether or not the tag should be removed from the master list
	 *            of all tags
	 * @throws FileNotFoundException
	 */
	public void removeTag(String tag, boolean removeFromMasterList) throws FileNotFoundException {
		if (removeFromMasterList) {
			masterTags.remove(tag);
		}
		removeTag(tag);
	}

	/**
	 * Removes the given tag from the image's tags and the image's name, does
	 * not remove the tag from the master list of all tags. If the tag doesn't
	 * exists, it doesn't do anything.
	 * 
	 * @param tag
	 *            the tag to be removed from the image
	 * @throws FileNotFoundException
	 */
	public void removeTag(String tag) throws FileNotFoundException {

		if (imageFile.exists()) {
			// Search for tag in imgName
			if (imgName.contains("@" + tag)) {
				String oldName = imageFile.getName();
				String path = imageFile.getAbsolutePath();
				String newName = imgName.replace("@" + tag, "");
				String newPath = path.replace(oldName, newName);
				Boolean ok = imageFile.renameTo(new File(newPath));
				if (ok) {
					System.out.println("Successfully removed" + tag + " " + oldName + "-> " + newName + " " + tag);
				} else {
					System.out.println("Something wrong in removing tag");
				}

				// Update the file name
				updateFileName(newName);
				tags.remove(tag);

				// Logging the change
				File file = new File(logPath);
				FileWriter writer;
				try {

					writer = new FileWriter(file, true);
					PrintWriter printer = new PrintWriter(writer);
					// FORMATTING THE DATE
					printer.append("\n" + new Date().toString() + " ~ " + "Removed \"" + tag + "\": " + oldName + " -> "
							+ newName);
					printer.close();
					writer.close();
				} catch (IOException e) {
					System.out.println("Couldn't write to the log!");
				}

			}

			else {
				System.out.println("Tag does not exist, so no changes were made");
			}
		} else {
			throw new FileNotFoundException("Image does not exist");
		}

	}

	/**
	 * Reverts the image's specifications to a particular time as chosen by the
	 * user
	 * 
	 * @param dates
	 *            the date to revert to
	 * 
	 */
	public void revertName(String dates) {

		Date date = new Date();

		for (Date d : log.keySet()) {
			if (d.toString().equals(dates)) {
				date = d;
			}
		}

		String oldName = imgName;
		// Update the file name to name

		imgName = log.get(date).getName();
		File newPath = log.get(date); // get the file at that time
		Boolean ok = imageFile.renameTo(newPath);
		if (ok) {
			System.out.println("Successfully reverted");

			// Log the change in the log file
			File file = new File(logPath);
			FileWriter writer;
			try {

				writer = new FileWriter(file, true);
				PrintWriter printer = new PrintWriter(writer);

				printer.append("\n" + new Date().toString() + " ~ " + "Reverted " + oldName + "to " + date + ": "
						+ oldName + " -> " + imgName);
				printer.close();

			} catch (IOException e) {
				System.out.println("Couldn't write to the log!");
			}

		} else {
			System.out.println("Something wrong in reverting to old file");
		}

		imageFile = log.get(date);

		log.put(date, imageFile);

		// Reserialize
		try {
			ImageManager.reserializeArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns a string version of an Image's log
	 * 
	 * @return String  The log in a formatted string
	 */
	public String displayImageLog() {
		String Log = imgName + "'s log: \n";

		for (Date key : log.keySet()) {
			Log += "\n" + key.toString() + "  " + log.get(key).toString();
		}
		return Log;
	}

	/**
	 * Returns the image name
	 * 
	 * @return String  The image's name
	 */
	public String toString() {
		return this.imgName;

	}

	/**
	 * Returns the original name of the image
	 * 
	 * @return String The original name of the image
	 */
	public String getOriginalName() {
		return this.originalName;

	}

	public File getImageFile() {
		// TODO Auto-generated method stub
		return this.imageFile;
	}

}