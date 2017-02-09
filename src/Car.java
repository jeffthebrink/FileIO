import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Car {
    private File f = new File("carAnswers.json");

    String carMake;
    String carModel;
    int carMiles;
    int carDoors;
    String carSunroof;

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarMiles() {
        return carMiles;
    }

    public void setCarMiles(int carMiles) {
        this.carMiles = carMiles;
    }

    public int getCarDoors() {
        return carDoors;
    }

    public void setCarDoors(int carDoors) {
        this.carDoors = carDoors;
    }

    public String getCarSunroof() {
        return carSunroof;
    }

    public void setCarSunroof(String carSunroof) {
        this.carSunroof = carSunroof;
    }


    public static void readAndDisplayContents() throws Exception {
        File f = new File("carAnswers.json");
        //read from json
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, file not found.");
            System.exit(0);
        }
        s.useDelimiter("\\Z");
        String contents = s.next();
        s.close();

        //de-serialize answers from json
        JsonParser parser = new JsonParser();
        Car c2 = parser.parse(contents, Car.class);

        System.out.println("Make: " + c2.getCarMake());
        System.out.println("Model: " + c2.getCarModel());
        System.out.println("Odometer: " + c2.carMiles);
        System.out.println("Number of doors: " + c2.carDoors);
        System.out.println("Car has a sunroof: " + c2.carSunroof);
    }


    public static void main(String[] args) throws IOException {
        Car c = new Car();
        Scanner scanner = new Scanner(System.in);

        int exit = 0;

        while (exit == 0) {
            System.out.println("Hello and welcome to the car questionnaire!");
            System.out.println("The last questionnaire contains: ");

            try {
                readAndDisplayContents();
            } catch (Exception e) {
                System.out.println("File not found!");
                System.exit(0);
            }

            //prompt if they want to change any answers
            System.out.println("-----------------------------------------------");
            System.out.println("Would you like to change the parameters?");
            System.out.println("Type 1 for yes.");
            System.out.println("Type 2 for no.");

            int updateAnswer = Integer.parseInt(scanner.nextLine());

            if (updateAnswer == 1) {
                //prompt user questions if they want to change all the answers
                System.out.println("What is your car make?");
                c.carMake = scanner.nextLine();
                System.out.println("What is your car model?");
                c.carModel = scanner.nextLine();
                System.out.println("How many miles does your odometer show?");
                c.carMiles = Integer.parseInt(scanner.nextLine());
                System.out.println("How many doors does your car have?");
                c.carDoors = Integer.parseInt(scanner.nextLine());
                System.out.println("Does your car have a sunroof [yes/no]");
                c.carSunroof = scanner.nextLine();

                File f = new File(("carAnswers.json"));
                JsonSerializer serializer = new JsonSerializer();
                String jsonCar = serializer.serialize(c);
                FileWriter fw = new FileWriter(f);
                fw.write(jsonCar);
                fw.close();


                try {
                    readAndDisplayContents();
                } catch (Exception e) {
                    System.out.println("File not found!");
                    System.exit(0);
                }
            } else {
                System.out.println("Goodbye!");
                exit = 1;
            }



        }

    }


}


