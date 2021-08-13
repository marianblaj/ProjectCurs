package ro.tachor.product.productservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.tachor.product.productservice.events.EventPublisher;
import ro.tachor.product.productservice.model.ProductEntity;
import ro.tachor.product.productservice.model.mapper.ProductMapper;
import ro.tachor.product.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final EventPublisher eventPublisher;
    private final ProductMapper mapper;

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> deleteProduct(Long productId) {
        Optional<ProductEntity> product = productRepository.findById(productId);

        product.ifPresent(this::deleteExistingProduct);

        return product;
    }

    private void deleteExistingProduct(ProductEntity productEntity) {
        log.info("Deleting " + productEntity);
        productRepository.delete(productEntity);
    }

    public Optional<ProductEntity> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public ProductEntity addProduct(ProductEntity newProduct) {
        newProduct.setId(null);
       eventPublisher.sendProductAddedEvent(mapper.toApi(newProduct));
        return productRepository.save(newProduct);
    }
}
