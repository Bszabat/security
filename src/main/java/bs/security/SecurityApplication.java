package bs.security;

import bs.security.dao.UserDao;
import bs.security.entity.User;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication {
 @Autowired
 private UserDao dao;
 @Autowired
 private PasswordEncoder passwordEncoder; 
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
        
 @PostConstruct
 public void init() {
 dao.save(new User("Piotr", "Piotrowski","admin",
 passwordEncoder.encode("admin")));
 dao.save(new User("Ania", "Annowska","ania",
 passwordEncoder.encode("ania")));
 } 

}
