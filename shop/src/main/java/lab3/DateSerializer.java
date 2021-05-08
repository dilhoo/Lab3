package lab3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateSerializer extends StdSerializer<LocalDateTime> {

    protected DateSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String formattedDate = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        gen.writeString(formattedDate);
    }
}