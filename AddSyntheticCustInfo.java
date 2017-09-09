import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;

/*	
* 	This program adds 3 pieces of random customer data to the Austin, TX B-Cycle Trip dataset 
* 	that was downloaded from the Austin, TX Open Data Portal.
*/
public class AddSyntheticCustInfo {

	public static void main(String args[]){
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			
			// To read the input file path that is passed from console
			fileReader = new FileReader(args[0]);
			bufferedReader = new BufferedReader(fileReader);
			
			// To write to the output file path that is passed from console
			fileWriter = new FileWriter(args[1]);
			bufferedWriter = new BufferedWriter(fileWriter);

			// Random number generator with new data arrays that contain possible values 
			Random rand = new Random();
			String[] ageRange = {"below 21","21 to 35","36 to 50","51 to 65","above 65"};
			String[] gender = {"male","female"};
			String[] paymentType = {"visa", "mastercard", "amex", "unionpay", "others"};
			
			// Read the line and add the 3 pieces of random customer data to it
			String currLine;
			while ((currLine = bufferedReader.readLine()) != null) {
				
				// Produce random customer data 
				String newInfo = "," + ageRange[rand.nextInt(ageRange.length)] + "," + gender[rand.nextInt(gender.length)] + "," + paymentType[rand.nextInt(paymentType.length)];
				
				// Write existing line with new random customer data 
				bufferedWriter.write(currLine + newInfo);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			// Clean up - close all the file objects 
			try {

				if (bufferedReader != null)
					bufferedReader.close();

				if (fileReader != null)
					fileReader.close();
				
				if (bufferedWriter != null)
					bufferedWriter.close();
				
				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
}