import java.util.Scanner;
import java.util.Arrays;

public class Project2 {
    public static void main(String[] args){



        /*Scanner usrScanner = new Scanner(System.in);
        String usrInput = "0";
        String fileLoad = "";



        System.out.println("Welcome to the RLE image encoder! \n\nDisplaying Spectrum Image:");

        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);


        do { //using a do-while so I don't have to initialize everything


            switch (usrInput){
                case "1":
                    System.out.println("Enter name of file to load: ");
                    fileLoad = usrScanner.next();
                    break;
                case "2":
                    fileLoad = "testImage";
                    System.out.print("Test image data loaded.\n");
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    if (fileLoad.contains("testImage")){ //this is easier than trying to figure it out from scratch
                    ConsoleGfx.displayImage(ConsoleGfx.testImage);
                    fileLoad = "done";
                    }

                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                default:
                    System.out.println("Error! Invalid input.");
                    break;
                case "0":
            }
            System.out.println("RLE Menu\n" + "--------\n" + "0. Exit\n" + "1. Load File\n" + "2. Load Test Image\n"
                    + "3. Read RLE String\n" + "4. Read RLE Hex String\n" + "5. Read Data Hex String\n"
                    + "6. Display Image\n" + "7. Display RLE String\n"
                    + "8. Display Hex RLE Data\n" + "9. Display Hex Flat Data");

            usrInput = usrScanner.next();//gonna get usrInput at the end so it checks at the bottom instead of running down
        }while(!usrInput.equals("0"));*/
        System.out.print((Arrays.toString(decodeRle(new byte[] {3,15,6,4}))));


    }



    public static String toHexString(byte[]data){ /*WORKS FINE*/
        char cat = '0';
        String catHerd = "";
        int cat1;

        for (int i = 0; i < data.length; i++){ //goes to the length of the byte array
            if (data[i] > 9){
                switch (data[i]){ //takes the bytes and creates a string the length of the number of bytes
                    case 10:
                        cat = 'a'; //Console GFX goes from 0-15 in byte numbers
                        break;
                    case 11:
                        cat = 'b';
                        break;
                    case 12:
                        cat = 'c';
                        break;
                    case 13:
                        cat = 'd';
                        break;
                    case 14:
                        cat = 'e';
                        break;
                    case 15:
                        cat = 'f';
                        break;

                }

                catHerd = catHerd + cat;
            }
            else{
                cat1 = (int)data[i]; //byte to char doesn't work well
                catHerd = catHerd + cat1;
            }
        }
        return (catHerd);

    }
    public static int countRuns(byte[]flatData){ /*WORKS FINE*/

        int count = 1;
        for (int i = 0; i < flatData.length - 1; i++){
            if (flatData[i] != flatData[i+1]){ //checks to see if the right and left are different and adds one if yes
                count += 1;
            }
        }
        return count;
    }

    public static byte[] encodeRle(byte[]flatData){ /*WORKS FINE*/

        int length = 2;
        for(int i = 0; i < flatData.length - 1; i++){ // *gets the length of the byte array, checking for if the number
            if (flatData[i] != flatData[i+1]){//        *on the right and left are different, adding two for the 1st count and number
                length += 2;
            }
        }

        byte number = 1;
        byte[] rleEncoding = new byte[length];
        int j = 0;

        for (int i = 0; i < flatData.length -1 ; i++){


            if (flatData[i] != flatData[i+1] || i+1 == flatData.length -1 ){

                if(i+1 == flatData.length -1){
                    number += 1;
                }
                rleEncoding[j] = number;
                rleEncoding[j+1] = (flatData[i]);
                j += 2;
                number = 1;
            }
            else { //checks to see if the number on the right is equal
                number += 1;
            }

        }
        return rleEncoding;
    }

    public static int getDecodedLength(byte[] rleData){  /*WORKS FINE*/
        int count = 0;

        for (int i = 0; i < rleData.length -1; i = i+2){ //the compressed strings have the total number broken up into every other thing
            count = count + rleData[i];

        }
        return count;
    }
    public static byte[] decodeRle(byte[] rleData){ /*WORKS FINE*/
        int length = 0;

        for (int i = 0; i< rleData.length; i = i + 2){
        length = length + rleData[i];
        }
        byte[] decodedRle = new byte[length];

        for(int j = rleData[0] - 1; j > -1; j--){ // j goes the number of times to from the first value - 1
                    decodedRle[j] = rleData[1]; // to the 0 digit, since 0 is a digit.
        }
        int sum = 0;

        for (int k = 3; k < rleData.length; k = k + 2) {
             sum = sum + rleData[k] - 1; //keeps track of the sum, subtracting 1 from each k value since 0 is a value
            for (int j = length - 1; j >= sum; j--) { //the sum is what has been filled by other numbers
                decodedRle[j] = rleData[k];
            }
        }


        return decodedRle;


    }
    //public static byte[] stringToData(String dataString){}
    //public static String toRleString(byte[] rleData){}
    //public static byte[] stringToRle(String rleString){}



}
