package ro.tachor.notificationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Product.ProductBuilder.class)
public class Product {
    Long id;
    String type;
    String model;
    String price;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProductBuilder {
    }
}
