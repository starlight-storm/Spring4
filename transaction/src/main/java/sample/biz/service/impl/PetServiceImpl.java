package sample.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import sample.biz.dao.PetDao;
import sample.biz.domain.Pet;
import sample.biz.exception.BussinessException;
import sample.biz.service.PetService;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 10, readOnly = false, rollbackFor = BussinessException.class)
    public void updatePet(Pet pet) throws BussinessException {
        petDao.updatePet(pet);
    }

    public void updatePetProgrammaticTransaction(Pet pet) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setTimeout(10);
        def.setReadOnly(false);
        TransactionStatus status = txManager.getTransaction(def);
        try {
            petDao.updatePet(pet);
        } catch (RuntimeException e) {
            txManager.rollback(status);
            throw e;
        }
        txManager.commit(status);
    }

    public void updatePetProgrammaticTransaction2(final Pet pet) {
        TransactionTemplate t = new TransactionTemplate(txManager);
        t.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        t.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        t.setTimeout(10);
        t.setReadOnly(false);

        t.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                petDao.updatePet(pet);
            }
        });
    }

}
