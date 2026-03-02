import java.io.IOException;
import java.util.HashSet;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        HashSet<String> uniqueDocs = new HashSet<>();

        for (Text val : values) {
            uniqueDocs.add(val.toString());
        }

        StringBuilder result = new StringBuilder();

        for (String doc : uniqueDocs) {
            result.append(doc).append(", ");
        }

        context.write(key, new Text(result.toString()));
    }
}
