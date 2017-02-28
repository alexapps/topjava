package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    Map<Integer, User> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    {
        save(new User(1, "User", "user@mail.ru", "password", Role.ROLE_USER));
        save(new User(2, "Admin", "admin@mail.ru", "admin", Role.ROLE_ADMIN));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        boolean returnValue = false;

        if (repository.containsKey(id)) {
            repository.remove(id);
            returnValue = true;
        }

        return returnValue;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);

        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);

        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.containsKey(id) ? repository.get(id) : null ;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");

        List<User> userList = repository.values().stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
        return  userList.size() != 0 ? userList : Collections.emptyList();
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
