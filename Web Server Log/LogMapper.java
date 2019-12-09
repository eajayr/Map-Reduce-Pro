package WebServerLog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/* 
 * To define a map function for your MapReduce job, subclass 
 * the Mapper class and override the map method.
 * The class definition requires four parameters: 
 *   The data type of the input key
 *   The data type of the input value
 *   The data type of the output key (which is the input key type 
 *   for the reducer)
 *   The data type of the output value (which is the input value 
 *   type for the reducer)
 */

public class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  /*
   * The map method runs once for each line of text in the input file.
   * The method receives a key of type LongWritable, a value of type
   * Text, and a Context object.
   */
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
		/*Called once for each key/value pair in the input split. 
		 * Most applications should override this, but the default is 
		 * the identity function.
		 */


    /*
     * Convert the line, which is received as a Text object,
     * to a String object.
     */
    String line = value.toString();

    /*
     * The line.split("\\W+") call uses regular expressions to split the
     * line up by non-word characters.
     * 
     */
    for (String word : line.split("\\W+")) {
      if (word.length() > 0) {
    	  if(word.matches("(?)jpg|png|gif"))
    	  {  
    	  context.write(new Text(word), new IntWritable(1));
      }
    }

    }
  }
}