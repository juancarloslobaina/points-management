package es.qabit.pm.service.impl;

import es.qabit.pm.domain.UserPoints;
import es.qabit.pm.repository.UserPointsRepository;
import es.qabit.pm.service.UserPointsService;
import es.qabit.pm.service.dto.UserPointsDTO;
import es.qabit.pm.service.mapper.UserPointsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link es.qabit.pm.domain.UserPoints}.
 */
@Service
@Transactional
public class UserPointsServiceImpl implements UserPointsService {

    private final Logger log = LoggerFactory.getLogger(UserPointsServiceImpl.class);

    private final UserPointsRepository userPointsRepository;

    private final UserPointsMapper userPointsMapper;

    public UserPointsServiceImpl(UserPointsRepository userPointsRepository, UserPointsMapper userPointsMapper) {
        this.userPointsRepository = userPointsRepository;
        this.userPointsMapper = userPointsMapper;
    }

    @Override
    public UserPointsDTO save(UserPointsDTO userPointsDTO) {
        log.debug("Request to save UserPoints : {}", userPointsDTO);
        UserPoints userPoints = userPointsMapper.toEntity(userPointsDTO);
        userPoints = userPointsRepository.save(userPoints);
        return userPointsMapper.toDto(userPoints);
    }

    @Override
    public UserPointsDTO update(UserPointsDTO userPointsDTO) {
        log.debug("Request to update UserPoints : {}", userPointsDTO);
        UserPoints userPoints = userPointsMapper.toEntity(userPointsDTO);
        userPoints = userPointsRepository.save(userPoints);
        return userPointsMapper.toDto(userPoints);
    }

    @Override
    public Optional<UserPointsDTO> partialUpdate(UserPointsDTO userPointsDTO) {
        log.debug("Request to partially update UserPoints : {}", userPointsDTO);

        return userPointsRepository
            .findById(userPointsDTO.getId())
            .map(existingUserPoints -> {
                userPointsMapper.partialUpdate(existingUserPoints, userPointsDTO);

                return existingUserPoints;
            })
            .map(userPointsRepository::save)
            .map(userPointsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserPointsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserPoints");
        return userPointsRepository.findAll(pageable).map(userPointsMapper::toDto);
    }

    public Page<UserPointsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return userPointsRepository.findAllWithEagerRelationships(pageable).map(userPointsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserPointsDTO> findOne(Long id) {
        log.debug("Request to get UserPoints : {}", id);
        return userPointsRepository.findOneWithEagerRelationships(id).map(userPointsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPoints : {}", id);
        userPointsRepository.deleteById(id);
    }
}
