package com.julianjupiter.addressbook.persistence.repository;

import com.julianjupiter.addressbook.persistence.entity.Contact;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

class ContactRepositoryImpl implements ContactRepository {

    @Override
    public List<Contact> findAll() {
        var entityManager = this.entityManager();

        try {
            return entityManager
                    .createQuery("SELECT c FROM Contact c", Contact.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Contact> findById(Long id) {
        var entityManager = this.entityManager();

        try {
            return Optional.ofNullable(entityManager.find(Contact.class, id));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Contact save(Contact contact) {
        var entityManager = this.entityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var id = contact.getId();
            if (id != null) {
                entityManager.merge(contact);
            } else {
                entityManager.persist(contact);
            }
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }

        return contact;
    }

    @Override
    public void deleteById(Long id) {
        var contactOptional = this.findById(id);

        contactOptional.ifPresent(contact -> {
            var entityManager = this.entityManager();
            EntityTransaction transaction = null;

            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                contact = entityManager.contains(contact) ? contact : entityManager.merge(contact);
                entityManager.remove(contact);
                transaction.commit();
            } catch (Exception exception) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            } finally {
                entityManager.close();
            }
        });
    }

    @Override
    public List<Contact> findByFirstNameOrLastName(String name) {
        var entityManager = this.entityManager();

        try {
            return entityManager
                    .createQuery("SELECT c FROM Contact c WHERE UPPER(c.lastName) LIKE :name OR UPPER(c.firstName) LIKE :name", Contact.class)
                    .setParameter("name", "%" + name.toUpperCase() + "%")
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
