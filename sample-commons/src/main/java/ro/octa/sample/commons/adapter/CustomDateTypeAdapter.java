package ro.octa.sample.commons.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * @author Octa on 1/4/2016.
 */
public class CustomDateTypeAdapter extends TypeAdapter<Date> {

  @Override
  public void write(JsonWriter out, Date value) throws IOException {
    if (value == null) {
      out.nullValue();
      return;
    }
    out.value(value.getTime());
  }

  @Override
  public Date read(JsonReader in) throws IOException {
    if (in.peek() == JsonToken.NULL) {
      in.nextNull();
      return null;
    }
    long timestamp = in.nextLong();
    return new Date(timestamp);
  }
}
