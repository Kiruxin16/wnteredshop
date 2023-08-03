package ru.geekbrains.wnteredshop.core.repositories;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import ru.geekbrains.wnteredshop.core.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class ProductCachedRepository  implements ProductRepository{

    private final ProductRepository productRepository;

    private final RedisTemplate<Long,Object> productRedisTemplate;



    //HOME_WORK
    @Override
    public Optional<Product> findById(Long id) {
        if(!productRedisTemplate.hasKey(id)){
            productRedisTemplate.opsForValue().set(id,productRepository.findById(id));
        }
        return Optional.of((Product) productRedisTemplate.opsForValue().get(id));
    }



    //Простыня
    @Override
    public List<Product> getCostDiap(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Product getOne(Long aLong) {
        return null;
    }

    @Override
    public Product getById(Long aLong) {
        return null;
    }

    @Override
    public Product getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Optional<Product> findOne(Specification<Product> spec) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll(Specification<Product> spec) {
        return null;
    }

    @Override
    public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> findAll(Specification<Product> spec, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Product> spec) {
        return 0;
    }

    @Override
    public boolean exists(Specification<Product> spec) {
        return false;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Product> S save(S entity) {
        return null;
    }



    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
