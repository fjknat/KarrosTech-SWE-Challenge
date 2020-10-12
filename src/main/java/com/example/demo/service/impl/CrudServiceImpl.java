/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DemoApplication;
import com.example.demo.config.ExceptionMessageEnum;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.SystemException;
import com.example.demo.service.BaseRepository;
import com.example.demo.service.CrudService;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public abstract class CrudServiceImpl<T, ID, Repository extends BaseRepository<T, ID>> extends BaseService
		implements CrudService<T, ID> {

	private static final Logger log = LogManager.getLogger(DemoApplication.class);
	
	@Autowired
	protected Repository baseRepository;

	protected abstract String getDefaultColumnSort();

	protected Optional<T> save(T t) {
		return Optional.ofNullable(baseRepository.save(t));
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Page<T> findAll(String[] columnSort, String sortDirection) {
		try {
			Integer sizeValue = Integer.MAX_VALUE;
			if (columnSort.length <= 0) {
				columnSort = new String[] { getDefaultColumnSort() };
			}
			Sort sort = Sort.by(new Sort.Order(StringUtils.isBlank(sortDirection) ? super.getOrderSortOfPaging()
					: Sort.Direction.fromString(sortDirection), columnSort[0]).ignoreCase());
//			Sort sort = new Sort(StringUtils.isBlank(sortDirection) ? super.getOrderSortOfPaging()
//					: Sort.Direction.fromString(sortDirection), columnSort);
			PageRequest pageable = PageRequest.of(0, sizeValue, sort);
			return this.baseRepository.findAll(pageable);
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60101);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Page<T> findAllWithPaging(Integer pageIndex, Integer size, String[] columnSort, String sortDirection) {
		try {
			Integer sizeValue = super.getMaxNumberOfPaging();
			if (size != null) {
				sizeValue = size;
			}
			if (columnSort.length <= 0) {
				columnSort = new String[] { getDefaultColumnSort() };
			}
			Sort sort = Sort.by(new Sort.Order(StringUtils.isBlank(sortDirection) ? super.getOrderSortOfPaging()
					: Sort.Direction.fromString(sortDirection), columnSort[0]).ignoreCase());
//			Sort sort = new Sort(StringUtils.isBlank(sortDirection) ? super.getOrderSortOfPaging()
//					: Sort.Direction.fromString(sortDirection), columnSort);
			PageRequest pageable = PageRequest.of(pageIndex, sizeValue, sort);
			return this.baseRepository.findAll(pageable);
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60101);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}
	
	public Pageable createPageRequest(Integer pageIndex, Integer size, String[] columnSort, String sortDirection) {
		Integer sizeValue = super.getMaxNumberOfPaging();
		if (size != null) {
			sizeValue = size;
		}
		if (columnSort.length <= 0) {
			columnSort = new String[] { getDefaultColumnSort() };
		}
		Sort sort = Sort.by(new Sort.Order(StringUtils.isBlank(sortDirection) ? super.getOrderSortOfPaging()
				: Sort.Direction.fromString(sortDirection), columnSort[0]).ignoreCase());
		return PageRequest.of(pageIndex, sizeValue, sort);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public T add(T t) {
		try {
			return save(t).orElseThrow(() -> new ServiceException(ExceptionMessageEnum.ERROR_CODE_60101));
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public T edit(T t) {
		try {
			return save(t).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessageEnum.ERROR_CODE_60404));
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Boolean deleteById(ID id) {
		try {
			if (!baseRepository.findById(id).isPresent()) {
				ResourceNotFoundException ex = new ResourceNotFoundException(ExceptionMessageEnum.ERROR_CODE_60404);
				log.error(ex);
				throw ex;
			}

			baseRepository.deleteById(id);
			return true;
		} catch (ServiceException e) {
			log.error(e);
			throw e;
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Boolean delete(T t) {
		try {
			baseRepository.delete(t);
			return true;
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60101);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Optional<T> findById(ID id) {
		try {
			return baseRepository.findById(id);
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public List<T> findAll() {
		try {
			return baseRepository.findAll();
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Boolean isExistById(ID id) {
		try {
			return baseRepository.existsById(id);
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}
	
}
