package com.commerce.backendserver.review.infra.persistence;

import com.commerce.backendserver.review.domain.Review;
import com.commerce.backendserver.review.infra.persistence.query.ReviewQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ReviewQueryRepository extends JpaRepository<Review, Long>, ReviewQueryDslRepository {
}
