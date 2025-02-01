package com.baibei.myservicespace.repositories;

import com.baibei.myservicespace.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Message, Long> {
}
