package sample.springdatajpa.dataaccess;

import sample.springdatajpa.business.service.PetDaoCustom;

public class PetDaoImpl implements PetDaoCustom {
    @Override
    public void foo() {
        System.out.println("foo!");
    }
}
