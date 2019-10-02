/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.codec;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.AbstractDataBufferAllocatingTestCase;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.support.DataBufferTestUtils;
import org.springframework.util.MimeTypeUtils;

import static org.junit.Assert.*;

/**
 * @author Sebastien Deleuze
 */
public class DataBufferDecoderTests extends AbstractDataBufferAllocatingTestCase {

    private final DataBufferDecoder decoder = new DataBufferDecoder();

    @Test
    public void canDecode() {
        assertTrue(this.decoder.canDecode(ResolvableType.forClass(DataBuffer.class),
                MimeTypeUtils.TEXT_PLAIN));
        assertFalse(this.decoder.canDecode(ResolvableType.forClass(Integer.class),
                MimeTypeUtils.TEXT_PLAIN));
        assertTrue(this.decoder.canDecode(ResolvableType.forClass(DataBuffer.class),
                MimeTypeUtils.APPLICATION_JSON));
    }

    @Test
    public void decode() {
        DataBuffer fooBuffer = stringBuffer("foo");
        DataBuffer barBuffer = stringBuffer("bar");
        Flux<DataBuffer> source = Flux.just(fooBuffer, barBuffer);
        Flux<DataBuffer> output = this.decoder.decode(source,
                ResolvableType.forClassWithGenerics(Publisher.class, DataBuffer.class),
                null, Collections.emptyMap());

        assertSame(source, output);

        release(fooBuffer, barBuffer);
    }

    @Test
    public void decodeToMono() {
        DataBuffer fooBuffer = stringBuffer("foo");
        DataBuffer barBuffer = stringBuffer("bar");
        Flux<DataBuffer> source = Flux.just(fooBuffer, barBuffer);
        Mono<DataBuffer> output = this.decoder.decodeToMono(source,
                ResolvableType.forClassWithGenerics(Publisher.class, DataBuffer.class),
                null, Collections.emptyMap());

        DataBuffer outputBuffer = output.block(Duration.ofSeconds(5));
        assertEquals("foobar", DataBufferTestUtils.dumpString(outputBuffer, StandardCharsets.UTF_8));

        release(outputBuffer);
    }

}
