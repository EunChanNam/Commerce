package com.commerce.backendserver.member.infra;

import static com.commerce.backendserver.tables.JUsers.*;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import com.commerce.backendserver.member.domain.Users;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JUserRepository {

	private final DSLContext dslContext;

	public Users find() {
		return dslContext
			.select(USERS.TIME_STAMP, USERS.TOTAL_COUNT, USERS.NAME)
			.from(USERS)
			.fetchOneInto(Users.class);
	}
}
