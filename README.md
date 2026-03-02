# Inverted-Index-Generation-using-MapReduce
In this activity, Hadoop was configured in pseudo-distributed mode, and multiple text files were uploaded to HDFS. Each file name was treated as the Document ID. The MapReduce program was implemented using Java.
The Mapper reads input files line by line. It first converts all text into lowercase to ensure case normalization. Then, punctuation and special characters are removed using regular expressions. The cleaned text is tokenized into individual words. For each word, the Mapper emits a key-value pair in the format (word, DocumentID). The Document ID is extracted from the FileSplit object, which provides the file name during processing.

The framework automatically groups all identical keys (words) before passing them to the Reducer. The Reducer receives a word and a list of Document IDs. To avoid duplicates, a HashSet is used to store unique document names. Finally, the Reducer formats the output as:

word → Doc1.txt, Doc2.txt

The final output is stored in HDFS inside the part-r-00000 file.

One major challenge was removing duplicate document IDs because a word may appear multiple times in the same document. This was solved using a HashSet. Another issue was case sensitivity and punctuation handling, which was resolved using lowercase conversion and regular expression filtering.

This implementation demonstrates how distributed text processing can be performed efficiently using Hadoop MapReduce. The inverted index generated enables scalable and fast document retrieval, similar to real-world search engines like Google.
