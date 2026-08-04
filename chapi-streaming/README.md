# Chapi Kafka streaming API

`chapi-streaming` turns code-change records into raw and project-resolved Chapi AST events. Version 1 supports Java and keeps project source plus processed `eventId` values in fault-tolerant Kafka Streams state stores.

## Topics

| Topic | Direction | Value |
| --- | --- | --- |
| `chapi.code-change.v1` | input | `CodeChangeEvent` JSON |
| `chapi.ast.raw.v1` | output | changed file after pass 1 |
| `chapi.ast.resolved.v1` | output | every affected project file after pass 2 |
| `chapi.diagnostic.v1` | output | validation or analysis failure |

Input records **must use `projectId` as the Kafka key**. This co-partitions all files whose same-package types need to be resolved. `eventId` values are kept in a changelog-backed state store; replaying an event produces no duplicate AST or diagnostic output. Kafka Streams is configured with `exactly_once_v2`, so output records, input offsets, and state updates are committed atomically.

An upsert value looks like this:

```json
{
  "eventId": "change-001",
  "projectId": "demo",
  "filePath": "src/main/java/com/example/Foo.java",
  "language": "java",
  "content": "package com.example; public class Foo {}",
  "operation": "UPSERT",
  "occurredAtEpochMs": 1785801600000
}
```

For `DELETE`, `content` may be omitted. Raw delete events have `ast` omitted, and the resolved topic receives new projections for all remaining files in the project.

## Run locally

Kafka 4.x clients and Streams require Java 11 or newer. This module targets Java 11; the broker container bundles its
own runtime.

```bash
cd chapi-streaming
docker compose up -d
cd ..

for topic in \
  chapi.code-change.v1 \
  chapi.ast.raw.v1 \
  chapi.ast.resolved.v1 \
  chapi.diagnostic.v1
do
  docker compose -f chapi-streaming/compose.yaml exec kafka \
    /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
    --create --if-not-exists --topic "$topic"
done

./gradlew :chapi-streaming:run
```

Produce records with `parse.key=true` so the text before the first colon is the project key:

```bash
docker compose -f chapi-streaming/compose.yaml exec kafka \
  /opt/kafka/bin/kafka-console-producer.sh \
  --bootstrap-server localhost:9092 \
  --topic chapi.code-change.v1 \
  --property parse.key=true \
  --property key.separator=:
```

Then paste one JSON record per line, prefixed by the project ID:

```text
demo:{"eventId":"foo-1","projectId":"demo","filePath":"Foo.java","language":"java","content":"package com.example; public class Foo {}"}
demo:{"eventId":"bar-1","projectId":"demo","filePath":"Bar.java","language":"java","content":"package com.example; public class Bar extends Foo {}"}
```

The resolved `Bar` event contains `"Extend":"com.example.Foo"` even if `Bar` arrived first: every accepted change reruns pass 2 for the current project snapshot.

## Configuration

All settings can be environment variables; defaults are shown below.

| Variable | Default |
| --- | --- |
| `CHAPI_BOOTSTRAP_SERVERS` | `localhost:9092` |
| `CHAPI_APPLICATION_ID` | `chapi-streaming-v1` |
| `CHAPI_STATE_DIR` | `/tmp/chapi-streaming-state` |
| `CHAPI_NUM_STREAM_THREADS` | `1` |
| `CHAPI_CODE_CHANGE_TOPIC` | `chapi.code-change.v1` |
| `CHAPI_AST_RAW_TOPIC` | `chapi.ast.raw.v1` |
| `CHAPI_AST_RESOLVED_TOPIC` | `chapi.ast.resolved.v1` |
| `CHAPI_DIAGNOSTIC_TOPIC` | `chapi.diagnostic.v1` |

System properties use the lower-case dotted form, for example `-Dchapi.bootstrap.servers=kafka:9092`.
