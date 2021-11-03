package edu.epam.esm.task.service.impl;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.Order;
import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.entity.User;
import edu.epam.esm.task.repository.CertificateRepository;
import edu.epam.esm.task.repository.OrderRepository;
import edu.epam.esm.task.repository.UserRepository;
import edu.epam.esm.task.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final CertificateRepository certificateRepository;

    private final OrderRepository orderRepository;

    public UserServiceImpl(UserRepository repository, CertificateRepository certificateRepository,
                           OrderRepository orderRepository) {
        this.repository = repository;
        this.certificateRepository = certificateRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<User> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    @Override
    public boolean buyCertificate(long userId, long certificateId) {
        boolean result = false;
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Optional<Certificate> optionalCertificate = certificateRepository.findById(certificateId);
            if (optionalCertificate.isPresent()){
                Certificate certificate = optionalCertificate.get();
                Order order = new Order();
                order.setUser(user);
                order.setCertificate(certificate);
                order.setOrderDate(new Date());
                order.setTotalPrice(certificate.getPrice());

                orderRepository.save(order);
                if (user.getOrders() == null){
                    Set<Order> orders = new HashSet<>();
                    orders.add(order);
                    user.setOrders(orders);
                } else {
                  user.getOrders().add(order);
                }

                repository.save(user);
                result = true;
            }
        }

        return result;
    }

    @Override
    public Optional<Tag> getMostWidelyUsedTag() {
        long userId = orderRepository.getUserWithHighestCostOfAllOrders();
        Optional<User> optionalUser = repository.findById(userId);

        Optional<Tag> optionalTag = Optional.empty();
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Map<Tag, Integer> tagMap = new HashMap<>();
            Set<Order> orders = user.getOrders();

            orders.forEach(a -> a.getCertificate().getTags().forEach(b -> {
                if (tagMap.containsKey(b)){
                    tagMap.replace(b, tagMap.get(b) + 1);
                }else{
                    tagMap.put(b, 1);
                }
            }));

            int maxValue = Collections.max(tagMap.values());

            List<Map.Entry<Tag, Integer>> resultTags = tagMap.entrySet().stream().filter(a -> a.getValue() == maxValue).collect(Collectors.toList());
            if (!resultTags.isEmpty()){
                optionalTag = Optional.of(resultTags.get(0).getKey());
            }
        }

        return optionalTag;
    }
}
