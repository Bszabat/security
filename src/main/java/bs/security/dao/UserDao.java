/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bs.security.dao;

import bs.security.entity.User;
import org.springframework.data.repository.CrudRepository;
public interface UserDao extends CrudRepository<User, Integer> {
 public User findByLogin(String login);
}
