package com.commerce.backendserver.review.infra.persistence.query;

import com.commerce.backendserver.product.domain.option.constants.ProductSize;
import com.commerce.backendserver.review.domain.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

import static com.commerce.backendserver.product.domain.QProduct.product;
import static com.commerce.backendserver.product.domain.option.QProductOption.productOption;
import static com.commerce.backendserver.review.domain.QReview.review;
import static com.commerce.backendserver.review.domain.additionalinfo.QAdditionalInfo.additionalInfo;

@RequiredArgsConstructor
public class ReviewQueryDslRepositoryImpl implements ReviewQueryDslRepository {

	private final JPAQueryFactory query;

	@Override
	public List<Review> findReviewByStatisticCondition(
		Set<String> engColorNames,
		Set<ProductSize> sizes,
		Set<String> additionalOptions,
		Set<Integer> scores,
		Set<String> additionalInfoValues
	) {
		return this.query
			.select(review)
			.from(review)
			.leftJoin(review.product)
			.fetchJoin()
			.leftJoin(product.options).on(product.id.eq(productOption.product.id))
			.where(
				engColorNameIn(engColorNames),
				sizeIn(sizes),
				additionalOptionIn(additionalOptions),
				scoreIn(scores),
				additionalInfoIn(additionalInfoValues))
			.groupBy(review.id)
			.fetch();

	}

	private BooleanExpression engColorNameIn(Set<String> engColorNames) {
		if (engColorNames != null)
			return productOption.color.engColorName.in(engColorNames);
		return null;
	}

	private BooleanExpression sizeIn(Set<ProductSize> sizes) {
		if (sizes != null)
			return productOption.size.in(sizes);
		return null;
	}

	private BooleanExpression additionalOptionIn(Set<String> additionalOptionValues) {
		if (additionalOptionValues != null)
			return productOption.additionalOption.value.in(additionalOptionValues);
		return null;
	}

	private BooleanExpression scoreIn(Set<Integer> scores) {
		if (scores != null)
			return review.score.in(scores);
		return null;
	}

	private BooleanExpression additionalInfoIn(Set<String> additionalInfoValues) {
		if (additionalInfoValues != null)
			return additionalInfo.infoValue.in(additionalInfoValues);
		return null;
	}
}
