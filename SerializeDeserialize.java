import java.io.*;

public class SerializeDeserialize {

	public void serializeToFile(String filename, Object object) {
		try {
			FileOutputStream file = new FileOutputStream(filename);

			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(object);

			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error : File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: File not open");
		}
	}

	public Object deSerializeFromFile(String filename) {
		Object object = null;
		try {
			FileInputStream file = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(file);

				// Method for deserialization of object
			
				object = in.readObject();

				in.close();
				file.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error : File not found");
		}
		return object;

	}
}
