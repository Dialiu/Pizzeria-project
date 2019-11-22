package lt.bit.pizzeriaproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PizzeriaMain {
    private final static int CLASS_NAME = 0;
    private final static int NAME = 1;
    private final static int INFO = 2;
    private final static int PRICE = 3;

    private static ArrayList<MenuItem> mi;
    private static ArrayList<MenuItem> pasirinkimas;
    private static double suma;
    private static int ruosimoLaikas;
    private static LocalDateTime uzsakymoDataLaikas;

    public static void main(String[] args){
        run();
        System.out.println("Uzsakymas vykdomas");
        for(MenuItem no:pasirinkimas){
            System.out.println(no);
        }
        System.out.println("Uzsakymo patvirtinimo data ir laikas: "+uzsakymoDataLaikas.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Uzsakymo suma "+suma+ "EUR");
        System.out.println("Uzsakymas bus ivykdytas "+ uzsakymoDataLaikas.plusMinutes(ruosimoLaikas).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

    public static void run() {
        Scanner reader = null;
        boolean ret;
        try {
            reader = new Scanner(new FileReader("menu.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        mi = new ArrayList<MenuItem>();

        if (reader == null)
            throw new IllegalStateException("Something wrong with file read");
        while (reader.hasNextLine()) {
            String[] fieldsArr = reader.nextLine().split(";");
            try {
                Class cls = Class.forName("lt.bit.pizzeriaproject." + fieldsArr[CLASS_NAME]);
                Constructor construct = cls.getDeclaredConstructor(String.class, String.class, double.class);//cls.getConstructor();

                mi.add((MenuItem) construct.newInstance(fieldsArr[NAME], fieldsArr[INFO], Double.parseDouble(fieldsArr[PRICE])));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(mi, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem o1, MenuItem o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
                // (int)(o1.getPrice() - o2.getPrice());
            }
        });
        System.out.println("MENU");
        for (int i = 0; i < mi.size(); i++) {
            System.out.println("Pasirinkimas " + i + ": " + mi.get(i));//.getMenuStr());
        }
        do{
        System.out.println("Iveskite pasirinkima atskirdami ,");
        String in = new Scanner(System.in).nextLine();
        pasirinkimas = new ArrayList<MenuItem>();

        for (String s : in.split(",")) {

            pasirinkimas.add(MenuItem.GetOrderCopy(mi.get(Integer.valueOf(s.trim()))));
        }


        for (MenuItem i : pasirinkimas) {
            if (IFood.class.isAssignableFrom(i.getClass())) {
                System.out.println("Pasirinkite padaza:");
                for (Sauce sc : Sauce.values()) {
                    System.out.print(sc.name() + ",");
                }
                IFood food = ((IFood) i);
                System.out.println();
                food.setSauce(Sauce.valueOf(new Scanner(System.in).nextLine().trim().toUpperCase()));
            }

            if (i.getClass().isAssignableFrom(Water.class)) {
                System.out.println("Pasirinkite vandens tipa:");
                for (WaterType sc : WaterType.values()) {
                    System.out.print(sc.name() + " ");
                }
                Water water = ((Water) i);
                System.out.println();
                water.setType(WaterType.valueOf(new Scanner(System.in).nextLine().trim().toUpperCase()));

            }
        }
        System.out.println("Jusu pasirinkimas");
        suma = 0;
        ruosimoLaikas = 0;
        for (MenuItem i : pasirinkimas) {
            System.out.println(i);
            suma+=i.getPrice();
            if (IFood.class.isAssignableFrom(i.getClass())) {
                IFood food = ((IFood)i);
                ruosimoLaikas += food.getParuosimoLaikas();
            }
        }
            suma = Math.round(suma*100)/100.00;
        System.out.println("Uzsakymo suma:"+suma+ "EUR");
            System.out.println("Uzsakymas bus ivykdytas uz "+ruosimoLaikas+" min");
        System.out.println("Ar tvirtinti pasirinkima? t/n");
        String command = new Scanner(System.in).nextLine().trim();
        ret = !command.equals("t");

    }while(ret);
        uzsakymoDataLaikas = LocalDateTime.now();

    }
}
