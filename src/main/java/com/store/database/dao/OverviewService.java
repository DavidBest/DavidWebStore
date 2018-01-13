package com.store.database.dao;

import com.store.database.model.Overview;
import com.store.exception.DoesNotExistException;
import org.springframework.stereotype.Repository;

public interface OverviewService extends WebStoreService<Overview,Long> {

}
