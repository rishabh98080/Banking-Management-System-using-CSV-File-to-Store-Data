import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Banking{
   static ArrayList<Object[]> Table = new ArrayList<>();
   static Object[] data = new Object[10];
   static String name;
   static int AccountNo;
   static int Balance;
   static int age;
   static String Address;
   static String Sex;
   static int CreditSc;
   static long phone;
   static String email;
   static int pin;
   static int Response = 0;


   public static void main(String[] args) {
       options();
       System.out.println("\n");
       do{
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your choice:");
        
        Response = scan.nextInt();
        if(Response > 7 || Response < 1){
            System.out.println("Try again with valid response!!!");
        }
       }while(Response > 7 || Response < 1);
 
       switch(Response){
        case 1:AccountDetail();break;
        case 2:BalanceDet();break;
        case 3:Withdraw();break;
        case 4:Deposit();break;
        case 5:Update();break;
        case 6:create();break;
        case 7:/*Delete()*/;break;
       }
   }
    protected static void options() {
       System.out.println("Enter the number against which your work lies:");
       System.out.println("1.Account Details");
       System.out.println("2.Balance Details");
       System.out.println("3:Withdraw Cash");
       System.out.println("4.Deposite you money");
       System.out.println("5.Update Details ");
       System.out.println("6.Create new Account");
       System.out.println("7.Delete your existing account");
   }
    protected  static void create() {
        String ch = "YES";
        while(!ch.equals("NO")){
            System.out.println("Now We are creating your account just follow the following step and eneter details:");
            Scanner scan = new Scanner(System.in);

            Random random = new Random();
            AccountNo = 1000000 + random.nextInt(0,100000);
            System.out.println("We will allot a Account number for you!!!");
            data[0] = AccountNo;

            System.out.print("Enter your Name:");
            name = scan.next().toUpperCase();
            data[1] = name;

            System.out.print("Enter the deposite at opening:");
            Balance = scan.nextInt();
            data[2] = Balance;

            System.out.print("Enter your Age:");
            age = scan.nextInt();
            data[3] = age;

            System.out.print("Enter your Sex:");
            Sex = scan.next().toUpperCase();
            data[4] = Sex;

            System.out.print("Enter your Address:");
            Address = scan.next().toUpperCase();
            data[5] = Address;

            System.out.print("Enter your phone Number:");
            phone = scan.nextLong();
            data[6] = phone;

            System.out.print("Enter your Email:");
            email = scan.next().toUpperCase();
            data[7] = email;

            System.out.print("Create a pin:");
            pin = scan.nextInt();
            data[8] = pin;

            System.out.print("We'll keep upadating your credit score!!");
            CreditSc = random.nextInt(0,10);
            data[9] = CreditSc;
    
            Table.add(data);
            System.out.print("Would you like to create more accounts:");
            ch = scan.next().toUpperCase();

            try {
                FileWriter file = new FileWriter("Data.csv",true);
                for(int i = 0; i < data.length;i++){
                    String chunk = data[i].toString();
                    file.append(chunk +",") ;
                }
                file.append("\n");
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected static void AccountDetail(){
        try(BufferedReader file = new BufferedReader(new FileReader("Data.csv"))){
            Scanner scan = new Scanner(System.in);
            System.out.print("Enetr Account Number:");
            int accountno = scan.nextInt();
            String data;
            Object[] chunk;
            ArrayList<Object[]> list = new ArrayList<>();
            while((data = file.readLine()) != null){
                chunk = data.split(",");
                list.add(chunk);
            }
            for(int i = 0; i < list.size() ; i++){
                Object[] objlist = list.get(i);
                String account = Integer.toString(accountno);
                if(objlist[0].equals(account)){
                    for(Object x : objlist){
                        System.out.print(x + " ");
                    }
                }
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    protected static void Withdraw(){
        Scanner pi = new Scanner(System.in);
        System.out.print("Enter the money to withdrawn:");
        int Withdraw = pi.nextInt();
        try(BufferedReader raederb = new BufferedReader(new FileReader("Data.csv"))){
            System.out.print("Enetr Account Number:");
            int accountno = pi.nextInt();
            String account = Integer.toString(accountno);
            pi.nextLine();
            System.out.print("Enter PIN:");
            int PIN = pi.nextInt();
            String pin = Integer.toString(PIN);

            String data;
            ArrayList<Object[]> list = new ArrayList<>();
            Object[] obj;
            while((data = raederb.readLine()) != null){
                obj = data.split(",");
                list.add(obj);
            }
            for(Object[] x : list){
                if(x[0].equals(account) && x[8].equals(pin)){
                    String hold = (String)x[2];
                    hold = Integer.toString(Integer.parseInt(hold) - Withdraw);
                    System.out.println("The Balance Before Withdrawl : " + x[2]);
                    System.out.println("The remaining balance is : " + hold);
                    x[2] = (Object)hold;
                    try {
                        FileWriter file = new FileWriter("Data.csv",true);
                        for(int i = 0; i < x.length;i++){
                            String chunk = x[i].toString();
                            file.append(chunk +",") ;
                        }
                        file.append("\n");
                        file.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        pi.close();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    protected static void BalanceDet(){
        Scanner pi = new Scanner(System.in);
        try(BufferedReader raederb = new BufferedReader(new FileReader("Data.csv"))){
            System.out.print("Enetr Account Number:");
            int accountno = pi.nextInt();
            String account = Integer.toString(accountno);
            pi.nextLine();
            System.out.print("Enter PIN:");
            int PIN = pi.nextInt();
            String pin = Integer.toString(PIN);

            String data;
            ArrayList<Object[]> list = new ArrayList<>();
            Object[] obj;
            while((data = raederb.readLine()) != null){
                obj = data.split(",");
                list.add(obj);
            }
            int a = 0;
            for(Object[] x : list){
                if(x[0].equals(account) && x[8].equals(pin)){
                    System.out.println("After Withdrawl "+a+" :"+x[2]);
                    a++;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    protected static  void Deposit(){
        Scanner pi = new Scanner(System.in);
        System.out.print("Enter the money to Deposit:");
        int Deposit = pi.nextInt();
        try(BufferedReader raederb = new BufferedReader(new FileReader("Data.csv"))){
            System.out.print("Enter Account Number:");
            int accountno = pi.nextInt();
            String account = Integer.toString(accountno);
            pi.nextLine();
            System.out.print("Enter PIN:");
            int PIN = pi.nextInt();
            String pin = Integer.toString(PIN);

            String data;
            ArrayList<Object[]> list = new ArrayList<>();
            Object[] obj;
            while((data = raederb.readLine()) != null){
                obj = data.split(",");
                list.add(obj);
            }
            for(Object[] x : list){
                if(x[0].equals(account) && x[8].equals(pin)){
                    String hold = (String)x[2];
                    hold = Integer.toString(Integer.parseInt(hold) + Deposit);
                    System.out.println("The Balance Before Deposit : " + x[2]);
                    System.out.println("The current balance is : " + hold);
                    x[2] = (Object)hold;
                    try {
                        FileWriter file = new FileWriter("Data.csv",true);
                        for(int i = 0; i < x.length;i++){
                            String chunk = x[i].toString();
                            file.append(chunk +",") ;
                        }
                        file.append("\n");
                        file.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        pi.close();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    protected static void Update(){
        try(BufferedReader buffer = new BufferedReader(new FileReader("Data.csv"))){
            System.out.println("To update any detail in your account,you need to reenter all the information again in the form.");
            Scanner out = new Scanner(System.in);
            System.out.print("Enter Your Account No.:");
            int accountno = out.nextInt();
            System.out.print("Enter Your PIN No.:");
            int PIN = out.nextInt();
            ArrayList<Object[]> list = new ArrayList<>();
            Object[] tempHold;
            String hold;
            
            while((hold = buffer.readLine()) != null){
                tempHold = hold.split(",");
                list.add(tempHold);
            }for(Object[] x : list){
                    String account = Integer.toString(accountno);
                    String Pin = Integer.toString(PIN);
                    if(x[0].equals(account) && x[8].equals(Pin)){
                        FileWriter file = new FileWriter("Data.csv",true);
                        data[0] = accountno;
                        System.out.print("Enter your Name:");
                        name = out.next().toUpperCase();
                        data[1] = name;
                        
                        data[2] = x[2];

                        System.out.print("Enter your Age:");
                        age = out.nextInt();
                        data[3] = age;

                        System.out.print("Enter your Sex:");
                        Sex = out.next().toUpperCase();
                        data[4] = Sex;

                        System.out.print("Enter your Address:");
                        Address = out.next().toUpperCase();
                        data[5] = Address;
 
                        System.out.print("Enter your phone Number:");
                        phone = out.nextLong();
                        data[6] = phone;

                        System.out.print("Enter your Email:");
                        email = out.next().toUpperCase();
                        data[7] = email;

                        System.out.print("Create a pin:");
                        pin = out.nextInt();
                        data[8] = pin;

                        System.out.print("We'll keep upadating your credit score!!");
                        data[9] = x[9];
                        System.out.println("Do you really want to update details!!! as below??? for the account no.:" + accountno);
                        System.out.println("Name : " + name);
                        System.out.println("Balance :" + x[2]);
                        System.out.println("Age :" + age);
                        System.out.println("Sex :" + Sex);
                        System.out.println("Address : " + Address);
                        System.out.println("Phone : " + phone);
                        System.out.println("Email : " + email);
                        System.out.println("PIN : " + pin);
                        System.out.println("CreditSc(can't change)");
                        System.out.print("Do you Confirm(yes/no): ");
                        String Confirm = out.next().toUpperCase();
                        if (Confirm.equals(Confirm)) {
                            for(Object y : data){
                            file.append(y+",");
                        }
                        file.append("\n");
                        file.close();
                        }
                    }
                }
            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /*protected static void Delete(){
        
        try(BufferedReader file = new BufferedReader(new FileReader("Data.csv"))){
            ArrayList<Object[]> list = new ArrayList<>();
            Object[] tempData;
            String temObj;
            Scanner  detail = new Scanner(System.in);
            System.out.print("Enter Your Account No.:");
            AccountNo = detail.nextInt();
            System.out.print("Enter Your PIN:");
            pin = detail.nextInt();
            while((temObj = file.readLine()) != null){
                tempData = temObj.split(",");
                list.add(tempData);
                for(Object n : tempData){
                    System.out.println(n);
                }
            }
            for(Object[] x : list){
                if(x[0].equals(Integer.toString(AccountNo)) && x[8].equals(Integer.toString(pin))){
                    list.remove(x);
                }
                for(Object y : x){
                    FileWriter write = new FileWriter("DataUpdated.csv",true);
                    write.append(y+",");
                }
            }System.out.print("Through Here!!3");
            File fiel = new File("Data.csv");
            String mydta;
            fiel.delete();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(BufferedReader file = new BufferedReader(new FileReader("DataUpdated.csv"))){
            ArrayList<Object[]> list1 = new ArrayList<>();
            Object[] tempData;
            String temObj;
            System.out.print("Enter Your Account Detail:");
            while((temObj = file.readLine()) != null){
                tempData = temObj.split(",");
                list1.add(tempData);
            }
            for(Object[] p : list1){
                for(Object q : p){
                    FileWriter write = new FileWriter("DataUpdated.csv",true);
                    write.append(p+",");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/
}