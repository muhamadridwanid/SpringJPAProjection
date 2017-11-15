package id.muhamadridwan.repository;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mridwan on 15/11/2017.
 */
@NoRepositoryBean
public interface QueryDslPredicateAndProjectionExecutor<T, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

    <PROJ> Page<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate, Pageable pageable, Sort sort);

    <PROJ> Page<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate, Pageable pageable);

    <PROJ> List<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate, Sort sort);

    <PROJ> List<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Predicate predicate);

    <PROJ, TARGET> List<TARGET> findWithProjection(FactoryExpression<PROJ> factoryExpression, Class<TARGET> targetClass, Predicate predicate);

    <PROJ> List<PROJ> findWithProjection(FactoryExpression<PROJ> factoryExpression, Object target, Predicate predicate);


}