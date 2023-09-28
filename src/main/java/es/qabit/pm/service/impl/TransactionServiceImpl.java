package es.qabit.pm.service.impl;

import es.qabit.pm.domain.Transaction;
import es.qabit.pm.domain.UserPoints;
import es.qabit.pm.domain.enumeration.Status;
import es.qabit.pm.repository.TransactionRepository;
import es.qabit.pm.repository.UserPointsRepository;
import es.qabit.pm.service.TransactionService;
import es.qabit.pm.service.dto.TransactionDTO;
import es.qabit.pm.service.exceptions.InsufficientBalanceExceptions;
import es.qabit.pm.service.exceptions.UserNotFoundExceptions;
import es.qabit.pm.service.mapper.TransactionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link es.qabit.pm.domain.Transaction}.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    private final UserPointsRepository userPointsRepository;

    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(
        TransactionRepository transactionRepository,
        UserPointsRepository userPointsRepository,
        TransactionMapper transactionMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.userPointsRepository = userPointsRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {
        log.debug("Request to save Transaction : {}", transactionDTO);
        Long userFromId = transactionDTO.getUserFrom().getId();
        UserPoints userFrom = userPointsRepository.findById(userFromId).orElseThrow(() -> new UserNotFoundExceptions(userFromId));
        Integer currentBalance = userFrom.getPoints();

        if (currentBalance > 0 && currentBalance >= transactionDTO.getPoints()) {
            userFrom.setPoints(userFrom.getPoints() - transactionDTO.getPoints());
            userPointsRepository.save(userFrom);

            Long userToId = transactionDTO.getUserTo().getId();
            UserPoints userTo = userPointsRepository.findById(userToId).orElseThrow(() -> new UserNotFoundExceptions(userToId));
            userTo.setPoints(userTo.getPoints() + transactionDTO.getPoints());
            userPointsRepository.save(userTo);

            Transaction transaction = transactionMapper.toEntity(transactionDTO);
            transaction.setStatus(Status.COMPLETADA);
            transaction = transactionRepository.save(transaction);
            return transactionMapper.toDto(transaction);
        }
        throw new InsufficientBalanceExceptions();
    }

    @Override
    public TransactionDTO update(TransactionDTO transactionDTO) {
        log.debug("Request to update Transaction : {}", transactionDTO);
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public Optional<TransactionDTO> partialUpdate(TransactionDTO transactionDTO) {
        log.debug("Request to partially update Transaction : {}", transactionDTO);

        return transactionRepository
            .findById(transactionDTO.getId())
            .map(existingTransaction -> {
                transactionMapper.partialUpdate(existingTransaction, transactionDTO);

                return existingTransaction;
            })
            .map(transactionRepository::save)
            .map(transactionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transactions");
        return transactionRepository.findAll(pageable).map(transactionMapper::toDto);
    }

    public Page<TransactionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return transactionRepository.findAllWithEagerRelationships(pageable).map(transactionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionDTO> findOne(Long id) {
        log.debug("Request to get Transaction : {}", id);
        return transactionRepository.findOneWithEagerRelationships(id).map(transactionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transaction : {}", id);
        transactionRepository.deleteById(id);
    }
}
