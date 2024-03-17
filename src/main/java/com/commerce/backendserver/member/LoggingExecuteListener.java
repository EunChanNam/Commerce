package com.commerce.backendserver.member;

import java.util.Arrays;

import org.jooq.DSLContext;
import org.jooq.ExecuteContext;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingExecuteListener extends DefaultExecuteListener {

	@Override
	public void executeStart(ExecuteContext ctx) {
		// Create a new DSLContext for logging rendering purposes
		// This DSLContext doesn't need a connection, only the SQLDialect...
		DSLContext create = DSL.using(ctx.dialect(),
			// ... and the flag for pretty-printing
			new Settings().withRenderFormatted(true));
		// If we're executing a query
		if (ctx.query() != null) {
			log.info("{}", create.renderInlined(ctx.query()));
		} else if (ctx.routine() != null) {
			// If we're executing a routine
			log.info("{}", create.renderInlined(ctx.routine()));
		} else if (ctx.batchQueries() != null) {
			// If we're executing a batch queries
			Arrays.stream(ctx.batchQueries()).forEach(query -> log.info("{}", create.renderInlined(query)));
		}
	}
}
