import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class LunchGroup {

	// Hash table to store employee id as key and name as value
	Hashtable<Integer, String> employees = new Hashtable<>();

	// File name to serialize employee table
	static String serializeFilename = "EmployeeTable";

	// File name to store the output after lunch groups are created
	static String lunchGroupFileName = "LunchGroup.txt";

	public void serialize() {
		SerializeDeserialize serDeser = new SerializeDeserialize();
		serDeser.serializeToFile(serializeFilename, employees);
	}

	@SuppressWarnings("unchecked")
	public LunchGroup() {

		// Deserialize employee table
		File file = new File(serializeFilename);
		if (file.isFile()) {
			SerializeDeserialize serDeser = new SerializeDeserialize();
			employees = (Hashtable<Integer, String>) serDeser.deSerializeFromFile(serializeFilename);
		}
		if (employees == null) {
			employees = new Hashtable<>();
		}
	}

	/*
	 * Get employees from inputFile. This is a one time process.
	 */
	public LunchGroup(String inputFile) {

		String name = null;
		int employeeIDCounter = 1;
		try {
			if (!inputFile.isEmpty() && !inputFile.equals("")) {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				while ((name = br.readLine()) != null) {
					if(!name.isEmpty()){
						employees.put(employeeIDCounter++, name);
					}
				}
				System.out.println("\n Employees entered from input file successfully");
				// serialize();
			}
		} catch (FileNotFoundException e) {
			// Do nothing
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getEmployeeCount() {
		return employees.size();
	}

	public int getMaxEmployeeID() {
		int max = 0;
		if (employees != null) {
			for (Integer i : employees.keySet()) {
				if (i > max) {
					max = i;
				}
			}
		}
		return max;
	}

	public void addNewEmployee(String input) {
		String[] names = input.split(",");
		for (int i = 0; i < names.length; i++) {
			if (names[i] != null) {
				employees.put(getMaxEmployeeID() + 1, names[i].trim());
				System.out.println("\nEmployee " + names[i].trim() + " added sucessfully");
			}
		}

		// serialize();
	}

	public void deleteEmployee(Integer empID) {
		String name = employees.get(empID);
		if (name != null) {
			employees.remove(empID);
			serialize();
			System.out.println("\nEmployee " + name + " removed sucessfully");
		} else {
			System.out.println("\nEmployee " + empID + " not found");
		}
	}

	/*
	 * 1. Get the list of employee ids 2. shuffle the list randomly 3. Split the
	 * list in groups of 5 4. If one group has <3 members add from another group
	 */
	public void createLunchGroup() {

		if(employees.isEmpty()) {
			System.out.println("\nThere are no employees in this company.");
			return;
		}
			List<Integer> list = new ArrayList<>();
			list.addAll(employees.keySet());
			Collections.shuffle(list);

			/*
			 * for(Integer i : list) { System.out.print(employees.get(i)+", "); }
			 * System.out.print("________\n");
			 */

			int numOfGroups = list.size() / 5; // number of groups with 5 members
			int extra = list.size() % 5; // group with <5 members, if exists.
			int i = 0, currGroup = 1;

			try {
				@SuppressWarnings("resource")
				BufferedWriter bw = new BufferedWriter(new FileWriter(lunchGroupFileName));

				// Handle group with <3 members. This will the first group, if exists.
				if (extra != 0 && extra < 3) {
					bw.write("Group " + currGroup + " : ");
					System.out.print("Group " + currGroup + " : ");
					for (; i < (list.size() < 3 ? list.size() : 3); i++) {
						bw.write(employees.get(list.get(i)) + "(" + list.get(i) + ")");
						System.out.print(employees.get(list.get(i)) + "(" + list.get(i) + ")");
						if (i < 2  && i < (list.size() - 1)) {
							bw.write(", ");
							System.out.print(", ");
						}
					}
					currGroup++;
					bw.write("\n");
					System.out.print("\n");
				}
				/*
				 * if 1 group has 3 or 4 members and rest all have 5, we add that 3 or 4 member
				 * group to numOfGroups count
				 */
				if (extra > 0) {
					numOfGroups++;
				}
				// Handle groups with >=3 members
				while (currGroup <= numOfGroups) {
					int counter = 0;
					bw.write("Group " + currGroup + " : ");
					System.out.print("Group " + currGroup + " : ");
					while (counter < 5 && i < list.size()) {
						bw.write(employees.get(list.get(i)) + "(" + list.get(i) + ")");
						System.out.print(employees.get(list.get(i)) + "(" + list.get(i) + ")");
						if (counter < 4 && i < (list.size() - 1)) {
							bw.write(", ");
							System.out.print(", ");
						}
						counter++;
						i++;
					}
					currGroup++;
					bw.write("\n");
					System.out.print("\n");
				}
				System.out.print("\n");
				bw.close();

				System.out.println("\nLunch groups can also be found in "
						+ Paths.get(".").toAbsolutePath().normalize().toString() + "/" + lunchGroupFileName + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void displayAllEmployees() {

		System.out.println("\nTotal Number of Employees : " + employees.keySet().size());
		System.out.println("\nEmpID - Employee Name\n");

		for (Integer i : employees.keySet()) {
			System.out.println(i + " - " + employees.get(i));
		}
	}

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {

		LunchGroup group = null;
		Scanner scan = new Scanner(System.in);
		File file = new File(serializeFilename);
		boolean error = false;
		while (!file.isFile() && group == null) {
			if (!file.isFile()) {
				System.out.println("Enter filepath to input employees or press Enter.");

				String inputFile = scan.nextLine();

				if (inputFile.isEmpty()) {
					System.out.println("No input is entered");
					break;
				} else {
					File file2 = new File(inputFile);
					if (!file2.isFile()) {
						System.out.println("File path is wrong");
					} else if (group == null) {
						group = new LunchGroup(inputFile);
					}
				} 
			}		
		}
		if(group == null) {
			group = new LunchGroup();
		}
		while (true) {
			if (!error) {
				System.out.println("\n1. Create Lunch Group");
				System.out.println("2. Add New Employee");
				System.out.println("3. Delete an Employee");
				System.out.println("4. List all Employees");
				System.out.println("5. Exit");
				System.out.println("\nEnter any option(Eg : 1 or : 4) : ");

				int option = Integer.parseInt(scan.nextLine());

				switch (option) {

				case 1:
					group.createLunchGroup();
					break;
				case 2:
					System.out.println(
							"\nEnter employee name. For entering multiple employees list all names separated by ','");
					String input = scan.nextLine();
					if (!input.isEmpty()) {
						group.addNewEmployee(input);
					} else {
						System.out.println(
								"\nWarn: No new employee was added. Please enter the name of the employee to be added.");
					}
					break;
				case 3:
					System.out.println("\nEnter employee Id : ");
					try {
						int empID = Integer.parseInt(scan.nextLine());
						group.deleteEmployee(empID);
					} catch (NumberFormatException e) {
						System.out.println("\nError: Please enter a valid employee Id.");
					}
					break;
				case 4:
					group.displayAllEmployees();
					break;
				case 5:
					group.serialize();
					System.out.println("\nExited Successfully ");
					return;
				default:
					System.out.println("\nError: Invalid option. Please enter one of the given options (1,2,3 or 4).");
					break;
				}
			}
		}
	}

}

