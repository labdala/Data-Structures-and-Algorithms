package assn02;

import java.util.Scanner;
import java.util.HashMap;
import java.util.*;

public class JavaWarmUp {

    static public void printAllProducts(ElectronicProduct[] productVec)
    {
            for(int i=0; i< productVec.length;i++) {
                System.out.println("productVec[0]._date=" + productVec[i]._date);
                System.out.println("productVec[0]._assemblyTime=" + productVec[i]._assemblyTime);
                System.out.println("productVec[0]._fee=" + productVec[i]._category);
                System.out.println("productVec[0]._quantity=" + productVec[i]._quantity);
                System.out.println("productVec[0]._time=" + productVec[i]._time);
                System.out.println("productVec[0]._cost=" + productVec[i]._cost);
            }
    }

    static public HashMap<String, Vector> createDictionaryCategory(ElectronicProduct[] productVec)
    {
        int indexHighestAssemblingFee=0;
        int indexLowestAssemblingFee=0;
        HashMap<String, Vector> dictionary = new HashMap<>();
        for(int i=0; i< productVec.length; i++)
        {
//            System.out.println("-------------------------");
//            System.out.println("i="+i+", productVec[i]._fee="+productVec[i]._fee);
            // Store index of lowest and highest assembling fee rates
            if(productVec[i]._fee>= (productVec[indexHighestAssemblingFee]._fee))
            {
               // System.out.println("This is highest");
                indexHighestAssemblingFee = i;
            }
            if(productVec[i]._fee <= (productVec[indexLowestAssemblingFee]._fee))
            {
               // System.out.println("This is lowest");
                indexLowestAssemblingFee = i;
            }

            // if category is not in the dictionary, add new item to hashmap
            if(dictionary.get(productVec[i]._category)==null)
            {
                Vector v = new Vector();
                v.add(productVec[i]._quantity); // quantity
                v.add(productVec[i]._fee); // average assembling fee
                v.add(productVec[i]._fee*productVec[i]._quantity - 16*(productVec[i]._time) - productVec[i]._cost); // TOTAL net profit
                dictionary.put(productVec[i]._category,v);
            }
            else
            {
                Vector value = dictionary.get(productVec[i]._category);
                int database_quantity = (int) value.get(0);
                float database_fee =  (float) value.get(1);
                float database_net_profit =  (float) value.get(2);
                value.set(0,  database_quantity + productVec[i]._quantity);
                value.set(1,  (database_quantity*database_fee + productVec[i]._quantity*productVec[i]._fee)/(database_quantity+productVec[i]._quantity));
                value.set(2,  database_net_profit+ (productVec[i]._fee*productVec[i]._quantity - 16*(productVec[i]._time) - productVec[i]._cost));
                dictionary.put(productVec[i]._category, value);
            }
        }


        System.out.println("Highest per unit assembling fee:");
        System.out.println("\tWhen: "+ productVec[indexHighestAssemblingFee]._date + " " +productVec[indexHighestAssemblingFee]._assemblyTime);
        System.out.println("\tCategory: "+ productVec[indexHighestAssemblingFee]._category);
        String formattedStr = String.format("%.2f",  productVec[indexHighestAssemblingFee]._fee);
        System.out.println("\tPrice: "+formattedStr);

        System.out.println("Lowest per unit assembling fee:");
        System.out.println("\tWhen: "+ productVec[indexLowestAssemblingFee]._date + " " +productVec[indexLowestAssemblingFee]._assemblyTime);
        System.out.println("\tCategory: "+ productVec[indexLowestAssemblingFee]._category);
        formattedStr = String.format("%.2f",  productVec[indexLowestAssemblingFee]._fee);
        System.out.println("\tPrice: "+ formattedStr);

//        for (String key: dictionary.keySet())
//        {
        Vector v = dictionary.get("phone");
        int database_quantity = (int) v.get(0);
        float database_net_profit =  (float) v.get(2);
        System.out.println("Statistic of "+"phone");
        System.out.println("\tQuantity: "+ v.get(0));
        formattedStr = String.format("%.2f", v.get(1));
        System.out.println("\tAverage Assembling fee: "+formattedStr);
        formattedStr = String.format("%.2f", database_net_profit/database_quantity);
        System.out.println("\tAverage Net Profit: "+ formattedStr);

        v = dictionary.get("laptop");
        database_quantity = (int) v.get(0);
        database_net_profit =  (float) v.get(2);
        System.out.println("Statistic of "+"laptop");
        System.out.println("\tQuantity: "+ v.get(0));
        formattedStr = String.format("%.2f", v.get(1));
        System.out.println("\tAverage Assembling fee: "+formattedStr);
        formattedStr = String.format("%.2f", database_net_profit/database_quantity);
        System.out.println("\tAverage Net Profit: "+ formattedStr);

        v = dictionary.get("smart_watch");
        database_quantity = (int) v.get(0);
        database_net_profit =  (float) v.get(2);
        System.out.println("Statistic of "+"smart_watch");
        System.out.println("\tQuantity: "+ v.get(0));
        formattedStr = String.format("%.2f", v.get(1));
        System.out.println("\tAverage Assembling fee: "+formattedStr);
        formattedStr = String.format("%.2f", database_net_profit/database_quantity);
        System.out.println("\tAverage Net Profit: "+ formattedStr);
//        }


        return dictionary;
    }


    public static void main(String[] args)
    {
        ElectronicProduct[] kvec = readInput();
        HashMap<String, Vector> dict =  createDictionaryCategory(kvec);



    }

//    static ElectronicProduct[] readInput(){
static ElectronicProduct[] readInput(){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        ElectronicProduct[] elList = new ElectronicProduct[n];

        for (int i=0; i< n; i++)
        {
            elList[i] = new ElectronicProduct();
            elList[i]._date = s.next();
            elList[i]._assemblyTime=s.next();
            elList[i]._category = s.next();
            elList[i]._fee = Float.parseFloat(s.next());
            elList[i]._quantity = Integer.parseInt(s.next());
            elList[i]._time = Float.parseFloat(s.next());
            elList[i]._cost = Float.parseFloat(s.next());
        }
        return elList;

    }

}

