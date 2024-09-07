# Setting Up and Using Kafka Locally

This guide will walk you through the process of setting up and running Kafka on your local machine, from starting the necessary services to producing and consuming messages.

## Step 1: Run Zookeeper
Kafka requires Zookeeper to manage its cluster. First, navigate to your Kafka installation directory:

```bash
cd /Users/chandrakanthrajesh/Desktop/kafka_2.13-3.8.0
```

Now, start the Zookeeper server:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

This will start Zookeeper on its default port, usually `localhost:2181`.

## Step 2: Run Kafka Server
In a new terminal window, navigate to the same directory and start the Kafka broker (server):

```bash
bin/kafka-server-start.sh config/server.properties
```

By default, Kafka will run on `localhost:9092`.

## Step 3: Create a Kafka Topic
Now that the server is running, you can create a topic. Topics are where messages are sent (produced) and received (consumed).

```bash
bin/kafka-topics.sh --create --topic <topic-name> --bootstrap-server localhost:9092 --partitions <num-partitions> --replication-factor <replication-factor>
```

Example:

```bash
bin/kafka-topics.sh --create --topic user-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

This creates a topic called `user-topic` with 3 partitions and a replication factor of 1.

## Step 4: Produce Messages
Once your topic is created, you can start producing messages to it. In a new terminal window:

```bash
bin/kafka-console-producer.sh --topic user-topic --bootstrap-server localhost:9092
```

You can now type messages into the console, which will be sent to the topic:

```bash
> Hi
> This is my message
```

## Step 5: Consume Messages
In another terminal window, start a consumer to read messages from the topic:

```bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic user-topic --from-beginning
```

This will output:

```bash
Hi
This is my message
```

The `--from-beginning` flag ensures that all messages from the start of the topic are read, not just new messages.

## Step 6: Simultaneously View Producer and Consumer Interaction
To observe the interaction between the producer and the consumer in real-time, use the following commands in two different terminals:

- **Producer**:
  ```bash
  bin/kafka-console-producer.sh --topic user-topic --bootstrap-server localhost:9092
  ```

- **Consumer**:
  ```bash
  bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic user-topic --from-beginning
  ```

## Additional Essential Commands

### List Topics
To view a list of all topics in your Kafka server:

```bash
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

### Describe Topic
To get detailed information about a specific topic, including its partitions and replication:

```bash
bin/kafka-topics.sh --describe --topic user-topic --bootstrap-server localhost:9092
```

### Delete a Topic
To delete a topic:

```bash
bin/kafka-topics.sh --delete --topic user-topic --bootstrap-server localhost:9092
```

### View Consumer Group Offsets
To see the current offsets of a consumer group:

```bash
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group <consumer-group>
```

### Reset Offsets for a Consumer Group
To reset the offset for a consumer group:

```bash
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group <consumer-group> --topic user-topic --reset-offsets --to-earliest --execute
```

This resets the consumer group to start consuming messages from the beginning of the topic.