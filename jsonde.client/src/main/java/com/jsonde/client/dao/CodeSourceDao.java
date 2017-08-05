package com.jsonde.client.dao;

import com.jsonde.client.domain.CodeSource;

import javax.sql.DataSource;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class CodeSourceDao extends AbstractEntityDao<CodeSource> {

    public CodeSourceDao(DataSource dataSource) throws DaoException {
        super(dataSource);
    }

}