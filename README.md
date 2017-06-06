# Crawler System (code: crawlersys)
This is news paper crawler system, writen in Java language. The system is based message broker (Apache Kafka) and memory cache (Redis).
## Build and Run
### Build
`cd crawlersys`

`mvn clean install -DskipTest`

`mvn package -DskipTests`

### Run
`bin/start.sh`

`bin/start-crawler.sh`

`bin/start-write-file.sh`
## Architecture
The system is based Apache Kafka and Redis.
### Apache Kafka
Apache Kafka is used to run BFS algorithm on sitemap tree.
Have two topics in Apache Kafka:
* **crawler_queue**: crawlers messages
* **append_file_queue**: data to append to file messages
