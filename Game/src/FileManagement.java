import java.io.*;

public class FileManagement {

    private BufferedReader reader;
    private BufferedWriter writer;

    public int[] getScores(){
        int[] scores = new int[10];
        String[] scoresString = new String[10];
        String line;
        String textOut= "";

        try {
            reader = new BufferedReader(new FileReader("resources/scores.txt"));

            line = reader.readLine();

            while(line != null){
                textOut += line+" ";
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            System.err.println("Issue reading file!");
        }


        scoresString = textOut.split(" ");

        for (int i=0; i< scoresString.length;i++){
            if (scoresString[i] != null){
                scores[i] = Integer.parseInt(scoresString[i]);
            }
        }

        return scores;
    }
    public void saveScores(int[] scores){

        String text="";
        for(int i= 0 ; i< scores.length;i++){
            if (scores[i]>0){
                text += Integer.toString(scores[i])+"\n";
            }
        }
        try{
            writer = new BufferedWriter(new FileWriter("resources/scores.txt"));
            writer.write(text);

            writer.flush();
            writer.close();

        } catch (IOException ex){
            System.err.println("Issue writing file!");
        }
    }

}
