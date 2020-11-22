## Compression

### Proposal

Data compression refers to reducing the amount of data without losing useful information to reduce storage space, improving its transmission, storage, and processing efficiency, or reorganizing data according to a certain algorithm to reduce data redundancy and storage space A technical approach. In other words, data compression works because most real-world data is statistically redundant. In computer science and information theory, data compression or source encoding is the process of expressing information with fewer data bits (or other information-related units) than without encoding according to a specific encoding mechanism. For any form of compressed data communication, it only works when the sender and receiver of the information can understand the encoding mechanism. For example, the article is meaningful only if the recipient knows that the article needs to be explained in English characters. Similarly, only when the recipient knows the encoding method can he understand the compressed data. Some compression algorithms take advantage of this feature to encrypt data during the compression process, such as using password encryption to ensure that only authorized parties can get the data correctly. Compression algorithms have a wide range of applications in the information age, especially in big data and multimedia transmission. Therefore, we decide to dig deeper on the compression algorithm.

### Team Members

- Shuoyu Wang

- Yuyun Chen

- Kang Yang 

### Core Features

1. Implement Burrows-Wheeler compression on arbitrary binary files. Compare the speed and compression of those code to gnu bzip2.

2. Implements Lemple-Ziv-Welch compression on arbitrary binary files, and compare its compression rate and speed with BW algorithm.

###  Extra Features(optional)

1. Implements a compression based on minimum redundant coding and Huffman algorithm, evaluate its performance.Implements file compression and decompression functions based on java input Streams and output Streams.
2. Implements multi-thread support for the above compression algorithms.
3. ReferenceEnhancin Burrows-Wheeler Data Compression Algorithm g Dictionary Based Preprocessing For Better Text CompressionLZW Data CompressionText Encryption with Huffman Compression



#### Github repo: https://github.com/qqjoyko/CompressionGoogle 

#### Driver：https://drive.google.com/drive/folders/1iLrH7XdmMObdxcJOCfdGZbOnrPk4x-NF

