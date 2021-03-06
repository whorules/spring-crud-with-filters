package ru.korovko.crud.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.mapper.CarMapper;
import ru.korovko.crud.model.Car;
import ru.korovko.crud.repository.CarSearchRepository;
import ru.korovko.crud.dto.CarSearchRequest;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class CarSearchRepositoryImpl implements CarSearchRepository {

    private static final String FN_COLOR = "color";
    private static final String FN_MODEL = "model";
    private static final String FN_WEIGHT = "weight";
    private static final String FN_MAX_SPEED = "maxSpeed";
    private static final String FN_IS_AUTOMATIC = "isAutomatic";
    private static final String FN_MANUFACTURE_DATE = "manufactureDate";

    private final EntityManager entityManager;
    private final CarMapper carMapper;

    @Autowired
    public CarSearchRepositoryImpl(EntityManager entityManager, CarMapper carMapper) {
        this.entityManager = entityManager;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarResponse> find(CarSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        List<Predicate> predicates = new ArrayList<>();

        addInPredicate(FN_MODEL, predicates, criteriaBuilder, root, request.getModels());
        addEqualPredicate(FN_COLOR, predicates, criteriaBuilder, root, request.getColor());
        addEqualPredicate(FN_IS_AUTOMATIC, predicates, criteriaBuilder, root, request.getIsAutomatic());
        addDatesBetweenPredicate(FN_MANUFACTURE_DATE, predicates, criteriaBuilder, root,
                request.getStartManufactureDate(), request.getEndManufactureDate());
        addLessThanPredicate(FN_WEIGHT, predicates, criteriaBuilder, root, request.getMaxWeight());
        addLessThanPredicate(FN_MAX_SPEED, predicates, criteriaBuilder, root, request.getMaxSpeed());

        criteriaQuery.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(criteriaQuery).getResultList().stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    private void addInPredicate(String fieldName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                Root<Car> root, List<String> values) {
        CriteriaBuilder.In<String> inClause;
        if (nonNull(values)) {
            inClause = criteriaBuilder.in(root.get(fieldName));
            for (String string : values) {
                inClause.value(string);
            }
            predicates.add(criteriaBuilder.and(inClause));
        }
    }

    private void addEqualPredicate(String fieldName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                   Root<Car> root, Object value) {
        if (nonNull(value)) {
            predicates.add(criteriaBuilder.equal(root.get(fieldName), value));
        }
    }

    private void addDatesBetweenPredicate(String fieldName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                          Root<Car> root, LocalDate startDate, LocalDate endDate) {
        if (nonNull(startDate) && nonNull(endDate)){
            predicates.add(criteriaBuilder.between(root.get(fieldName), startDate, endDate));
        } else if (nonNull(endDate)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), endDate));
        } else if (nonNull(startDate)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), startDate));
        }
    }

    private void addLessThanPredicate(String fieldName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                   Root<Car> root, Integer value) {
        if (nonNull(value)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), value));
        }
    }
}
