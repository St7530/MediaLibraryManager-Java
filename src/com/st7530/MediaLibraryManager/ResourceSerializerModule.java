// Ensure the format of Library.json is compatible
package com.st7530.MediaLibraryManager;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.st7530.MediaLibraryManager.data.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceSerializerModule extends SimpleModule {
    public ResourceSerializerModule() {
        this.addSerializer(new ResourceSerializer());
    }

    private static class ResourceSerializer extends StdSerializer<List<Resource>> {
        // 无参构造器
        public ResourceSerializer() {
            super((Class<List<Resource>>) (Class<?>) List.class);
        }

        @Override
        public void serialize(List<Resource> resources, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartArray();
            gen.writeString(ArrayList.class.getName());
            gen.writeStartArray();

            for (Resource resource : resources) {
                gen.writeStartArray();
                gen.writeString(resource.getClass().getName());
                gen.writeObject(resource);
                gen.writeEndArray();
            }

            gen.writeEndArray();
            gen.writeEndArray();
        }
    }
}