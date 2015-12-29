# hz-serialization-fst

## Custom Hazelcast 3.X Serialization method using Fast-serialization (FST).

Hazelcast relies heavily on serialization due to its nature (being a distributed cache).
Since version 3 there is an option to plug a custom serialization and that's what this 
tiny project is - a custom serializer implementation relying on FST serialization library.

### Requires
- hazelcast >= 3.0
- fst >= 2.0
- java 8