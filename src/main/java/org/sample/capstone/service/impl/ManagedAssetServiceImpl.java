package org.sample.capstone.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.sample.capstone.entity.Account;
import org.sample.capstone.entity.AssetDetail;
import org.sample.capstone.entity.ManagedAsset;
import org.sample.capstone.entity.ManagedAssetView;
import org.sample.capstone.proxy.AccountsProxy;
import org.sample.capstone.proxy.AssetsProxy;
import org.sample.capstone.repository.ManagedAssetRepository;
import org.sample.capstone.service.api.ManagedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ManagedAssetServiceImpl implements ManagedAssetService {

	@Autowired
	private ManagedAssetRepository managedAssetRepository;

	@Autowired
	private AccountsProxy accountsProxy;

	@Autowired
	private AssetsProxy assetsProxy;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public void delete(ManagedAssetView managedAssetView) {
		managedAssetRepository.delete(convertToEntity(managedAssetView));
	}

	@Transactional
	public List<ManagedAssetView> save(Iterable<ManagedAssetView> managedAssetViews) {
		List<ManagedAsset> entities = StreamSupport.stream(managedAssetViews.spliterator(), false)
				.map(this::convertToEntity).collect(Collectors.toList());
		List<ManagedAsset> saveAll = managedAssetRepository.saveAll(entities);
		return StreamSupport.stream(saveAll.spliterator(), false).map(this::convertToDto).collect(Collectors.toList());

	}

	@Transactional
	public void delete(Iterable<Long> ids) {
		List<ManagedAsset> toDelete = managedAssetRepository.findAllById(ids);
		managedAssetRepository.deleteInBatch(toDelete);
	}

	@Transactional
	public ManagedAssetView save(ManagedAssetView managedAssetView) {
		ManagedAsset anagedAsset = convertToEntity(managedAssetView);
		ResponseEntity<Account> account = accountsProxy.show(managedAssetView.getAccount().getId());
		ResponseEntity<AssetDetail> asset = assetsProxy.show(managedAssetView.getAsset().getId());
		anagedAsset.setAccount(account.getBody());
		anagedAsset.setAsset(asset.getBody());
		ManagedAsset savedManagedAsset = managedAssetRepository.save(anagedAsset);
		return convertToDto(savedManagedAsset);
	}

	public ManagedAssetView findOne(Long id) {
		Optional<ManagedAsset> findById = managedAssetRepository.findById(id);
		return convertToDto(findById.get());
	}

	public ManagedAssetView findOneForUpdate(Long id) {
		Optional<ManagedAsset> findById = managedAssetRepository.findById(id);
		return convertToDto(findById.get());
	}

	public List<ManagedAssetView> findAll(Iterable<Long> ids) {
		List<ManagedAsset> findAllById = managedAssetRepository.findAllById(ids);
		return StreamSupport.stream(findAllById.spliterator(), false).map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public List<ManagedAssetView> findAll() {
		List<ManagedAsset> findAll = managedAssetRepository.findAll();
		return StreamSupport.stream(findAll.spliterator(), false).map(this::convertToDto).collect(Collectors.toList());
	}

	public long count() {
		return managedAssetRepository.count();
	}

	public Page<ManagedAssetView> findAll(Pageable pageable) {
		Page<ManagedAsset> findAll = managedAssetRepository.findAll(pageable);
		List<ManagedAssetView> managedAssetViews = StreamSupport.stream(findAll.spliterator(), false).map(this::convertToDto)
				.collect(Collectors.toList());
		return new PageImpl<>(managedAssetViews, findAll.getPageable(), managedAssetViews.size());
	}

	public Class<ManagedAssetView> getEntityType() {
		return ManagedAssetView.class;
	}

	public Class<Long> getIdType() {
		return Long.class;
	}

	private ManagedAssetView convertToDto(ManagedAsset managedAsset) {
		ManagedAssetView managedAssetView = modelMapper.map(managedAsset, ManagedAssetView.class);
		return managedAssetView;
	}

	private ManagedAsset convertToEntity(ManagedAssetView managedAssetView) {
		ManagedAsset managedAsset = modelMapper.map(managedAssetView, ManagedAsset.class);
		return managedAsset;
	}
}
