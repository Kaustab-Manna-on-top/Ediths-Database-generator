import java.io.*;
import java.util.*;

public class databasegenerator {

    static String FP ="C:\\Users\\kaust\\OneDrive\\Desktop\\Edith\\Edith-Database";
    static Set<String> GeneratedSentences = new LinkedHashSet<>();
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            File directory = new File(FP);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String intentFilePath = FP + "\\PresentIntents.txt";
            File file = new File(intentFilePath);
            ArrayList<String> PresentIntents = new ArrayList<>();
            Collections.addAll(
                PresentIntents,
                "greetings",
                "goodbye",
                "thanks",
                "help"
            );

            ArrayList<String> Greetings = new ArrayList<>();
            Collections.addAll(Greetings, "Hello", "Hi", "Hey", "Howdy","greetings");
            ArrayList<String> Goodbye = new ArrayList<>();
            Collections.addAll(Goodbye, "Goodbye", "Bye", "See you later");
            ArrayList<String> Thanks = new ArrayList<>();
            Collections.addAll(Thanks, "Thanks", "Thank you", "Much appreciated");
            ArrayList<String> Help = new ArrayList<>();
            Collections.addAll(Help, "Help", "I need assistance", "Can you help me?");


            if (file.createNewFile()) {
                System.out.println("Successfully created Base File");
                FileWriter fw = new FileWriter(file);
                for (String s : PresentIntents) {
                    if(s.equalsIgnoreCase("Add Intent")){
                    }
                    fw.write(s);
                    fw.write("\n");
                }
                fw.close();
            } else {
            }

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            Set<String> Intents = new LinkedHashSet<>();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Intents.add(line);
            }

            int i = 0;
            LinkedHashMap<Integer, String> Menu = new LinkedHashMap<>();
            for (String s : Intents) {
                Menu.putIfAbsent(i, s);
                i++;
            }
            Menu.putIfAbsent(i, "exit");

            for(String s : PresentIntents){
                String Filepath = FP + "\\" + s + ".txt";
                File file1 = new File(Filepath);
                if (file1.createNewFile()){
                    System.out.println("Successfully created " + s);
                    FileWriter fw = new FileWriter(file1);
                    if(s.equalsIgnoreCase("greetings")){
                        for(String g : Greetings){
                            fw.write(g);
                            fw.write("\n");
                        }
                    } else if(s.equalsIgnoreCase("goodbye")){
                        for(String g : Goodbye){
                            fw.write(g);
                            fw.write("\n");
                        }
                    } else if(s.equalsIgnoreCase("thanks")){
                        for(String g : Thanks){
                            fw.write(g);
                            fw.write("\n");
                        }
                    } else if(s.equalsIgnoreCase("help")){
                        for(String g : Help){
                            fw.write(g);
                            fw.write("\n");
                        }
                    } 
                        else if(s.equalsIgnoreCase("exit")){
                            break;
                        }
                    fw.close();
                } else {
                }
            }
            
            System.out.println(
                    "----Welcome to Edith Database Generator----"
                );
            
            while (true) {    
                System.out.println(
                    "Enter Your choice from the following options : "
                );
                for (Map.Entry<Integer, String> Entry : Menu.entrySet()) {
                    int j = Entry.getKey();
                    String Value = Entry.getValue();
                    System.out.println(j + " : " + Value);
                }
                System.out.println("---Enter your choice--- ");
                int j = scanner.nextInt();
                if (!Menu.containsKey(j)) {
                    System.out.println("Choose a valid option!!");
                    continue;
                }
                String intent = Menu.get(j).trim();

                if (intent.equalsIgnoreCase("exit")) {
                    break;
                }
                String Filepath = FP + "\\" + intent + ".txt";
                File file1 = new File(Filepath);

                if (!file1.exists()) {
                    if (file1.createNewFile()) {
                        System.out.println("Successfully created");
                    } else {
                        System.out.println("Error in creating file");
                    }
                }

                System.out.println("Do you want to add any new sentences to the intent?? " + "//Type Intent to add Intents" + "// Type combine to combine sentences");

                char R =scanner.next().toLowerCase().trim().charAt(0);
                scanner.nextLine();
                if (R == 'y') {
                    if (sentenceadder(intent, Filepath, scanner)) {
                        System.out.println("Sentences added successfully");
                    } else {
                        System.out.println("Error adding sentences");
                    }
                    continue;
                }
                else if (R == 'i') {
                            System.out.println("Enter your Intent/Category");
                            String Intent = scanner.nextLine().trim();
                            if (intentadder(Intent,intentFilePath)){
                                System.out.println("Intent added successfully");
                            } else {
                                System.out.println("Error adding intent");
                            }
                            continue;
                        }
                else{
                    if (sentencecomboer(intent,Filepath)){
                        System.out.println("Sentences combined successfully");
                        System.out.println("Do you wanna combine more sentences?? (y/n)");
                        char choice = scanner.next().toLowerCase().trim().charAt(0);
                        scanner.nextLine();
                        if (choice == 'n') {
                            filemaker();
                            break;
                        }
                        else{
                            continue;
                        }
                    } else {
                        System.out.println("Error combining sentences");
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Something happened to the file : " + e);
        }
        scanner.close();
    }

    public static void filemaker(){
        File file = new File(FP + "\\GeneratedSentences.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("Successfully created Generated Sentences File");
            } else {
                System.out.println("Generated Sentences File already exists");
            }
            FileWriter fw = new FileWriter(file);
            for (String s : GeneratedSentences) {
                fw.write(s);
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error in creating Generated Sentences File : " + e);
        }
    }

    public static boolean sentenceadder(
        String Intent,
        String filepath,
        Scanner scanner
    ) {
        System.out.println("--Data presented Within--");
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            Set<String> Sentences =
                new LinkedHashSet<>();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Sentences.add(line);
            }

            System.out.println("Current Sentences:");
            int i = 1;
            for (String s : Sentences) {
                System.out.println(i + ". " + s);
                i++;
            }
            System.out.println("Enter 0 to exit");
            while (true) {
                System.out.println("Enter a Sentence");
                String s = scanner.nextLine().trim();

                if (s.equalsIgnoreCase("0")) {
                    break;
                } else if (!s.isEmpty()) {
                    Sentences.add(s);
                }
            }

            System.out.println(
                "Updated Contents\n" + Sentences
            );

            FileWriter fw = new FileWriter(filepath);
            for (String s : Sentences) {
                fw.write(s);
                fw.write("\n");
            }

            fw.close();
            br.close();
            return true;
        } catch (Exception e) {
            System.out.println(
                "Something is wrong " + e
            );
            return false;
        }
    }

    public static boolean intentadder(String Intent,String filepath){
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if(line.trim().equalsIgnoreCase(Intent)){
                    System.out.println("Intent already exists");
                    br.close();
                    return false;
                }
            }

            br.close();
            FileWriter fw = new FileWriter(filepath, true);
            fw.write(System.lineSeparator());
            fw.write(Intent);

            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println(
                "Error : " + e
            );
            return false;
        }
    }

    public static boolean sentencecomboer(String Intent,String filepath){
        String combinerPath = FP + "\\Combiner.txt";
        ArrayList<String> Combiners = new ArrayList<>();
        ArrayList<String> punctuations = new ArrayList<>();
        ArrayList<String> prefixes = new ArrayList<>();
        ArrayList<String> suffixes = new ArrayList<>();

        Collections.addAll(Combiners,"Punctuations.txt",
            "Prefix.txt",
            "Suffix.txt"
        );
        Collections.addAll(punctuations,"!",
                "?",
                ".",
                ","
            );

        Collections.addAll(prefixes,"hey",
                "yo",
                "bro",
                "well",
                "um"
            );

            Collections.addAll(suffixes,"bro",
                "dude",
                "mate",
                "buddy"
            );
        try {
            File combinerFile = new File(combinerPath);
            if (combinerFile.createNewFile()) {
                System.out.println("Successfully created Combiner File");

                FileWriter fw = new FileWriter(combinerFile);
                for (String s : Combiners) {
                    fw.write(s);
                    fw.write("\n");
                }
                fw.close();
            } else {
            }

            for(String s : Combiners){
                String Filepath = FP + "\\" + s;
                File file1 = new File(Filepath);
                if (file1.createNewFile()){
                    if (s.equalsIgnoreCase("Punctuations.txt")){
                    FileWriter fw = new FileWriter(file1);
                    for(String p : punctuations){
                        fw.write(p);
                        fw.write("\n");
                    }
                    fw.close();
                } else if (s.equalsIgnoreCase("Prefix.txt")){
                    FileWriter fw = new FileWriter(file1);
                    for(String p : prefixes){
                        fw.write(p);
                        fw.write("\n");
                    }
                    fw.close();
                } else if (s.equalsIgnoreCase("Suffix.txt")){
                    FileWriter fw = new FileWriter(file1);
                    for(String p : suffixes){
                        fw.write(p);
                        fw.write("\n");
                    }
                    fw.close();
                }
                }
            }

            for(String s : Combiners){
                String Filepath = FP + "\\" + s;
                File file1 = new File(Filepath);
                if(file1.createNewFile()){

                }
            }

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            Set<String> Sentences = new LinkedHashSet<>();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Sentences.add(line);
            }

            FileReader fr1 = new FileReader(combinerPath);
            BufferedReader br1 = new BufferedReader(fr1);

            String line1 = "";
            Set<String> combo = new LinkedHashSet<>();
            while ((line1 = br1.readLine()) != null) {
                line1 = line1.trim();
                if (line1.isEmpty()) {
                    continue;
                }
                combo.add(line1);
            }
            for (String s : combo) {
                File combiner = new File(FP + "\\" + s);
                if (!combiner.exists()) {
                    System.out.println("Combiner missing : " + combiner.getAbsolutePath());
                    continue;
                }

                String filename = combiner.getName();
                FileReader temp = new FileReader(combiner);
                BufferedReader tempbr = new BufferedReader(temp);
                String Sym;
                Set<String> TP = new LinkedHashSet<>();
                while ((Sym = tempbr.readLine()) != null){
                    Sym = Sym.trim();
                    if (Sym.isEmpty()) {
                        continue;
                    }
                    TP.add(Sym);
                }
                tempbr.close();
                HashSet<String> tempSet = new HashSet<>();
                for (String T : TP) {
                    for (String sentence : Sentences) {
                        if (filename.toLowerCase().contains("prefix")){
                            GeneratedSentences.add(T + " " + sentence);
                        } 
                        else if (filename.toLowerCase().contains("punctuations")){
                            for(String P : GeneratedSentences){
                                tempSet.add(P + T);
                            } 
                            GeneratedSentences.add(sentence + T);
                        } 
                        else {
                            for(String S : GeneratedSentences){
                                tempSet.add(S + " " + T);
                            }
                            GeneratedSentences.add(sentence + " " + T);
                        }
                        }
                    }
                    GeneratedSentences.addAll(tempSet);
                }
            
            int i = 1;
            for (String T : GeneratedSentences) {
                System.out.println(i + " : " + T); 
                i++;
            }

            br1.close();
            br.close();

        } catch (Exception e) {
            System.out.println(
                "Error in File ma guy!! : " + e
            );
            return false;
        }
        return true;
    }
}
