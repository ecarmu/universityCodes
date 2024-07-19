package assignment1REAL;

import com.google.common.collect.ObjectArrays;

import java.util.Arrays;

public class nBitGrayCodeSection {

    public String[] initialList = {"0" , "1"};
    public char zero = '0';
    public char one= '1';

    //reflect edilmiş halini kopyalamak için   --static mantıklı mı değil mi düşün sonra
    public static String[] initialListCopy;
    public static String[] copyList1;
    public static String[] copyList2;

    public int binaryDigitCount = 1;

    public static int currentIndexOf1 = 0;
    public static int currentIndexOf2 = 0;

    public static final int maxBits = 32;
    public static final int minBits = 1;


    public String[] finalList;

    public nBitGrayCodeSection(int desiredDigitCount) {
        if(desiredDigitCount >= minBits && desiredDigitCount <= maxBits)
            this.initialList = constructNBitGrayCodeSection(desiredDigitCount, ' ');
        else
        finalList = null;

    }



    public String[] constructNBitGrayCodeSection(int numberOfBinaryDigits, char addedChar){

        if(binaryDigitCount >= numberOfBinaryDigits){
            return initialList;
        }


        else{
            if(addedChar == zero || addedChar == one){
                if(addedChar == zero && currentIndexOf1 < copyList1.length){
                    copyList1[currentIndexOf1] = zero + copyList1[currentIndexOf1];
                    currentIndexOf1++;

                    if(currentIndexOf1 < copyList1.length){
                        copyList1 = constructNBitGrayCodeSection(numberOfBinaryDigits, zero).clone();
                        return copyList1;
                    }
                    else{
                        constructNBitGrayCodeSection(numberOfBinaryDigits, one);
                    }
                }
                if(addedChar == one && currentIndexOf2 <copyList2.length){
                    copyList2[currentIndexOf2] = one + copyList2[currentIndexOf2];
                    currentIndexOf2++;
                    copyList2 = constructNBitGrayCodeSection(numberOfBinaryDigits, one).clone();
                    return copyList2;
                }
            }




            else{

                copyList1 = Arrays.copyOf(initialList, initialList.length);
                copyList2 = reflect(initialList);

                if(currentIndexOf1 < copyList1.length)
                    copyList1 = constructNBitGrayCodeSection(numberOfBinaryDigits, zero).clone();

                if(currentIndexOf2 <copyList2.length)
                    copyList2 = constructNBitGrayCodeSection(numberOfBinaryDigits, one).clone();


            }
        }


        if(binaryDigitCount < numberOfBinaryDigits){
            //concat copyList1 and copyList2 into new (concatted tmp) array
            String[] concattedList = ObjectArrays.concat(copyList1, copyList2, String.class);
            //make initialList same with (concatted tmp) array
            initialList = concattedList.clone();

            binaryDigitCount++;
            currentIndexOf1 = 0;
            currentIndexOf2 = 0;
        }


        return constructNBitGrayCodeSection(numberOfBinaryDigits, ' ');

    }


    public String[] reflect(String[] list){
        String[] reflectedList = new String[list.length];

        for (int i = 0; i < list.length; i++) {
            reflectedList[i] = list[list.length - 1 - i];
        }

        return reflectedList;
    }

    public void printer(){
        for (int i = 0; i < initialList.length; i++) {
            System.out.println("size : " + initialList.length);
            System.out.println(initialList[i]);
        }
    }


    public static void main(String[] args) {
        nBitGrayCodeSection obje = new nBitGrayCodeSection(3);
        System.out.println(Arrays.toString(obje.constructNBitGrayCodeSection(3, 'a')));

    }
}
