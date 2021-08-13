package ro.tachor.product.productservice.model.mapper;


import dto.Product;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs13.utils.ModelMappers;
import ro.tachor.product.productservice.model.ProductEntity;

@Component
public class ProductMapper implements ModelMappers<Product, ProductEntity> {


    public Product toApi(ProductEntity source) {
        return Product.builder()
                .id(source.getId())
                .type(source.getType())
                .model(source.getModel())
                .price(source.getPrice().toString())
                .build();
    }

    public ProductEntity toDb(Product source) {
        return ProductEntity.builder()
                .id(source.getId())
                .type(source.getType())
                .model(source.getModel())
                .price(Long.valueOf(source.getPrice()))
                .build();
    }
}