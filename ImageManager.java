package photorenamer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

import java.io.*;

/**
 * Manages the saving and loading of Image objects.
 */
public class ImageManager {

	/** A list of Images to serialize. */
	public static ArrayList<Image> images;

	/**
	 * Creates a new ImageManager.
	 * 
	 * @param filePath
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ImageManager(String filePath) throws ClassNotFoundException, IOException {

		images = new ArrayList<Image>();

		// Reads serializable objects from file.
		// Populates the record list using stored data, if it exists.
		File file = new File(filePath);
		if (file.exists()) {
			readFromFile(filePath);
		} else {
			System.out.println("Created new .ser file");
			// Create the .ser file for later
			file.createNewFile();

		}
	}

	/**
	 * Reads in the Image arraylist from the .ser file and updates images
	 * 
	 * @param path
	 * @throws ClassNotFoundException
	 */
	public void readFromFile(String path) throws ClassNotFoundException {
		System.out.println("Read From .ser: " + path);
		try {
			InputStream file = new FileInputStream(path);

			InputStream buffer = new BufferedInputStream(file);

			ObjectInput input = new ObjectInputStream(buffer);

			// deserialize the list of images
			images = (ArrayList<Image>) input.readObject();

			input.close();
			
			//Updating the master list of tags
			for (Image image: images){
				for (String tag: image.getTags()){
					if(Image.masterTags.contains(tag) == false){
						Image.masterTags.add(tag);
					}
					
				}
				
			}

		} catch (IOException ex) {
			System.out.println("Can't read from ser file " + path);
		}
	}

	/**
	 * Writes the students to file at filePath.
	 * 
	 * @param filePath
	 *            the file to write the records to
	 * @throws IOException
	 */
	public void saveToFile(String filePath) throws IOException {

		OutputStream file = new FileOutputStream(filePath);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);

		// serialize the list of images
		output.writeObject(images);
		output.close();
	}

	/**
	 * Writes the images array to the .ser file again to update it
	 * 
	 * @throws IOException
	 */
	public static void reserializeArray() throws IOException {
		String filePath = "C:/Users/owner/Documents/madu/Assignment2/group_0707/a2/Assignment2/src/photorenamer/imageObjects.ser";
		OutputStream file = new FileOutputStream(filePath);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);

		// serialize the list of images
		output.writeObject(images);
		output.close();

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "";
		for (Image i : images) {
			result += i.toString() + "\n";
		}
		return result;
	}

	/**
	 * Returns the list of all images in the program
	 * 
	 * @return ArrayList<Image>  The master list of image objects in the program
	 */
	public static ArrayList<Image> getImages() {
		return images;

	}
}
