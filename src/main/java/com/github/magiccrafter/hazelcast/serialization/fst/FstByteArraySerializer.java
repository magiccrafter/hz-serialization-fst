
/*
 *
 *  * Copyright 2016 Nasko Vasilev
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.github.magiccrafter.hazelcast.serialization.fst;

import com.hazelcast.nio.serialization.ByteArraySerializer;
import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;
import java.io.Serializable;

/**
 * Custom Hazelcast 3.X Serialization method using Fast-serialization (FST).
 * https://github.com/RuedigerMoeller/fast-serialization
 *
 * @param <T>
 */
public class FstByteArraySerializer<T extends Serializable> implements ByteArraySerializer<T> {

    /** FSTConfiguration caches metadata and is being reused for better performance. */
    private final FSTConfiguration conf;
    private final int typeId;


    /**
     * Create a serializer with default FST Configuration {@link FSTConfiguration#createDefaultConfiguration()}
     * @param typeId
     */
    public FstByteArraySerializer(int typeId) {
        this(typeId, FSTConfiguration.createDefaultConfiguration());
    }

    public FstByteArraySerializer(int typeId, FSTConfiguration conf) {
        this.typeId = typeId;
        this.conf = conf;
    }

    @Override
    public byte[] write(T t) throws IOException {
        return conf.asByteArray(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T read(byte[] bytes) throws IOException {
        return (T) conf.asObject(bytes);
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public void destroy() {
    }
}
