import java.util.Properties
import java.util.Arrays
import java.util.Date
import java.util.UUID

import scala.collection.JavaConverters._

import org.apache.kafka.clients.consumer._
import com.datastax.driver.core._

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

/**
 * Main application object.
 */
object Main extends App {
  val topic = "twitter"

  def run() {
    val props = new Properties
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-hdfs")
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")

    val consumer = new KafkaConsumer[Nothing, String](props) 

    consumer.subscribe(Arrays.asList(topic))

    System.setProperty("HADOOP_USER_NAME", "root")
    val path = new Path("test.txt")
    val conf = new Configuration()
    conf.set("fs.defaultFS", "hdfs://0.0.0.0:9000")
    val fs = FileSystem.get(conf)
    val os = fs.create(path)

    while(true) {
      val records = consumer.poll(100)

      for (record <- records.asScala) {
        println(record.value)
      	os.write((record.value + "\n").getBytes)
        os.hsync()
        os.hflush()
      }
    }

    fs.close()
  }

  run()
}
