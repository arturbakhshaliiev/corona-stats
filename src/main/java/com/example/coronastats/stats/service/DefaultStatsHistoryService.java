package com.example.coronastats.stats.service;

import com.example.coronastats.stats.model.StatsHistory;
import com.example.coronastats.stats.repository.StatsHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultStatsHistoryService implements StatsHistoryService {

    @Autowired
    private StatsHistoryRepository statsHistoryRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<StatsHistory> list(StatsHistoryFilter statsHistoryFilter, Pageable pageable) {
        return statsHistoryRepository.findAll(toSpecification(statsHistoryFilter), pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StatsHistory> list(StatsHistoryFilter filter, Sort sort) {
        return statsHistoryRepository.findAll(toSpecification(filter), sort);
    }

    @Transactional
    @Override
    public StatsHistory getOne(StatsHistoryFilter filter) {
        return statsHistoryRepository.findOne(toSpecification(filter)).orElse(null);
    }

    @Transactional
    @Override
    public StatsHistory add(StatsHistory statsHistory) {
        statsHistoryRepository.save(statsHistory);
        logger.info(statsHistory + " was added");
        return statsHistory;
    }

    @Transactional
    @Override
    public StatsHistory update(StatsHistory statsHistory) {
        statsHistoryRepository.save(statsHistory);
        logger.info(statsHistory + " was updated");
        return statsHistory;
    }

    private Specification<StatsHistory> toSpecification(StatsHistoryFilter filter) {
        return new Specification<StatsHistory>() {

            private static final long serialVersionUID = 1;

            @Override
            public Predicate toPredicate(Root<StatsHistory> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();

                if (filter.getCountry() != null) {
                    predicates.add(builder.equal(root.get("country"), filter.getCountry()));
                }

                if (filter.getDate() != null) {
                    predicates.add(builder.equal(root.get("date"), filter.getDate()));
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
