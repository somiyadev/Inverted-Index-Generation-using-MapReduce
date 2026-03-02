import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

    private Text word = new Text();
    private Text docId = new Text();

    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
        docId.set(fileName);

        String line = value.toString().toLowerCase().replaceAll("[^a-zA-Z ]", "");
        String[] tokens = line.split("\\s+");

        for (String token : tokens) {
            if (token.length() > 0) {
                word.set(token);
                context.write(word, docId);
            }
        }
    }
}
