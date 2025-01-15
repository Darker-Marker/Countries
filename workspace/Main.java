//By:Parker Lasko
//1/14/25
//this program quizzes users on countrys by highlighting the country they are to guess on a global map

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Main 
{

  // array of 10 Country objects
  private Country[] countryArray = new Country[10];  
  // index of current shown country
  private int index = 0;

  // GUI elements
  private JFrame jFrame = new JFrame("Countries");
  private ImageIcon img;
  private JLabel imageLabel;
  private JLabel outputLabel;
  private JTextField input;
  
  public static void main(String[] args) {
    // Create the GUI
    Main gui = new Main();
    gui.loadCountries();
    gui.showCountry();
  }

  /* loadCountries() reads in the data from the countries-data.csv file and fills in the countryArray with data. You need to add the loop that reads in the country data into the array. */
//  Pre-condition: have a data set of countries cataloged in the correct area
//   possible limitations on input: information about countries isnt in the correct order
//  Post-condition: fills in the countryArray with data

  public void loadCountries() 
  {
    // Open the data file - do not change
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");
    Scanner scan = null;
    try {
      scan = new Scanner(file);
    } catch(FileNotFoundException e) { 
        System.out.println("File not found");     
    }
    
    // Write a for loop that goes through the countryArray.
    // for(int i ....) {
    // Do the following inside the loop
    for (int i = 0; i < countryArray.length; i++)
    {
          String input = scan.nextLine();
     String[] data = input.split(",");
     System.out.println("Read in " + data[2]);
    

      countryArray[i]= new Country (data[0],data[1],data[2],data[3]);
    }
 
      // inside the loop, create a new Country using your constructor with 3 arguments and pass in data[0], data[1], data[2], data[3] as arguments.
     // inside the loop, set countryArray[i] to the created Country object

  }

  /* showCountry() will show the image associated with the current country. It should get the country at index from the countryArray. It should use its get method to get its image file name and use the code below to put the image in the GUI.
  */
//  Pre-condition: there needs to be a countryArray with a properly formated image file 
//   possible limitations on input: the image isn't in the proper location or isn't formated properly
//  Post-condition: shows the image associated with the current country
  public void showCountry() {
    // Get the country at index from countryArray
    Country c = countryArray[index];

    
    // Use its get method to get the its image file name and save it into imagefile variable below instead of worldmap.jpg.
    String imagefile = c.getImagFile();
    // Use the following code to create an new Image Icon and put it into the GUI
    img = new ImageIcon("/workspaces/Countries/workspace/" + imagefile);
    imageLabel.setIcon(img);
  }
  
  /* nextButton should increment index. If the index is greater than 9, reset it back to 0. Clear the outputLabel to empty string using setText, and call showCountry();*/
//  Pre-condition: there needs to be a countryArray with a properly formated  information
//   possible limitations on input: the array isn't formated properlly or isnt present at the refernce 
//  Post-condition: increment index. If the index is greater than 9, reset it back to 0. Clear the outputLabel to empty string using setText, and call showCountry().(it effictvily swicthes the curretn ountry that is being tested to te next in the array and shows the said next country) 
  public void nextButtonClick()
  {
     
    index++;

    if (index > 9)
    {
index = 0;
    }
    outputLabel.setText("");
    showCountry();
    outputLabel.setText("What Country is This?");
  }
  
  /* reviewButton should get the country at index from the countryArray, call its toString() method and save the result, print it out with System.out.println and as an argument to outputLabel.setText( text to print out ); */
//  Pre-condition: there needs to be a countryArray with a properly formated  information
//  possible limitations on input: the array isn't formated properlly or isnt present at the refernce 
//  Post-condition: prints out which country the player is currently guessing under the text input box
  public void reviewButtonClick()
  {
     Country c = countryArray[index];
     String countryinfo = c.toString();

     System.out.println(countryinfo);

     outputLabel.setText(countryinfo);

    

  }

  /* quizButton should clear the outputLabel (outputLabel.setText to empty string), get the country at index from countryArray, print out a question about it like What country is this? and/or What's this country's capital?. Get the user's answer using scan.nextLine() and check if it is equal to the country's data using its get methods and print out correct or incorrect.
  */
  //  Pre-condition: there needs to be a countryArray with a properly formated  information
//  possible limitations on input: the array isn't formated properlly or isnt present at the refernce 
//  Post-condition: clears out the output label and tells the user if they guessed the country correctly or not printed out in the output label.

  public void quizButtonClick()
  {
  Country c = countryArray[index];
    outputLabel.setText("");
    
   if (input.getText().equalsIgnoreCase(c.getName()))
   {


    outputLabel.setText("correct!");

  
    
   }
   else
   {
    outputLabel.setText("Try Again");
   }

   
  }




  /* Do NOT change anything below here */
  /* The Main() constructor is finished and will construct the GUI */
public Main() {
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // buttons at the top
        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton newButton = new JButton("Next");
        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(newButton);
        
      

        // create a new image icon
        img = new ImageIcon("worldmap.jpg");
        // create a label to display image
        imageLabel = new JLabel(img);
        // and one for output
        outputLabel = new JLabel();
        jFrame.add(imageLabel);
       
        input = new JTextField(40);
       
        jFrame.add(input);
        jFrame.add(outputLabel);
        jFrame.setVisible(true);



        // add event listener for button click
        reviewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      reviewButtonClick();
    }
        });
    quizButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      quizButtonClick();
    }
    });
   
   newButton.addActionListener(new ActionListener()  {
    public void actionPerformed(ActionEvent e) 
    {
      nextButtonClick();
    }
   });
}
  

}
