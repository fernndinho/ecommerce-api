package me.fernndinho.shop.products;

import com.google.common.collect.Lists;
import me.fernndinho.shop.categories.models.CategoryEntity;
import me.fernndinho.shop.categories.repo.CategoryRepository;
import me.fernndinho.shop.colors.repo.ColorRepository;
import me.fernndinho.shop.files.payload.FileResponse;
import me.fernndinho.shop.files.services.LocalFileService;
import me.fernndinho.shop.products.payload.ProductCreateRequest;
import me.fernndinho.shop.products.payload.ProductDetailsResponse;
import me.fernndinho.shop.products.payload.ProductQueryRequest;
import me.fernndinho.shop.products.payload.ProductVariantRequest;
import me.fernndinho.shop.products.mapper.ProductMapper;
import me.fernndinho.shop.products.models.ProductEntity;
import me.fernndinho.shop.products.models.ProductVariantEntity;
import me.fernndinho.shop.products.repo.ProductRepository;
import me.fernndinho.shop.products.repo.ProductVariantRepo;
import me.fernndinho.shop.shared.error.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductVariantRepo variantRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ColorRepository colorRepo;
    @Autowired
    private LocalFileService fileService;

    public List<ProductDetailsResponse> getPaginated(ProductQueryRequest request) {
        Sort sort = Sort.by(request.getOrderBy());

        if(request.isAsc())
            sort.ascending();
        else
            sort.descending();

        Pageable pageable = PageRequest.of(request.getPage(), 12, sort);

        Page<ProductEntity> entities;

        if(request.getCategories().isEmpty() && request.getColors().isEmpty()) {
            entities = productRepo.findAll(pageable);
        } else {
            entities = productRepo.findByCategoriesSlugInOrVariantsColorsIn(request.getCategories(), request.getColors(), pageable);
        }


        return entities.stream()
                .map(entity -> productMapper.toDto(entity))
                .collect(Collectors.toList());
    }

    public ProductDetailsResponse getProduct(String slug) {
        return productRepo.findBySlug(slug)
                .map(pe -> productMapper.toDto(pe))
                .orElseThrow(() -> new NotFoundException("product provided not found"));
    }


    public ProductDetailsResponse create(ProductCreateRequest request) {
        ProductEntity entity = productMapper.toEntity(request);

        if(request.getCategories() != null) {
            List<CategoryEntity> categories = categoryRepo.findBySlugIn(request.getCategories());
            entity.setCategories(categories);
        }

        ProductEntity temp = productRepo.save(entity);

        if(request.getVariants() != null && !request.getVariants().isEmpty()) {
            List<ProductVariantEntity> variants = createVariants(temp, request.getVariants());
            temp.setVariants(variants);
        }

        ProductEntity saved = productRepo.save(temp);


        return productMapper.toDto(saved);
    }

    private List<ProductVariantEntity> createVariants(ProductEntity owner, List<ProductVariantRequest> variantPayloads) {
        List<ProductVariantEntity> variants = Lists.newArrayList();

        for(ProductVariantRequest request : variantPayloads) {
            ProductVariantEntity entity = new ProductVariantEntity();

            String thumbnailUrl = fileService.uploadFile(request.getThumbnail(), "thumbnails").getUrl();
            List<String> filesUrls = fileService.uploadFiles(request.getImages(), "variants").stream()
                    .map(FileResponse::getUrl)
                    .collect(Collectors.toList());

            entity.setThumbnail(thumbnailUrl);
            entity.setImages(filesUrls);
            entity.setColors(request.getColors());
            entity.setProduct(owner);

            entity.generateSku();
            variants.add(entity);
        }

        return variantRepo.saveAll(variants);
    }
}
