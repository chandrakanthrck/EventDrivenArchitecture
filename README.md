# EventDrivenArchitecture
This project demonstrates a microservices architecture using Kafka with Spring Boot, consisting of two services:

### **DeliveryBoy (Producer)**
### **EndUser (Consumer)**
These microservices interact via Kafka topics, where the DeliveryBoy service produces location updates, and the EndUser service consumes those updates. This project is a practical example of implementing event-driven architecture using Kafka and Spring Boot.

## Overview:
### Services
 1. **DeliveryBoy (Producer)**: Sends location updates to a Kafka topic.
 2. **EndUser (Consumer)**: Listens to the Kafka topic and consumes location updates.
### Features
 * Event-driven communication between services via Kafka.
 * Kafka integration with Spring Boot for seamless message production and consumption.
 * Separate producer and consumer services using Kafka topics.
 * Configurable properties for Kafka and service setup.
### Kafka Topic
 **Location Topic**: Used for publishing and consuming delivery location updates.
## Getting Started
### Prerequisites
To run this project locally, ensure you have the following installed:

 * Java 11 or higher
 * Apache Kafka and Zookeeper running locally
 * Maven for dependency management
 * An IDE like IntelliJ or Eclipse for running the Spring Boot services
### Installation Steps
 1. **Clone the Repository**:

```bash
git clone https://github.com/chandrakanthrck/EventDrivenArchitecture.git
cd EventDrivenArchitecture
```
 2. **Set Up Apache Kafka**:

 * Make sure Kafka and Zookeeper are running locally. Use the following commands in your Kafka installation directory:
```bash
./zookeeper-server-start.sh config/zookeeper.properties
./kafka-server-start.sh config/server.properties
```
 3. **Create the Kafka Topic**:

 * Create the location-topic-name topic in Kafka:
```bash
./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic location-topic-name
```
 4. **Build the Project**:

 * Navigate to each service directory (deliveryboy and enduser) and build the services:
```bash
mvn clean install
```
 5. **Run the Services**:
 * Run both the DeliveryBoy (producer) and EndUser (consumer) services.
For **DeliveryBoy**:

```bash
cd deliveryboy
mvn spring-boot:run
```
For **EndUser**:

```bash
cd enduser
mvn spring-boot:run
```
## Configuration
### DeliveryBoy (Producer) Service
The DeliveryBoy service sends location updates to Kafka. The service is configured via the application.properties file:

```properties
spring.application.name=deliveryboy
server.port=8080
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
```
### EndUser (Consumer) Service
The **EndUser** service consumes location updates from Kafka. It is configured via the application.properties file:

```properties
spring.application.name=enduser
server.port=8081
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=group-1
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```
## Usage
 1. **DeliveryBoy (Producer)** sends location updates to the location-topic-name topic.
 2. **EndUser (Consumer)** listens to the location-topic-name topic and processes the updates.
You can test this by producing a location update in the DeliveryBoy service and watching it be consumed by the EndUser service.

## Running the Kafka Consumer (Manually)
If you want to manually view messages in Kafka, you can run the Kafka console consumer:

```bash
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic location-topic-name --from-beginning
```
## Contributing
Feel free to open issues and submit pull requests if you want to contribute to this project.

## License
This project is licensed under the ISC License.