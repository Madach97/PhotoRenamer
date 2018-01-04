package photorenamer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;

/*
public class ImageDeserializer {
	public void deserialize(){
	Image e = null;
    try {
       FileInputStream fileIn = new FileInputStream("ImageObject.ser");
       ObjectInputStream in = new ObjectInputStream(fileIn);
       e = (Image) in.readObject();
       in.close();
       fileIn.close();
    }catch(IOException i) {
       i.printStackTrace();
       return;
    }catch(ClassNotFoundException c) {
       System.out.println("Image class not found");
       c.printStackTrace();
       return;
    }
	}

}
*/

import java.io.File;
import java.io.IOException;

public class ImageDeserializer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		deserialize();

	}
	
	/**
	 * Reads in the contents of the specified .ser file
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void deserialize() throws IOException, ClassNotFoundException {

		String path = "C:/Users/owner/Documents/madu/Assignment2/group_0707/a2/Assignment2/src/photorenamer/imageObjects.ser";

		ImageManager im = new ImageManager(path);

		System.out.println(im);

		// Writes the existing Student objects to file.
		// This data is serialized and written to file as a sequence of bytes.
		im.readFromFile(path);

		im.saveToFile(path);

	}
}