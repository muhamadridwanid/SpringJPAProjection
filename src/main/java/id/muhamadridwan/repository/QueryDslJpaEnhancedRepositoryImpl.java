package id.muhamadridwan.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mridwan on 15/11/2017.
 */

public class QueryDslJpaEnhancedRepositoryImpl<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID>
        implements QueryDslPredicateAndProjectionExecutor<T, ID> {

    @Override
    public <PROJ> List<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Object target, Predicate predicate) {
        return null;
    }

    //All instance variables are available in super, but they are private
    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final EntityPath<T> path;
    private final PathBuilder<T> builder;
    private final Querydsl querydsl;


    public QueryDslJpaEnhancedRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
    }

    public QueryDslJpaEnhancedRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager,
                                             EntityPathResolver resolver) {

        super(entityInformation, entityManager, resolver);
        this.path = resolver.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder<T>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
    }

    @Override
    public <PROJ> Page<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate, Pageable pageable) {
        final JPQLQuery<?> countQuery = createCountQuery(predicate);
        JPQLQuery<PROJ> query = querydsl.applyPagination(pageable, createQuery(predicate).select(factoryExpression));

        long total = countQuery.fetchCount();
        List<PROJ> content = pageable == null || total > pageable.getOffset() ? query.fetch() : Collections.<PROJ>emptyList();

        return new PageImpl<PROJ>(content, pageable, total);
    }

    @Override
    public <PROJ> List<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate) {
        JPQLQuery query = querydsl.applyPagination(null, createQuery(predicate).select(factoryExpression));
        List<PROJ> content = query.fetch();
        return content;
    }

    @Override
    public <PROJ> Page<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate, Pageable pageable, Sort sort) {
        final JPQLQuery<?> countQuery = createCountQuery(predicate);
        JPQLQuery<PROJ> query = querydsl.applyPagination(pageable, createQuery(predicate).select(factoryExpression));
        query = querydsl.applySorting(sort, query);

        long total = countQuery.fetchCount();
        List<PROJ> content = pageable == null || total > pageable.getOffset() ? query.fetch() : Collections.<PROJ>emptyList();

        return new PageImpl<PROJ>(content, pageable, total);
    }

    @Override
    public <PROJ, TARGET> List<TARGET> findWithProjection(FactoryExpression<PROJ> factoryExpression, Class<TARGET> clazz, Predicate predicate) {
        JPQLQuery query = querydsl.applyPagination(null, createQuery(predicate).select(factoryExpression));
        List<PROJ> content = query.fetch();
        List<TARGET> targets = new ArrayList<TARGET>();

        content.forEach(proj -> {
            try {
                TARGET targetClass2 = clazz.newInstance();
                BeanUtils.copyProperties(proj, targetClass2);
                targets.add(targetClass2);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        });

        return targets;
    }

    @Override
    public <PROJ> List<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate, Sort sort) {
        JPQLQuery query = querydsl.applySorting(sort, createQuery(predicate).select(factoryExpression));
        List<PROJ> content = query.fetch();
        return content;
    }

}